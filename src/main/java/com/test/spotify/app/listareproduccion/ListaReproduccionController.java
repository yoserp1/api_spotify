package com.test.spotify.app.listareproduccion;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.spotify.app.autenticar.AccessTokenService;
import com.test.spotify.app.autenticar.PerfilService;
import com.test.spotify.app.autenticar.SpotifyUrlService;
import com.test.spotify.app.constantes.ApiPath;
import com.test.spotify.app.constantes.Template;
import com.test.spotify.app.excepcion.NoTrackPlayingExcepcion;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ListaReproduccionController {
	
	private final SpotifyUrlService spotifyUrlService;
	private final AccessTokenService accessToken;
	private final PerfilService perfil;
	private final CurrentPlayingService currentPlaying;

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String showIndex(final Model model) {
		model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
		return Template.INDEX;
	}
	
	@GetMapping(value = ApiPath.CALLBACK, produces = MediaType.TEXT_HTML_VALUE)
	public String handleCallback(
			@RequestParam(value = "code", required = false) final String code,
			@RequestParam(value = "error", required = false) final String error, final Model model,
			final HttpSession session) {

			if (error != null) {
				model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
				return Template.CALLBACK_FALLO;
			}
			session.setAttribute("code", code);
			String token = accessToken.getToken(code);
	
			session.setAttribute("accessToken", token);
			model.addAttribute("accessToken", token);
			model.addAttribute("userName", perfil.getUsername(token));
	
			try {
				model.addAttribute("currentPlaying", currentPlaying.getCurrentPlaying(token));
				model.addAttribute("display", 1);
			} catch (NoTrackPlayingExcepcion exception) {
				model.addAttribute("display", 0);
			}

		return Template.CALLBACK_EXITO;
	}

}

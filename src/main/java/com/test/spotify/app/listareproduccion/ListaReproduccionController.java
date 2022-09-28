package com.test.spotify.app.listareproduccion;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.spotify.app.autenticar.SpotifyUrlService;
import com.test.spotify.app.constantes.Template;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ListaReproduccionController {
	
	private final SpotifyUrlService spotifyUrlService;

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String showIndex(final Model model) {
		model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
		return Template.INDEX;
	}

}

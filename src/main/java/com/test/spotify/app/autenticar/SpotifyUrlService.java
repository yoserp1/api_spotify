package com.test.spotify.app.autenticar;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.test.spotify.app.configuracion.AppConfiguracionProperties;
import com.test.spotify.app.util.CodeChallengeUtility;
import com.test.spotify.app.util.CodeVerifierUtility;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AppConfiguracionProperties.class)
public class SpotifyUrlService {

	private final AppConfiguracionProperties spotifyAppConfigurationProperties;
	private String codeVerifier;

	public String getAuthorizationURL() {
		final var properties = spotifyAppConfigurationProperties.getApp();
		final var codeVerifier = CodeVerifierUtility.generate();
		setCodeVerifier(codeVerifier);
		return "https://accounts.spotify.com/es/authorize?client_id=" + properties.getClientId()
				+ "&response_type=code&redirect_uri=" + properties.getRedirectUrl()
				+ "&code_challenge_method=S256&code_challenge=" + CodeChallengeUtility.generate(codeVerifier)
				+ "&scope=ugc-image-upload,user-read-playback-state,user-modify-playback-state,user-read-currently-playing,streaming,app-remote-control,user-read-email,user-read-private"
				+ ",playlist-read-collaborative,playlist-modify-public,playlist-read-private,playlist-modify-private,user-library-modify,user-library-read,user-top-read,user-read-playback-position,user-read-recently-played,user-follow-read,user-follow-modify";
	}
}
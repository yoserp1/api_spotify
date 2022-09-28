package com.test.spotify.app.autenticar;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.test.spotify.app.configuracion.AppConfiguracionProperties;
import com.test.spotify.app.constantes.ApiPath;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(value = AppConfiguracionProperties.class)
public class AccessTokenService {
	
	private final SpotifyUrlService spotifyUrlService;
	private final RestTemplate restTemplate;
	private final AppConfiguracionProperties spotifyAppConfigurationProperties;
	
	public String getToken(String code) {
		final var properties = spotifyAppConfigurationProperties.getApp();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", properties.getClientId());
		map.add("grant_type", "authorization_code");
		map.add("code", code);
		map.add("redirect_uri", properties.getRedirectUrl());
		map.add("code_verifier", spotifyUrlService.getCodeVerifier());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<AccessTokenDto> response = restTemplate.postForEntity(ApiPath.URL_API_TOKEN, request, AccessTokenDto.class);
		return response.getBody().getAccessToken();
	}

}

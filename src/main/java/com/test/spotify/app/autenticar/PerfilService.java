package com.test.spotify.app.autenticar;

import java.util.LinkedHashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.spotify.app.constantes.ApiPath;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilService {
	
	private final RestTemplate restTemplate;

	public LinkedHashMap getUser(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

		HttpEntity<String> entity = new HttpEntity<>("paramters", headers);

		ResponseEntity<Object> response = restTemplate.exchange(ApiPath.URL_PERFIL, HttpMethod.GET, entity, Object.class);
		LinkedHashMap result = (LinkedHashMap) response.getBody();

		return result;
	}

	public String getUsername(String token) {
		LinkedHashMap user = getUser(token);
		return (String) user.get("display_name");
	}

}

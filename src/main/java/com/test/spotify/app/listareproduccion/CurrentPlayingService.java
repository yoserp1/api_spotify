package com.test.spotify.app.listareproduccion;

import java.util.LinkedHashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.spotify.app.constantes.ApiPath;
import com.test.spotify.app.excepcion.NoTrackPlayingExcepcion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentPlayingService {
	
	private final RestTemplate restTemplate;

	public LinkedHashMap getCurrentPlaying(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

		HttpEntity<String> entity = new HttpEntity<>("paramters", headers);

		ResponseEntity<Object> response = restTemplate.exchange(ApiPath.URL_REPRODUCCION_ACTUAL, HttpMethod.GET, entity, Object.class);
		if (response.getStatusCodeValue() == 204) {
			throw new NoTrackPlayingExcepcion();
		}
		LinkedHashMap result = (LinkedHashMap) response.getBody();
		return result;
	}

}

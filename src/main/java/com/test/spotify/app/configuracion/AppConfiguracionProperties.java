package com.test.spotify.app.configuracion;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.test.spotify")
public class AppConfiguracionProperties {
	
	private App app = new App();

	@Data
	public class App {
		private String clientId;
		private String redirectUrl;
	}

}

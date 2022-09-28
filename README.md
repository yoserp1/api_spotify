# api_spotify
Se consume el servicio de Spotify, el cual le retornará una lista de géneros, se podrán listar, crear, ver detalle y eliminar.

# Pila tecnológica utilizada
* Java 17
* Spring Boot 2.7.2
* Spring MVC
* Thymeleaf
* Lombok
* RestTemplate

# Recursos
* Url configuración developer spotify (Creación de cuenta y obtener token) ```https://stackoverflow.com/questions/60659902/how-to-get-oauth-token-from-spotify```
* Postman con categorías musicales ```https://www.getpostman.com/collections/471a33c7a5aeb75f5d2c```

# Configuración de la aplicación Spotify
* Crear una nueva aplicación en la consola de desarrolladores de Spotify
* Establezca la URL de redirección en ```http://localhost:8080/callback```
* Copie el ID de cliente generado para la aplicación anterior junto con redirect-uri y configúrelos en el archivo application.properties
```
com.test.spotify.app.client-id=<Client-id>
com.test.spotify.app.redirect-url=<Redirect-URI>
```
## Como correrlo

* Clonar este repositorio
* Asegúrese de estar usando JDK 1.8 y Maven 3.x
* Puede compilar el proyecto y ejecutar las pruebas ejecutando ```mvn clean package```
* Para mas comodidad ejecutar desde un IDE ejemplo: Eclipse
* Ejecute los siguientes comandos:
```
mvn clean install
```
```
mvn spring-boot:run
```
* El puerto del servidor está configurado en 9090, que se puede cambiar en el archivo application.properties
* Vaya a la siguiente URL para ver la aplicación
```
http://localhost:8080
```

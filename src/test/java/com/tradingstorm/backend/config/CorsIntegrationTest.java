package com.tradingstorm.backend.config;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorsIntegrationTest {
  @LocalServerPort
  private int port;

  @Test
  void actuatorHealthEndpointShouldAllowCors() {
    String origin = "http://localhost:5173";

    Response response = RestAssured.given()
        .header("Origin", origin)
        .when()
        .get("http://localhost:" + port + "/actuator/health");

    assertThat(response.getHeader("Access-Control-Allow-Origin")).isEqualTo(origin);
  }
}

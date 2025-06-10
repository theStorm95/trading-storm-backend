package com.tradingstorm.backend.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityIntegrationTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUpRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  @Test
  void actuatorHealthEndpointShouldBePublic() {
    Response response = RestAssured.get("/actuator/health");

    assertThat(response.getStatusCode()).isEqualTo(200);
  }
}

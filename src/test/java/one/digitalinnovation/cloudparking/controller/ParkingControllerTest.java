package one.digitalinnovation.cloudparking.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ParkingControllerTest {

    @Container
    static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest");

    @LocalServerPort
    private int port;

    @DynamicPropertySource
    static void configureTestcontainers(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
    }

    @PostConstruct
    public void init() {
        RestAssured.port = port;
    }

    @Test
    void whenFindAllThenCheckResult() {
        given()
                .header("Authorization", "Basic dXNlcjp1c2VyQDEyMw==")
                .when()
                .get("/parkings")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("license[0]", equalTo("ABC-1234"))
        //.extract().response().body().prettyPrint()
        ;
    }

    @Test
    void whenCreateThenCheckIsCreate() {
        Map<String, String> request = new HashMap<>();
        request.put("color", "AZUL");
        request.put("license", "ABC-1234");
        request.put("model", "CELTA");
        request.put("state", "SC");

        given()
                .header("Authorization", "Basic YWRtaW46YWRtaW5AMTIz")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/parkings")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", equalTo("ABC-1234"))
                .body("color", equalTo("AZUL"))
                .body("model", equalTo("CELTA"));
    }

}

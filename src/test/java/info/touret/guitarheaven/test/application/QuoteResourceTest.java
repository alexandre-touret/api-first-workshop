package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.dto.GuitarDto;
import info.touret.guitarheaven.application.dto.QuoteDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static info.touret.guitarheaven.application.dto.GuitarDto.TYPE.ELECTRIC;

@QuarkusTest
public class QuoteResourceTest {
    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/quotes")
                .then()
                .statusCode(200)
                .assertThat().body("size()", Is.is(0));
    }


    @Test
    void should_create_successfully() {
        var quote = new QuoteDto(null, UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1"), 10D, null, OffsetDateTime.now());
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(quote)
                .when()
                .post("/quotes")
                .then()
                .statusCode(201);
    }

    @Test
    void should_create_and_fail() {
        var quote = new QuoteDto(null, UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb9"), 10D, null, OffsetDateTime.now());
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(quote)
                .when()
                .post("/quotes")
                .then()
                .statusCode(400);
    }
}

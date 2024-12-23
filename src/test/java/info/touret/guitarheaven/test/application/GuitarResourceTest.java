package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.dto.GuitarDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static info.touret.guitarheaven.application.dto.GuitarDto.TYPE.ELECTRIC;

@QuarkusTest
class GuitarResourceTest {

    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/guitars")
                .then()
                .statusCode(200)
                .assertThat().body("isEmpty()", Is.is(false));
    }

    @Test
    void should_create_successfully() {
        var guitar = new GuitarDto(null, "ES 335", ELECTRIC, 2500.0, 10);
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(guitar)
                .when()
                .post("/guitars")
                .then()
                .statusCode(201);
    }

    @Test
    void should_update_successfully() {
        var guitar = new GuitarDto(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585"), "ES 335", ELECTRIC, 2500.0, 9);
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(guitar)
                .when()
                .put("/guitars/628766d4-fee3-46dd-8bcb-426cffb4d585")
                .then()
                .statusCode(200);
    }

    @Test
    void should_delete_successfully() {
        RestAssured.given()
                .delete("/guitars/999")
                .then()
                .statusCode(204);
    }
}

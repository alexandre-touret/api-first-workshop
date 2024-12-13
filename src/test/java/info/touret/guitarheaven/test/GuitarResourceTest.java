package info.touret.guitarheaven.test;

import info.touret.guitarheaven.dto.GuitarDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static info.touret.guitarheaven.dto.GuitarDto.TYPE.ELECTRIC;

@QuarkusTest
class GuitarResourceTest {

    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/guitars")
                .then()
                .statusCode(200)
                .assertThat().body("size()", Is.is(1));
    }

    @Test
    void should_create_successfully() {
        var guitar = new GuitarDto(1L, "ES 335", ELECTRIC, 2500.0, 10);
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
        var guitar = new GuitarDto(1L, "ES 335", ELECTRIC, 2500.0, 10);
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(guitar)
                .when()
                .put("/guitars/1")
                .then()
                .statusCode(204);
    }

    @Test
    void should_delete_successfully() {
        var guitar = new GuitarDto(1L, "ES 335", ELECTRIC, 2500.0, 10);
        RestAssured.given()
                .delete("/guitars/1")
                .then()
                .statusCode(204);
    }
}

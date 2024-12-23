package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.dto.GuitarDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.UUID;

import static info.touret.guitarheaven.application.dto.GuitarDto.TYPE.ELECTRIC;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class GuitarResourceTest {

    @Order(1)
    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/guitars")
                .then()
                .statusCode(200)
                .assertThat().body("isEmpty()", Is.is(false));
    }

    @Order(2)
    @Test
    void should_create_successfully() {
        var guitar = new GuitarDto(null, "Gibson ES 135", ELECTRIC, 1500.0, 10);
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(guitar)
                .when()
                .post("/guitars")
                .then()
                .statusCode(201);
    }
    @Order(3)
    @Test
    void should_update_successfully() {
        var guitar = new GuitarDto(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585"), "Gibson ES 335", ELECTRIC, 2500.0, 9);
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(guitar)
                .when()
                .put("/guitars/628766d4-fee3-46dd-8bcb-426cffb4d585")
                .then()
                .statusCode(200);
    }
    @Order(6)
    @Test
    void should_delete_successfully() {
        RestAssured.given()
                .when()
                .delete("/guitars/628766d4-fee3-46dd-8bcb-426cffb4d585")
                .then()
                .statusCode(204);
    }

    @Order(4)
    @Test
    void should_get_guitar_successfully() {
        RestAssured.given()
                .get("/guitars/628766d4-fee3-46dd-8bcb-426cffb4d585")
                .then()
                .statusCode(200);
    }
    @Order(5)
    @Test
    void should_not_find_order_successfully() {
        RestAssured.given()
                .get("/guitars/628766d4-fee3-46dd-8bcb-426cffb4d587")
                .then()
                .statusCode(404);
    }
}

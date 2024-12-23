package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.dto.GuitarDto;
import info.touret.guitarheaven.application.dto.OrderDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static info.touret.guitarheaven.application.dto.GuitarDto.TYPE.ELECTRIC;

@QuarkusTest
class OrderResourceTest {
    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/orders")
                .then()
                .statusCode(200)
                .assertThat().body("size()", Is.is(1));
    }

    @Test
    void should_create_order_successfully() {
        var orderDto = new OrderDto(null, List.of(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d685")), 10D, OffsetDateTime.now());
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(orderDto)
                .when()
                .post("/orders")
                .then()
                .statusCode(201);
    }

    @Test
    void should_get_order_successfully() {
        RestAssured.given()
                .get("/orders/292a485f-a56a-4938-8f1a-bbbbbbbbbbb1")
                .then()
                .statusCode(200);
    }
    @Test
    void should_not_find_order_successfully() {
        RestAssured.given()
                .get("/orders/292a485f-a56a-4938-8f1a-bbbbbbbbbbb9")
                .then()
                .statusCode(404);
    }

}

package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.dto.OrderRequestDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class OrderRequestResourceTest {
    private static final String UUID_REGEX = "[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}";

    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/orders-requests")
                .then()
                .statusCode(200)
                .assertThat().body("isEmpty()", Is.is(false));
    }

    @Test
    void should_create_order_successfully() {
        var orderDto = new OrderRequestDto(null, List.of(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d685")), 10D, OffsetDateTime.now());
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(orderDto)
                .when()
                .post("/orders-requests")
                .then()
                .statusCode(201)
                .assertThat().body("orderId", MatchesPattern.matchesPattern(UUID_REGEX));

    }

    @Test
    void should_fail_creating_order() {
        var orderDto = new OrderRequestDto(null, List.of(UUID.fromString("625566d4-fee3-46dd-8bcb-426cffb4d685")), 10D, OffsetDateTime.now());
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(orderDto)
                .when()
                .post("/orders-requests")
                .then()
                .statusCode(417)
                .contentType(ContentType.fromContentType("application/problem+json"));

    }

    @Test
    void should_get_order_successfully() {
        RestAssured.given()
                .get("/orders-requests/292a485f-a56a-4938-8f1a-bbbbbbbbbbb1")
                .then()
                .statusCode(200)
                .assertThat().body("guitarIds", Every.everyItem(MatchesPattern.matchesPattern(UUID_REGEX)));
    }

    @Test
    void should_not_find_order_successfully() {
        RestAssured.given()
                .get("/orders-requests/292a485f-a56a-4938-8f1a-bbbbbbbbbbb9")
                .then()
                .statusCode(404);
    }

}

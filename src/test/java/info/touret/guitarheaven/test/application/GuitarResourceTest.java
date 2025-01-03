package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.generated.model.GuitarDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsAnything;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.UUID;

import static info.touret.guitarheaven.application.generated.model.TYPEDto.ELECTRIC;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class GuitarResourceTest {
    private static final String UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

    @Order(1)
    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/guitars?pageNumber=0&pageSize=10")
                .then()
                .statusCode(200)
                .assertThat().body("isEmpty()", Is.is(false))
                .assertThat().body("links.size()", Is.is(5))
                .assertThat().body("links.self", IsAnything.anything())
                .assertThat().body("links.next", IsAnything.anything())
                .assertThat().body("links.last", IsAnything.anything())
                .assertThat().body("links.prev", IsAnything.anything())
                .assertThat().body("links.first", IsAnything.anything());
    }

    @Order(2)
    @Test
    void should_create_successfully() {
        var guitar = new GuitarDto().guitarId(null).name("Gibson ES 135").type(ELECTRIC).priceInUSD(1500.0).stock(10);
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(guitar)
                .when()
                .post("/guitars")
                .then()
                .statusCode(201)
                .assertThat().body("guitarId", MatchesPattern.matchesPattern(UUID_REGEX));
    }

    @Order(3)
    @Test
    void should_update_successfully() {
        var guitar = new GuitarDto().guitarId(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585")).name("Gibson ES 135").type(ELECTRIC).priceInUSD(2500.0).stock(9);
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
                .delete("/guitars/628226d4-fee3-46dd-8bcb-426cefb4a666")
                .then()
                .statusCode(204);
    }

    @Order(8)
    @Test
    void should_fail_deleting() {
        RestAssured.given()
                .when()
                .delete("/guitars/111226d4-fee3-46dd-8bcb-426cefb4a666")
                .then()
                .statusCode(404).contentType(ContentType.fromContentType("application/problem+json"));
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
    void should_not_find_guitar_successfully() {
        RestAssured.given()
                .get("/guitars/628766d4-fee3-46dd-8bcb-426cffb4d587")
                .then()
                .statusCode(404).contentType(ContentType.fromContentType("application/problem+json"));
    }

    @Order(5)
    @Test
    void should_find_guitar_page_successfully() {
        RestAssured.given()
                .get("/guitars?pageNumber=0&pageSize=10")
                .then()
                .statusCode(200)
                .assertThat().body("links.size()", Is.is(5))
                .assertThat().body("links.self", IsAnything.anything())
                .assertThat().body("links.next", IsAnything.anything())
                .assertThat().body("links.last", IsAnything.anything())
                .assertThat().body("links.prev", IsAnything.anything())
                .assertThat().body("links.first", IsAnything.anything());
    }
}

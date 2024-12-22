package info.touret.guitarheaven.test.application;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

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
}

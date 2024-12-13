package info.touret.guitarheaven;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GuitarResourceTest {

    @Inject
    private GuitarResource guitarService;


    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/guitars")
                .then()
                .statusCode(200)
                .assertThat().body("size()", Is.is(1));
    }
}

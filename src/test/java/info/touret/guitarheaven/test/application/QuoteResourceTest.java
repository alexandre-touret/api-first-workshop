package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.generated.model.QuoteDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

@QuarkusTest
public class QuoteResourceTest {
    private static final String UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

    @Test
    void should_get_a_list_successfully() {
        RestAssured.given()
                .get("/quotes")
                .then()
                .statusCode(200)
                .assertThat().body("isEmpty()", Is.is(false));
    }


    @Test
    void should_create_successfully() {
        var quote = new QuoteDto().quoteId(null).orderId(UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbc1")).discountInUSD(10D).totalPriceWithDiscountInUSD(null).createdAt(OffsetDateTime.now());
        RestAssured.given()
                .header("Content-Type", "application/json")
                .and()
                .body(quote)
                .when()
                .post("/quotes")
                .then()
                .statusCode(201)
                .assertThat().body("quoteId", MatchesPattern.matchesPattern(UUID_REGEX));

    }

    @Test
    void should_create_and_fail() {
        var quote = new QuoteDto().quoteId(null).orderId(UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb9")).discountInUSD(10D).totalPriceWithDiscountInUSD(null).createdAt(OffsetDateTime.now());
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

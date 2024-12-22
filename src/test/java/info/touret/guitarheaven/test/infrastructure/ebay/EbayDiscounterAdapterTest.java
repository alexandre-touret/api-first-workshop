package info.touret.guitarheaven.test.infrastructure.ebay;

import info.touret.guitarheaven.infrastructure.ebay.EbayDiscounterAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class EbayDiscounterAdapterTest {

    @Inject
    EbayDiscounterAdapter ebayDiscounterAdapter;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_find_a_price_for_Fender_Stratocaster() {
        var fenderStratocaster = ebayDiscounterAdapter.getAverageGuitarPrice("Fender Stratocaster");
        assertFalse(fenderStratocaster.isEmpty());
        assertEquals(1249.995, fenderStratocaster.getAsDouble());
    }

    @Test
    void should_not_find_a_price_for_Gibson_ES335() {
        assertTrue(ebayDiscounterAdapter.getAverageGuitarPrice("Gibson ES 335").isEmpty());
    }
}

package info.touret.guitarheaven.test.infrastructure.kafka;

import info.touret.guitarheaven.infrastructure.kafka.SupplyChainAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class SupplyChainAdapterTest {

    @Inject
    SupplyChainAdapter supplyChainAdapter;

    @Test
    void requestForAdditionalGuitars() {
        assertNotNull(supplyChainAdapter.requestForAdditionalGuitars("Fender Stratocaster", 10));
    }
}

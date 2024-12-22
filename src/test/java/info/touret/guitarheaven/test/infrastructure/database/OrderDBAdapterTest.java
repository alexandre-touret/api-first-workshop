package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.infrastructure.database.adapter.OrderDBAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class OrderDBAdapterTest {

    @Inject
    OrderDBAdapter orderDBAdapter;

    @Test
    void should_find_one_order() {
        assertEquals(1, orderDBAdapter.findAllOrders().size());
    }

    @Test
    void should_save_one_order_successfully() {
        Order order = new Order(null, List.of(UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1")), 10, OffsetDateTime.now());
        assertDoesNotThrow(() -> orderDBAdapter.saveOrder(order));
    }
}

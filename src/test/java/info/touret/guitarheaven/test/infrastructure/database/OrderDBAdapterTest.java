package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.infrastructure.database.adapter.OrderDBAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static info.touret.guitarheaven.domain.model.Guitar.TYPE.ELECTRIC;
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
        Order order = new Order(null, List.of(new Guitar(999L, "ES 335", ELECTRIC, 2500d, 10)), 10, 2500d, OffsetDateTime.now());
        assertDoesNotThrow(() -> orderDBAdapter.saveOrder(order));
    }
}

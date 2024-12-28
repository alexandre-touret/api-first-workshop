package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.infrastructure.database.adapter.OrderRequestDBAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class OrderRequestDBAdapterTest {

    @Inject
    OrderRequestDBAdapter orderRequestDBAdapter;

    @Test
    void should_find_orders() {
        assertFalse(orderRequestDBAdapter.findAllOrders().isEmpty());
    }

    @Test
    void should_save_one_order_successfully() {
        OrderRequest orderRequest = new OrderRequest(null, List.of(UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1")), 10, OffsetDateTime.now());
        assertDoesNotThrow(() -> orderRequestDBAdapter.saveOrder(orderRequest));
    }

    @Test
    void void_should_get_order_successfully() {
        var uuid = UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbba1");
        var orderByUUID = orderRequestDBAdapter.findOrderByUUID(uuid);
        assertNotNull(orderByUUID);
        assertTrue(orderByUUID.isPresent());
        var order = orderByUUID.get();
        assertEquals(uuid, order.orderId());
        assertFalse(order.guitarIds().isEmpty());
        assertEquals(1, order.guitarIds().size());
        assertEquals(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d684"), order.guitarIds().getFirst());
    }
}

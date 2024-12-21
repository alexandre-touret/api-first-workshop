package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.infrastructure.database.adapter.OrderDBAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class OrderDBAdapterTest {

    @Inject
    OrderDBAdapter orderDBAdapter;

    @Test
    void should_find_no_orders() {
        assertEquals(0, orderDBAdapter.findAllOrders().size());
    }

    @Test
    void saveOrder() {
    }
}

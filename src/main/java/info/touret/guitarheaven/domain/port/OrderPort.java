package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPort {
    List<Order> findAllOrders();

    void saveOrder(Order order);

    Optional<Order> findOrderByUUID(UUID id);
}

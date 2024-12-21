package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Order;

import java.util.List;

public interface OrderPort {
    List<Order> findAllOrders();

    void saveOrder(Order order);

}

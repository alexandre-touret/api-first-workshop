package info.touret.guitarheaven.infrastructure.database.adapter;

import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import info.touret.guitarheaven.infrastructure.database.mapper.OrderEntityMapper;
import info.touret.guitarheaven.infrastructure.database.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class OrderDBAdapter implements OrderPort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public List<Order> findAllOrders() {
        return orderEntityMapper.toOrders(orderRepository.listAll());
    }

    @Inject
    public OrderDBAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.persist(orderEntityMapper.toOrderEntity(order));
    }
}

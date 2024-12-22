package info.touret.guitarheaven.infrastructure.database.adapter;

import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import info.touret.guitarheaven.infrastructure.database.mapper.OrderEntityMapper;
import info.touret.guitarheaven.infrastructure.database.repository.GuitarRepository;
import info.touret.guitarheaven.infrastructure.database.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class OrderDBAdapter implements OrderPort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final GuitarRepository guitarRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderEntityMapper.toOrders(orderRepository.listAll());
    }

    @Inject
    public OrderDBAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper, GuitarRepository guitarRepository) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.guitarRepository = guitarRepository;
    }

    @Transactional
    @Override
    public void saveOrder(Order order) {
        var orderEntity = orderEntityMapper.toOrderEntity(order);
        orderEntity.setGuitars(Set.copyOf(guitarRepository.findInIds(orderEntity.getGuitars().stream().mapToLong(GuitarEntity::getId).boxed().toList())));
        orderRepository.persist(orderEntity);
    }

    @Override
    public Optional<Order> findOrderByUUID(UUID id) {
        var orderEntities = orderRepository.list("orderId = ?1", id);
        if (orderEntities.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(orderEntityMapper.toOrder(orderEntities.getFirst()));
        }
    }
}

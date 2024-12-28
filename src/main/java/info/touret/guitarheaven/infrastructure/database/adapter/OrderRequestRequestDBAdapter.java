package info.touret.guitarheaven.infrastructure.database.adapter;

import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.port.OrderRequestPort;
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
public class OrderRequestRequestDBAdapter implements OrderRequestPort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final GuitarRepository guitarRepository;

    @Override
    public List<OrderRequest> findAllOrders() {
        return orderEntityMapper.toOrders(orderRepository.listAll());
    }

    @Inject
    public OrderRequestRequestDBAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper, GuitarRepository guitarRepository) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.guitarRepository = guitarRepository;
    }

    @Transactional
    @Override
    public void saveOrder(OrderRequest orderRequest) {
        var orderEntity = orderEntityMapper.toOrderEntity(orderRequest);
        var guitarEntityList = guitarRepository.findGuitarsyUUIDs(orderEntity.getGuitars().stream().map(GuitarEntity::getGuitarId).toList());
        orderEntity.setGuitars(Set.copyOf(guitarEntityList));
        orderRepository.persist(orderEntity);
    }

    @Override
    public Optional<OrderRequest> findOrderByUUID(UUID id) {
        return orderRepository.findByUUID(id).map(orderEntityMapper::toOrder);
    }
}

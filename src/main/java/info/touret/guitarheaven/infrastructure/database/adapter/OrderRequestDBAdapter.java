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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class OrderRequestDBAdapter implements OrderRequestPort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final GuitarRepository guitarRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRequestDBAdapter.class);

    @Override
    public List<OrderRequest> findAllOrders() {
        return orderEntityMapper.toOrders(orderRepository.listAll());
    }

    @Inject
    public OrderRequestDBAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper, GuitarRepository guitarRepository) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.guitarRepository = guitarRepository;
    }

    @Transactional
    @Override
    public void saveOrder(OrderRequest orderRequest) {
        LOGGER.info("Saving Order Request {}",orderRequest.orderId());
        var orderEntity = orderEntityMapper.toOrderEntity(orderRequest);
        var guitarUuids = orderEntity.getGuitars().stream().map(GuitarEntity::getGuitarId).toList();
        var guitarEntityList = guitarRepository.findGuitarsByUUIDs(guitarUuids);
        LOGGER.info("Found {} guitars for order {}", guitarEntityList.size(),orderRequest.orderId());
        orderEntity.setGuitars(Set.copyOf(guitarEntityList));
        orderRepository.persist(orderEntity);
        LOGGER.info("Saved Order Request {} for guitars {}", orderEntity.getOrderId(), guitarUuids);
    }

    @Override
    public Optional<OrderRequest> findOrderByUUID(UUID id) {
        return orderRepository.findByUUID(id).map(orderEntityMapper::toOrder);
    }
}

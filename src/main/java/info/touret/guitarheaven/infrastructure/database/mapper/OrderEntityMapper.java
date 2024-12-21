package info.touret.guitarheaven.infrastructure.database.mapper;

import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.infrastructure.database.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderEntityMapper {
    Order toOrder(OrderEntity entity);

    OrderEntity toOrderEntity(Order order);

    List<OrderEntity> toOrderEntities(List<Order> orders);

    List<Order> toOrders(List<OrderEntity> orderEntities);
}

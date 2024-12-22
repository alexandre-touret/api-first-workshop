package info.touret.guitarheaven.infrastructure.database.mapper;

import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import info.touret.guitarheaven.infrastructure.database.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderEntityMapper {

    default Order toOrder(OrderEntity entity) {
        return new Order(entity.getOrderId(), entity.getGuitars().stream().map(GuitarEntity::getGuitarId).toList(), entity.getDiscountRequested(), entity.getCreatedAt());
    }

    default OrderEntity toOrderEntity(Order order) {
        return new OrderEntity(null, order.guitarIds().stream().map(uuid -> {
            GuitarEntity guitar = new GuitarEntity();
            guitar.setGuitarId(uuid);
            return guitar;
        }).collect(Collectors.toSet()), order.orderId(), order.discountRequested(), null, order.createdAt(), null);
    }

    List<OrderEntity> toOrderEntities(List<Order> orders);

    List<Order> toOrders(List<OrderEntity> orderEntities);
}

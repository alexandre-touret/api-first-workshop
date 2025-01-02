package info.touret.guitarheaven.infrastructure.database.mapper;

import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import info.touret.guitarheaven.infrastructure.database.entity.OrderRequestEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderEntityMapper {

    default OrderRequest toOrder(OrderRequestEntity entity) {
        return new OrderRequest(entity.getOrderId(), entity.getGuitars().stream().map(GuitarEntity::getGuitarId).toList(), entity.getDiscountRequestedInUSD(), entity.getCreatedAt());
    }

    default OrderRequestEntity toOrderEntity(OrderRequest orderRequest) {
        return new OrderRequestEntity(null, orderRequest.guitarIds().stream().map(uuid -> {
            GuitarEntity guitar = new GuitarEntity();
            guitar.setGuitarId(uuid);
            return guitar;
        }).collect(Collectors.toSet()), orderRequest.orderId(), orderRequest.discountRequestedInUSD(),  orderRequest.createdAt(), null);
    }

    List<OrderRequest> toOrders(List<OrderRequestEntity> orderEntities);
}

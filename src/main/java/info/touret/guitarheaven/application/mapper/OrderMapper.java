package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.application.dto.OrderDto;
import info.touret.guitarheaven.domain.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = GuitarMapper.class)
public interface OrderMapper {

    Order toOrder(OrderDto order);

    OrderDto toOrderDto(Order order);

    List<OrderDto> toOrderDtoList(List<Order> orders);
}

package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.application.generated.model.OrderDto;
import info.touret.guitarheaven.domain.model.OrderRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = GuitarMapper.class)
public interface OrderRequestMapper {

    OrderRequest toOrder(OrderDto order);

    OrderDto toOrderDto(OrderRequest orderRequest);

    List<OrderDto> toOrderDtoList(List<OrderRequest> orderRequests);
}

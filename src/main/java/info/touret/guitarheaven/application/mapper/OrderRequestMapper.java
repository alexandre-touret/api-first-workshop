package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.application.generated.model.*;
import info.touret.guitarheaven.domain.model.OrderRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = GuitarMapper.class)
public interface OrderRequestMapper {

    OrderRequest toOrder(OrderRequestDto order);

    OrderRequestDto toOrderDto(OrderRequest orderRequest);

    List<OrderRequestDto> toOrderDtoList(List<OrderRequest> orderRequests);
}

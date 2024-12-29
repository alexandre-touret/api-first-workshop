package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.generated.model.OrderDto;
import info.touret.guitarheaven.application.generated.resource.OrdersApi;
import info.touret.guitarheaven.application.mapper.OrderRequestMapper;
import info.touret.guitarheaven.domain.service.OrderRequestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class OrderRequestResource implements OrdersApi {

    private final OrderRequestService orderRequestService;
    private final OrderRequestMapper orderRequestMapper;

    public OrderRequestResource(OrderRequestService orderRequestService, OrderRequestMapper orderRequestMapper) {
        this.orderRequestService = orderRequestService;
        this.orderRequestMapper = orderRequestMapper;
    }
    @Override
    public Response create(OrderDto order) {
        return Response.ok(Map.of("orderId", orderRequestService.create(orderRequestMapper.toOrder(order)))).build();
    }

    @Override
    public Response getAllOrders() {
        return Response.ok(orderRequestMapper.toOrderDtoList(orderRequestService.findAllOrders())).build();
    }

    @Override
    public Response getOrder( UUID orderId) {
        return Response.ok(orderRequestMapper.toOrderDto(orderRequestService.findByUUID(orderId).orElseThrow(
                () -> new WebApplicationException(Response.Status.NOT_FOUND)))).build();
    }
}

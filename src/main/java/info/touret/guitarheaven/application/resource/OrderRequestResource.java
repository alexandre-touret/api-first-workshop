package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.generated.model.*;
import info.touret.guitarheaven.application.generated.resource.OrdersRequestsApi;
import info.touret.guitarheaven.application.mapper.OrderRequestMapper;
import info.touret.guitarheaven.domain.service.OrderRequestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
@Path("/orders-requests")
public class OrderRequestResource implements OrdersRequestsApi{

    private final OrderRequestService orderRequestService;
    private final OrderRequestMapper orderRequestMapper;

    public OrderRequestResource(OrderRequestService orderRequestService, OrderRequestMapper orderRequestMapper) {
        this.orderRequestService = orderRequestService;
        this.orderRequestMapper = orderRequestMapper;
    }

    @Override
    public Response create(OrderRequestDto order) {
        return Response.status(201).entity(Map.of("orderId", orderRequestService.create(orderRequestMapper.toOrder(order)))).build();
    }

    @Override
    public Response getAllOrders() {
        return Response.ok(orderRequestMapper.toOrderDtoList(orderRequestService.findAllOrders())).build();
    }

    @Override
    public Response getOrder(UUID orderId) {
        return Response.ok(orderRequestMapper.toOrderDto(orderRequestService.findByUUID(orderId).orElseThrow(
                () -> new WebApplicationException(Response.Status.NOT_FOUND)))).build();
    }
}

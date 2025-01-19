package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.dto.OrderRequestDto;
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
public class OrderRequestResource {

    private final OrderRequestService orderRequestService;
    private final OrderRequestMapper orderRequestMapper;

    public OrderRequestResource(OrderRequestService orderRequestService, OrderRequestMapper orderRequestMapper) {
        this.orderRequestService = orderRequestService;
        this.orderRequestMapper = orderRequestMapper;
    }

    @Operation(summary = "Creates an order")
    @APIResponse(responseCode = "201", description = "Order creation successful")
    @APIResponse(responseCode = "400", description = "The request is invalid (probably the guitars IDs)")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @ResponseStatus(201)
    @POST
    public Map<String, UUID> create(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = OrderRequestDto.class))) OrderRequestDto order) {
        return Map.of("orderId", orderRequestService.create(orderRequestMapper.toOrder(order)));
    }

    @Operation(summary = "Gets all orders")
    @APIResponse(responseCode = "200", description = "Success")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @GET
    public List<OrderRequestDto> getAllOrders() {
        return orderRequestMapper.toOrderDtoList(orderRequestService.findAllOrders());
    }

    @Operation(summary = "Gets an order")
    @APIResponse(responseCode = "200", description = "Success")
    @APIResponse(responseCode = "400", description = "The request is invalid (probably the guitars IDs)")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @GET()
    @Path("{orderId}")
    public OrderRequestDto getOrder(@NotNull UUID orderId) {
        return orderRequestMapper.toOrderDto(orderRequestService.findByUUID(orderId).orElseThrow(
                () -> new WebApplicationException("Order "+orderId+ "not found",Response.Status.NOT_FOUND)));
    }
}

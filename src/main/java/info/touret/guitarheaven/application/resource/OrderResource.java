package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.dto.OrderDto;
import info.touret.guitarheaven.application.mapper.OrderMapper;
import info.touret.guitarheaven.domain.service.OrderService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Path("/orders")
public class OrderResource {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderResource(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @ResponseStatus(201)
    @POST
    public String create(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = OrderDto.class))) OrderDto order) {
        return orderService.order(orderMapper.toOrder(order)).toString();
    }


    @GET
    public List<OrderDto> getAllOrders() {
        return orderMapper.toOrderDtoList(orderService.findAllOrders());
    }

    // TODO https://github.com/quarkusio/quarkus/wiki/Migration-Guide-3.9
    @GET()
    @Path("{orderId}")
    public OrderDto getOrder(String orderId) {
        return orderMapper.toOrderDto(orderService.findById(UUID.fromString(orderId)).orElseThrow(
                () -> new WebApplicationException(Response.Status.NOT_FOUND)));
    }
}

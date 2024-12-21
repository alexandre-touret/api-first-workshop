package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import info.touret.guitarheaven.domain.port.SupplyChainPort;
import jakarta.inject.Inject;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class OrderService {

    public static final int SUPPLY_CHAIN_THRESHOLD = 5;
    private final GuitarService guitarService;
    private final DiscountService discountService;
    private final SupplyChainPort supplyChainPort;
    private final OrderPort orderPort;

    @Inject
    public OrderService(GuitarService guitarService, DiscountService discountService, SupplyChainPort supplyChainPort, OrderPort orderPort) {
        this.guitarService = guitarService;
        this.discountService = discountService;
        this.supplyChainPort = supplyChainPort;
        this.orderPort = orderPort;
    }

    public String order(Order order) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByIds(order.guitars().stream().mapToLong(Guitar::id).boxed().toList());
        if (relatedGuitars.isEmpty()) {
            throw new GuitarOrderException("Invalid Guitar List");
        } else {
            double totalPrice = relatedGuitars.stream().mapToDouble(Guitar::price).sum();
            // if the requested discount is below the market, we only apply it
            double discount = discountService.getTotalDiscount(relatedGuitars);
            if (discount > order.discountRequested()) {
                discount = order.discountRequested();
            }
            // ask for suppliers for furnitures
            relatedGuitars.forEach(this::CheckAndSupplyForNewFurniture);
            Order finalOrder = new Order(UUID.randomUUID(), relatedGuitars, discount, totalPrice, OffsetDateTime.now());
            orderPort.saveOrder(finalOrder);
            return finalOrder.orderId().toString();
        }
    }

    private void CheckAndSupplyForNewFurniture(Guitar guitar) {
        if (guitar.stock() < SUPPLY_CHAIN_THRESHOLD) {
            supplyChainPort.requestForAdditionalGuitars(guitar.name(), SUPPLY_CHAIN_THRESHOLD - guitar.stock());
        }
    }
}

package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import info.touret.guitarheaven.domain.port.SupplyChainPort;
import jakarta.inject.Inject;

import java.util.Date;
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
        if (!guitarService.findAllGuitars().containsAll(order.guitars())) {
            throw new GuitarOrderException("Invalid Guitar List");
        } else {
            var guitars = guitarService.findAllGuitars().stream().filter(order.guitars()::contains).toList();
            double totalPrice = guitars.stream().mapToDouble(Guitar::price).sum();
            // if the requested discount is below the market, we only apply it
            double discount = discountService.getTotalDiscount(guitars);
            if (discount > order.discountRequested()) {
                discount = order.discountRequested();
            }
            // ask for suppliers for furnitures
            guitars.forEach(this::CheckAndSupplyForNewFurniture);
            Order finalOrder = new Order(UUID.randomUUID(), guitars, discount, totalPrice, new Date());
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

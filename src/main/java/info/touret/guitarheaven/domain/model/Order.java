package info.touret.guitarheaven.domain.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record Order(UUID orderId, List<Guitar> guitars, double discountRequested, double totalPrice, Date createdAt) {
}

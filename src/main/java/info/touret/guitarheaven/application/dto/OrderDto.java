package info.touret.guitarheaven.application.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDto(UUID orderId, List<UUID> guitarIds, double discountRequested, OffsetDateTime createdAt) {
}

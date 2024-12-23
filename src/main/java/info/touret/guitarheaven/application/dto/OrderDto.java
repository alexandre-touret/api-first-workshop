package info.touret.guitarheaven.application.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDto(UUID orderId, @NotEmpty List<UUID> guitarIds, double discountRequested, OffsetDateTime createdAt) {
}

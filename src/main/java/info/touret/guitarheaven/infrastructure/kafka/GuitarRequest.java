package info.touret.guitarheaven.infrastructure.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record GuitarRequest(UUID requestId, String guitarName, int quantity) {
    @JsonCreator
    public GuitarRequest(@JsonProperty("requestId") UUID requestId, @JsonProperty("guitarName") String guitarName, @JsonProperty("quantity") int quantity) {
        this.requestId = requestId;
        this.guitarName = guitarName;
        this.quantity = quantity;
    }
}

package info.touret.guitarheaven.infrastructure.kafka;

import info.touret.guitarheaven.domain.port.SupplyChainPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class SupplyChainAdapter implements SupplyChainPort {

    private final KafkaClient kafkaClient;

    @Inject
    public SupplyChainAdapter(KafkaClient kafkaClient) {
        this.kafkaClient = kafkaClient;
    }

    @Override
    public String requestForAdditionalGuitars(String guitarName, int quantity) {
        var guitarRequest = new GuitarRequest(UUID.randomUUID(), guitarName, quantity);
        kafkaClient.requestForNewGuitars(guitarRequest);
        return guitarRequest.requestId().toString();
    }
}

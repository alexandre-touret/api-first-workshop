package info.touret.guitarheaven.infrastructure.kafka;

import info.touret.guitarheaven.domain.port.SupplyChainPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@ApplicationScoped
public class SupplyChainAdapter implements SupplyChainPort {

    private final KafkaClient kafkaClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyChainAdapter.class);

    @Inject
    public SupplyChainAdapter(KafkaClient kafkaClient) {
        this.kafkaClient = kafkaClient;
    }

    @Override
    public String requestForAdditionalGuitars(String guitarName, int quantity) {
        LOGGER.info("Broadcasting request to kafka of {} {} guitars", quantity, guitarName);
        if (guitarName == null || guitarName.isEmpty() || quantity <= 0) {
            throw new IllegalArgumentException("Guitar name cannot be null or empty");
        }
        var guitarRequest = new GuitarRequest(UUID.randomUUID(), guitarName, quantity);
        kafkaClient.requestForNewGuitars(guitarRequest);
        return guitarRequest.requestId().toString();
    }
}

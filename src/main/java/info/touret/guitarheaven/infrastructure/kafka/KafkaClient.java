package info.touret.guitarheaven.infrastructure.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@ApplicationScoped
public class KafkaClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaClient.class);

    @Inject
    @Channel("guitar-requests-out")
    Emitter<Record<UUID, GuitarRequest>> guitarRequestEmitter;

    /**
     * Sends message to Kafka
     *
     * @param guitarRequest : The Guitar to send to Kafka
     */
    public void requestForNewGuitars(GuitarRequest guitarRequest) {
        LOGGER.info("Sending Guitar Request to supplier : {}", guitarRequest.requestId().toString());
        guitarRequestEmitter.send(Record.of(guitarRequest.requestId(), guitarRequest));
    }

    /**
     * Fetches the kafka topic
     * <b>This method is only for testing purpose during the workshop</b>
     *
     * @param guitarRequestRecord: The Kafka record of the Guitar to send
     */
    @Incoming("guitar-requests-in")
    public void traceRequestsForNewGuitars(Record<UUID, GuitarRequest> guitarRequestRecord) {
        LOGGER.info("Received new Guitar Request: ID: {} - NAME: {} - QTY: {}", guitarRequestRecord.key(), guitarRequestRecord.value().guitarName(), guitarRequestRecord.value().quantity());
    }
}

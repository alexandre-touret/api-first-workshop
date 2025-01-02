package info.touret.guitarheaven.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

/**
 * Simple Deserializer for Kafka (Extract the content from a JSON Text file)
 */
public class GuitarRequestDeserializer implements Deserializer<GuitarRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GuitarRequest deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readerFor(GuitarRequest.class).readValue(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

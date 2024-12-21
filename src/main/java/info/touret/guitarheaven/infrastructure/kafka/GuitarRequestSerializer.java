package info.touret.guitarheaven.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class GuitarRequestSerializer implements Serializer<GuitarRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, GuitarRequest guitarRequest) {
        if (guitarRequest == null) {
            return null;
        } else {
            try {
                return objectMapper.writeValueAsBytes(guitarRequest);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

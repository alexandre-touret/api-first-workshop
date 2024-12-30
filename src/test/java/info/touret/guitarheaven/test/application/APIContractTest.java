package info.touret.guitarheaven.test.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.microcks.testcontainers.MicrocksContainer;
import io.github.microcks.testcontainers.model.TestRequest;
import io.github.microcks.testcontainers.model.TestResult;
import io.github.microcks.testcontainers.model.TestRunnerType;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class APIContractTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(APIContractTest.class);

    @ConfigProperty(name = "quarkus.http.test-port")
    int quarkusHttpPort;

    @ConfigProperty(name = "quarkus.microcks.default.http")
    String microcksContainerUrl;

    @Inject
    ObjectMapper mapper;

    @Test
    public void testOpenAPIContract() throws Exception {
        TestRequest testRequest = new TestRequest.Builder()
                .serviceId("Guitar Heaven API with Examples:1.0.1")
                .runnerType(TestRunnerType.OPEN_API_SCHEMA.name())
                .testEndpoint("http://host.testcontainers.internal:" + quarkusHttpPort)
                .build();
        TestResult testResult = MicrocksContainer.testEndpoint(microcksContainerUrl, testRequest);
        LOGGER.error(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testResult));
        assertTrue(testResult.isSuccess());
    }
}

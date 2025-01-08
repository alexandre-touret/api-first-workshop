package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.ExceptionPostProcessor;
import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import io.quarkiverse.resteasy.problem.HttpProblem;
import io.quarkiverse.resteasy.problem.postprocessing.ProblemContext;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExceptionPostProcessorTest {

    private ExceptionPostProcessor exceptionPostProcessor;

    @Mock
    HttpProblem httpProblem;

    ProblemContext problemContext;

    @BeforeEach
    void setUp() {
        exceptionPostProcessor = new ExceptionPostProcessor();
    }

    @Test
    void should_return_bad_request() {
        when(httpProblem.getDetail()).thenReturn("Problem detail");
        problemContext = ProblemContext.of(new IllegalArgumentException("test"), "/test");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), exceptionPostProcessor.apply(httpProblem, problemContext).getStatusCode());
    }

    @Test
    void should_return_expectation_failed() {
        when(httpProblem.getDetail()).thenReturn("Problem detail");
        problemContext = ProblemContext.of(new EntityNotFoundException("test"), "/test");
        assertEquals(Response.Status.EXPECTATION_FAILED.getStatusCode(), exceptionPostProcessor.apply(httpProblem, problemContext).getStatusCode());
    }


    @Test
    void should_return_default_problem() {
        when(httpProblem.getStatusCode()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        problemContext = ProblemContext.of(new NullPointerException("test"), "/test");
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), exceptionPostProcessor.apply(httpProblem, problemContext).getStatusCode());
    }
}

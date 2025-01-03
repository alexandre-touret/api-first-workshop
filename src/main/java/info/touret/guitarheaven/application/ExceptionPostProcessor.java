package info.touret.guitarheaven.application;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import io.quarkiverse.resteasy.problem.HttpProblem;
import io.quarkiverse.resteasy.problem.postprocessing.ProblemContext;
import io.quarkiverse.resteasy.problem.postprocessing.ProblemPostProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

/**
 * Quarkus RestEasy Post Processor. Maps custom exceptions to specific HTTP status codes
 */
@ApplicationScoped
public class ExceptionPostProcessor implements ProblemPostProcessor {
    @Override
    public HttpProblem apply(HttpProblem problem, ProblemContext context) {
        return switch (context.cause) {
            case IllegalArgumentException ignored ->
                    HttpProblem.builder().withDetail(problem.getDetail()).withStatus(Response.Status.BAD_REQUEST).build();
            case EntityNotFoundException ignored ->
                    HttpProblem.builder().withDetail(problem.getDetail()).withStatus(Response.Status.EXPECTATION_FAILED).build();
            default -> problem;
        };
    }
}

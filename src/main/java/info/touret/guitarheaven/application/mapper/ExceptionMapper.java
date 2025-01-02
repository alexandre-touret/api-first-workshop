package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * Maps exceptions to HTTP Response statuses
 */
public class ExceptionMapper {

    @ServerExceptionMapper
    public RestResponse<String> mapInvalidOrderException(final EntityNotFoundException exception) {
        return RestResponse.status(Response.Status.BAD_REQUEST, exception.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<String> mapIllegalArgumentException(final IllegalArgumentException exception) {
        return RestResponse.status(Response.Status.EXPECTATION_FAILED, exception.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(final Exception exception) {
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}

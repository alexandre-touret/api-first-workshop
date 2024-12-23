package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapper {

    @ServerExceptionMapper
    public RestResponse<String> mapInvalidOrderException(final EntityNotFoundException exception) {
        return RestResponse.status(Response.Status.BAD_REQUEST, exception.getMessage());
    }
}

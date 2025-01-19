package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.PaginationLinksFactory;
import info.touret.guitarheaven.application.generated.model.GuitarDto;
import info.touret.guitarheaven.application.generated.model.PageableGuitarDto;
import info.touret.guitarheaven.application.generated.resource.GuitarsApi;
import info.touret.guitarheaven.application.mapper.GuitarMapper;
import info.touret.guitarheaven.domain.service.GuitarService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Guitar API
 */
@ApplicationScoped
public class GuitarResource implements GuitarsApi {

    private final GuitarService guitarService;

    private final GuitarMapper guitarMapper;
    private final PaginationLinksFactory pageUtils;

    @Inject
    public GuitarResource(GuitarService guitarService, GuitarMapper guitarMapper, PaginationLinksFactory pageUtils) {
        this.guitarService = guitarService;
        this.guitarMapper = guitarMapper;
        this.pageUtils = pageUtils;
    }

    @Context
    private UriInfo uriInfo;

    @Override
    public Response findAllGuitarsWithPagination(Integer pageNumber, Integer pageSize) {
        return Response.ok(new PageableGuitarDto()).build();
    }

    @Override
    public Response retrieveAllGuitars() {
        return Response.ok(guitarService.findAllGuitars()).build();
    }

    @Override
    public Response createGuitar(GuitarDto guitarDto) {
        return Response.status(201).entity(Map.of("guitarId", guitarService.createGuitar(guitarMapper.toGuitar(guitarDto)))).build();
    }

    @Override
    public Response updateGuitar(UUID guitarId, GuitarDto guitarDto) {
        return Response.ok(guitarMapper.toGuitarDto(guitarService.updateGuitar(guitarMapper.toGuitar(guitarDto)))).build();
    }

    @Override
    public Response deleteGuitar(UUID guitarId) {
        var deleted = guitarService.deleteGuitarByUUID(guitarId);
        if (!deleted) {
            throw new WebApplicationException("Guitar {} not found", Response.Status.NOT_FOUND);
        }
        return Response.noContent().build();
    }


    @Override
    public Response getGuitar(UUID guitarId) {
        var guitars = guitarService.findGuitarsByGuitarIds(List.of(guitarId));
        if (guitars.isEmpty()) {
            throw new WebApplicationException("Guitar " + guitarId + " not found", Status.NOT_FOUND);
        } else {
            return Response.ok(guitarMapper.toGuitarDto(guitars.getFirst())).build();
        }
    }

}

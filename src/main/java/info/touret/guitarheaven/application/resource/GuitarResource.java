package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.PaginationLinksFactory;
import info.touret.guitarheaven.application.dto.GuitarDto;
import info.touret.guitarheaven.application.dto.PageableGuitarDto;
import info.touret.guitarheaven.application.mapper.GuitarMapper;
import info.touret.guitarheaven.domain.service.GuitarService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
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
@Path("/guitars")
public class GuitarResource {

    private final GuitarService guitarService;

    private final GuitarMapper guitarMapper;
    private final PaginationLinksFactory pageUtils;

    @Inject
    public GuitarResource(GuitarService guitarService, GuitarMapper guitarMapper, PaginationLinksFactory pageUtils) {
        this.guitarService = guitarService;
        this.guitarMapper = guitarMapper;
        this.pageUtils = pageUtils;
    }

    @Operation(summary = "Gets all guitars")
    @APIResponse(responseCode = "200", description = "Success ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @GET
    public List<GuitarDto> retrieveAllGuitars() {
        return guitarMapper.toGuitarsDto(guitarService.findAllGuitars());
    }

    @Operation(summary = "Creates a guitar")
    @APIResponse(responseCode = "201", description = "Guitar creation successful")
    @APIResponse(responseCode = "400", description = "The request is invalid ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @ResponseStatus(201)
    @POST
    public Map<String, UUID> createGuitar(GuitarDto guitarDto) {
        return Map.of("guitarId", guitarService.createGuitar(guitarMapper.toGuitar(guitarDto)));
    }

    @Operation(summary = "Updates a guitar")
    @APIResponse(responseCode = "200", description = "Guitar update successful ")
    @APIResponse(responseCode = "400", description = "The request is invalid ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{guitarId}")
    @PUT()
    public GuitarDto updateGuitar(@RestPath UUID guitarId, @RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GuitarDto.class))) GuitarDto guitarDto) {
        return guitarMapper.toGuitarDto(guitarService.updateGuitar(guitarMapper.toGuitar(guitarDto)));
    }

    @Operation(summary = "Deletes a guitar")
    @APIResponse(responseCode = "200", description = "Guitar update successful ")
    @APIResponse(responseCode = "400", description = "The request is invalid ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{guitarId}")
    @DELETE
    public void deleteGuitar(@RestPath("guitarId") @NotNull UUID guitarId) {
        guitarService.deleteGuitarByUUID(guitarId);
    }

    @Operation(summary = "Gets a guitar")
    @APIResponse(responseCode = "200", description = "Guitar update successful ")
    @APIResponse(responseCode = "400", description = "The request is invalid ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{guitarId}")
    @GET
    public GuitarDto getGuitar(@RestPath("guitarId") @NotNull UUID guitarId) {
        var guitars = guitarService.findGuitarsByGuitarIds(List.of(guitarId));
        if (guitars.isEmpty()) {
            throw new WebApplicationException("Guitar " + guitarId + " not found", Status.NOT_FOUND);
        } else {
            return guitarMapper.toGuitarDto(guitars.getFirst());
        }
    }

    @Operation(summary = "Gets all guitars and paginate the results")
    @APIResponse(responseCode = "200", description = "Success ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @GET
    @Path("/pages")
    public PageableGuitarDto findAllGuitarsWithPagination(@Context UriInfo uriInfo, @QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize) {
        var guitarsByPage = guitarService.findAllGuitarsByPage(pageNumber, pageSize);
        try {
            return new PageableGuitarDto(guitarMapper.toGuitarsDto(guitarsByPage.entities()), pageUtils.createLinksDto(uriInfo, guitarsByPage, pageSize));
        } catch (URISyntaxException | MalformedURLException e) {
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }

}

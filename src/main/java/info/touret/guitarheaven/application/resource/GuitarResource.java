package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.dto.GuitarDto;
import info.touret.guitarheaven.application.mapper.GuitarMapper;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.service.GuitarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.UUID;

/**
 * Guitar API
 */
@Path("/guitars")
public class GuitarResource {

    private final GuitarService guitarService;

    private final GuitarMapper guitarMapper;

    @Inject
    public GuitarResource(GuitarService guitarService, GuitarMapper guitarMapper) {
        this.guitarService = guitarService;
        this.guitarMapper = guitarMapper;
    }

    @GET
    public List<GuitarDto> retrieveAllGuitars() {
        return guitarMapper.toGuitarsDto(guitarService.findAllGuitars());
    }

    @ResponseStatus(201)
    @POST
    public void createGuitar(GuitarDto guitarDto) {
        guitarService.createGuitar(guitarMapper.toGuitar(guitarDto));
    }

    @Path("/{guitarId}")
    @PUT()
    public GuitarDto updateGuitar(@RestPath UUID guitarId, @RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GuitarDto.class))) GuitarDto guitarDto) {
        return guitarMapper.toGuitarDto(guitarService.updateGuitar(guitarMapper.toGuitar(guitarDto)));
    }

    @Path("/{guitarId}")
    @DELETE
    public void deleteGuitar(@RestPath("guitarId") UUID guitarId) {
        guitarService.deleteGuitarByUUID(guitarId);
    }

    @Path("/{guitarId}")
    @GET
    public GuitarDto getGuitar(@RestPath("guitarId") UUID guitarId) {
        var guitars = guitarService.findGuitarsByGuitarIds(List.of(guitarId));
        if (guitars.isEmpty()) {
            throw new WebApplicationException("Guitar " + guitarId + " not found", Response.Status.NOT_FOUND);
        } else {
            return guitarMapper.toGuitarDto(guitars.getFirst());
        }
    }

}

package info.touret.guitarheaven.resource;

import info.touret.guitarheaven.dto.GuitarDto;
import info.touret.guitarheaven.dto.GuitarsDto;
import info.touret.guitarheaven.mapper.GuitarMapper;
import info.touret.guitarheaven.service.GuitarService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;

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
    public GuitarsDto retrieveAllGuitars() {
        return guitarMapper.toGuitarsDto(guitarService.findAllGuitars());
    }


        @ResponseStatus(201)
    @POST
    public void createGuitar(GuitarDto guitarDto) {
        var guitar= guitarMapper.toGuitar(guitarDto);
        guitarService.createGuitar(guitar);
    }


    @Path("/{guitarId}")
    @PUT()
    public GuitarDto updateGuitar(@RestPath String guitarId, @RequestBody(required = true,content = @Content(mediaType =  MediaType.APPLICATION_JSON,schema = @Schema(implementation= GuitarDto.class))) GuitarDto guitarDto) {
        return guitarMapper.toGuitarDto(guitarService.updateGuitar(guitarMapper.toGuitar(guitarDto)));
    }

    @Path("/{guitarId}")
    @DELETE
    public void deleteGuitar(@RestPath long guitarId) {
        guitarService.deleteGuitar(guitarId);
    }
}

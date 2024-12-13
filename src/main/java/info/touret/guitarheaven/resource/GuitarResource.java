package info.touret.guitarheaven.resource;

import info.touret.guitarheaven.dto.GuitarDto;
import info.touret.guitarheaven.dto.GuitarsDto;
import info.touret.guitarheaven.mapper.GuitarMapper;
import info.touret.guitarheaven.service.GuitarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.PartType;
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

    @POST
    public void createGuitar(GuitarDto guitarDto) {
        guitarService.createGuitar(guitarMapper.toGuitar(guitarDto));
    }

    @Path("/{guitarId}")
    @PUT()
    public GuitarDto updateGuitar(@RestPath String guitarId, @RestForm @PartType("application/json") GuitarDto guitarDto) {
        return guitarMapper.toGuitarDto(guitarService.updateGuitar(guitarMapper.toGuitar(guitarDto)));
    }

    @Path("/{guitarId}")
    @DELETE
    public void deleteGuitar(@RestPath long guitarId) {
        guitarService.deleteGuitar(guitarId);
    }
}

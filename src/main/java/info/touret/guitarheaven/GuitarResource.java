package info.touret.guitarheaven;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

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
}

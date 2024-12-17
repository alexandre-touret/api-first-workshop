package info.touret.guitarheaven;

import info.touret.guitarheaven.domain.service.GuitarPort;
import info.touret.guitarheaven.domain.service.GuitarService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class DomainConfiguration {


    @ApplicationScoped
    @Produces
    GuitarService createGuitarService(GuitarPort guitarPort) {
        return new GuitarService(guitarPort);
    }
}

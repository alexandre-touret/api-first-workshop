package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GuitarService {

    private final GuitarPort guitarPort;

    @Inject
    public GuitarService(GuitarPort guitarPort) {
        this.guitarPort = guitarPort;
    }

    public List<Guitar> findAllGuitars() {
        return guitarPort.listAll();
    }

    public void createGuitar(Guitar guitar) {
        guitarPort.save(guitar);
    }


    public Guitar updateGuitar(Guitar guitar) {
        return guitarPort.update(guitar);
    }

    public boolean deleteGuitar(Long guitarId) {
        return guitarPort.delete(guitarId);
    }
}

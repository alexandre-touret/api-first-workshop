package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.port.GuitarPort;

import java.util.List;

/**
 * Manages Guitars
 */
public class GuitarService {

    private final GuitarPort guitarPort;

    /**
     * @param guitarPort
     */
    public GuitarService(GuitarPort guitarPort) {
        this.guitarPort = guitarPort;
    }

    /**
     * Gets all guitars
     *
     * @return The whole guitar list
     */
    public List<Guitar> findAllGuitars() {
        return guitarPort.listAll();
    }

    public List<Guitar> findGuitarsByIds(List<Long> ids) {
        return guitarPort.findGuitarsByIds(ids);
    }

    /**
     * Creates a guitar
     *
     * @param guitar
     */
    public void createGuitar(Guitar guitar) {
        guitarPort.save(guitar);
    }

    /**
     * Updates the guitar
     *
     * @param guitar
     * @return The updated guitar
     */
    public Guitar updateGuitar(Guitar guitar) {
        return guitarPort.update(guitar);
    }

    /**
     * Deletes one guitar
     *
     * @param guitarId
     * @return
     */
    public boolean deleteGuitar(Long guitarId) {
        return guitarPort.delete(guitarId);
    }
}

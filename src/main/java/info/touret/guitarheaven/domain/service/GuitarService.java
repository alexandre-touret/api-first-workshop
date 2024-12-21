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
     * @param guitarPort the Port for reaching the infrastructure
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

    /**
     * Finds all the guitars matching the IDs
     *
     * @param ids list of Guitar IDs
     * @return The list of guitars. It will be empty if no guitar is found
     */
    public List<Guitar> findGuitarsByIds(List<Long> ids) {
        System.err.println(">>>> FIND BY IDS {}" + ids);
        return guitarPort.findGuitarsByIds(ids);
    }

    /**
     * Creates a guitar
     *
     * @param guitar the Guitar to create. The ID must not be filled
     */
    public void createGuitar(Guitar guitar) {
        guitarPort.save(guitar);
    }

    /**
     * Updates the guitar
     *
     * @param guitar the guitar to update
     * @return The updated guitar
     */
    public Guitar updateGuitar(Guitar guitar) {
        return guitarPort.update(guitar);
    }

    /**
     * Deletes one guitar
     *
     * @param guitarId : The ID of the guitar to delete
     * @return true if the guitar is deleted. False instead.
     */
    public boolean deleteGuitar(Long guitarId) {
        return guitarPort.delete(guitarId);
    }
}

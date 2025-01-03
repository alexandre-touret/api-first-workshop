package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Page;
import info.touret.guitarheaven.domain.port.GuitarPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * Manages Guitars
 */
public class GuitarService {

    private final GuitarPort guitarPort;
    private static final Logger LOGGER = LoggerFactory.getLogger(GuitarService.class);

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

    public List<Guitar> findGuitarsByGuitarIds(List<UUID> guitarIds) {
        return guitarPort.findGuitarsByGuitarIds(guitarIds);
    }

    /**
     * Creates a guitar. Creates a new UUID
     *
     * @param guitar the Guitar to create. The ID must not be filled
     */
    public UUID createGuitar(Guitar guitar) {
        final var guitarId = UUID.randomUUID();
        LOGGER.info("Saving guitar {}", guitarId);
        guitar = new Guitar(guitar.id(), guitarId, guitar.name(), guitar.type(), guitar.priceInUSD(), guitar.stock());
        guitarPort.save(guitar);
        LOGGER.debug("Saved guitar {}", guitar.guitarId());
        return guitarId;
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
    public boolean deleteGuitarByUUID(UUID guitarId) {
        return guitarPort.deleteByUUID(guitarId);
    }

    public Page<Guitar> findAllGuitarsByPage(int pageNumber, int pageSize) {
        return guitarPort.findAllGuitarByPage(pageNumber, pageSize);
    }
}

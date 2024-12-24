package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Page;

import java.util.List;
import java.util.UUID;

/**
 * Guitar Domain Port
 */
public interface GuitarPort {

    /**
     * @return All guitars
     */
    List<Guitar> listAll();

    /**
     * Save a new guitar
     *
     * @param guitar
     */
    void save(Guitar guitar);

    /**
     * Update the guitar
     *
     * @param guitar
     * @return The updated guitar
     */
    Guitar update(Guitar guitar);

    /**
     * Delete guitar
     *
     * @param guitarId
     * @return <code>true</code> if the guitar is deleted. <code>false</code> instead
     */
    boolean deleteByUUID(UUID guitarId);

    /**
     * Finds Guitars by their private IDS
     *
     * @param ids the PRIMARY KEYS
     * @return The list of guitars. Empty if no guitars found
     */
    List<Guitar> findGuitarsByIds(List<Long> ids);

    /**
     * Finds Guitars by their UUID
     *
     * @param guitarIds the corresponding UUID
     * @return The list of guitars. Empty if no guitars found
     */
    List<Guitar> findGuitarsByGuitarIds(List<UUID> guitarIds);


    /**
     * Finds all guitars and paginate the results
     *
     * @param pageNumber The page (Min 0)
     * @param pageSize   the number of elements per page (Min 1)
     * @return the list of guitars with some information
     */
    Page<Guitar> findAllGuitarByPage(int pageNumber, int pageSize);
}

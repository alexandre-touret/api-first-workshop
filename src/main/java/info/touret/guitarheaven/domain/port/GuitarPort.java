package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Guitar;

import java.util.List;

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
     * @param guitar
     */
    void save(Guitar guitar);

    /**
     * Update the guitar
     * @param guitar
     * @return The updated guitar
     */
    Guitar update(Guitar guitar);

    /**
     * Delete guitar
     * @param guitarId
     * @return <code>true</code> if the guitar is deleted. <code>false</code> instead
     */
    boolean delete(Long guitarId);

}

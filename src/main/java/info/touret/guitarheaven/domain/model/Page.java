package info.touret.guitarheaven.domain.model;

import java.util.List;

/**
 * Used to paginate the results
 *
 * @param pageCount:  The number of pages
 * @param entities:   The entities
 * @param pageNumber: The page
 * @param hasNext:    Indicates if there's a next page
 * @param hasPrevious Indicates if there's a previous page
 * @param <T>         : The entity type
 */
public record Page<T>(int pageCount, List<T> entities, int pageNumber, boolean hasNext, boolean hasPrevious) {

}

package info.touret.guitarheaven.domain.model;

import java.util.List;

public record Page<T>(int pageCount, List<T> entities, int pageNumber, boolean hasNext, boolean hasPrevious) {

}

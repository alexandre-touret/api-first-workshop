package info.touret.guitarheaven.domain.model;

import java.util.List;

public class Page<T> {
    private int pageCount;
    private List<T> entities;
    private int pageNumber;
    private boolean hasNext;
    private boolean hasPrevious;

    public Page(int pageCount, List<T> entities, int pageNumber, boolean hasNext, boolean hasPrevious) {
        this.pageCount = pageCount;
        this.entities = entities;
        this.pageNumber = pageNumber;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public Page() {
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean hasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}

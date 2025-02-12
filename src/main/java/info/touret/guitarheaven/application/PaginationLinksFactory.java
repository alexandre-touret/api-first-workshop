package info.touret.guitarheaven.application;

import info.touret.guitarheaven.application.dto.LinksDto;
import info.touret.guitarheaven.domain.model.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.UriInfo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Generates pagination links in a HATEOS way
 */
@ApplicationScoped
public class PaginationLinksFactory {

    public static final String URL_FORMAT_PATTERN = "%s?pageNumber=%1$s&pageSize=%2$s";

    /**
     * Creates the Links DTO to enable pagination of results
     *
     * @param uriInfo                 The context of the current request
     * @param page                    The page data
     * @param numberOfElementsPerPage The number of elements per page
     * @param <T>                     The DTO type
     * @return All the pagination Links
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    public <T> LinksDto createLinksDto(UriInfo uriInfo, Page<T> page, int numberOfElementsPerPage) throws URISyntaxException, MalformedURLException {
        var baseUri = uriInfo.getAbsolutePath();
        var self = new URI(String.format(URL_FORMAT_PATTERN, baseUri, page.pageNumber(), page.entities().size())).toURL();
        var first = new URI(String.format(URL_FORMAT_PATTERN, baseUri, 0, numberOfElementsPerPage)).toURL();
        var prev = new URI(String.format(URL_FORMAT_PATTERN, baseUri, page.pageNumber() >= 1 ? page.pageNumber() : 0, numberOfElementsPerPage)).toURL();
        var next = new URI(String.format(URL_FORMAT_PATTERN, baseUri, page.pageNumber() >= page.pageCount() + 1 ? page.pageNumber() : page.pageNumber() + 1, numberOfElementsPerPage)).toURL();
        var last = new URI(String.format(URL_FORMAT_PATTERN, baseUri, page.pageCount() - 1, numberOfElementsPerPage)).toURL();
        return new LinksDto(self, first, prev, next, last);
    }
}

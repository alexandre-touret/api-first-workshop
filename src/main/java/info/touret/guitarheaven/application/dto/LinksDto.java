package info.touret.guitarheaven.application.dto;

import java.net.URL;

/**
 * Holds the pagination links.
 * Strongly inspired of the <a href="https://jsonapi.org/examples/#pagination">JSON API way</a>
 *
 * @param self
 * @param first
 * @param prev
 * @param next
 * @param last
 */
public record LinksDto(URL self, URL first, URL prev, URL next, URL last) {
}

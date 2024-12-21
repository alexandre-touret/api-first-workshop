package info.touret.guitarheaven.infrastructure.ebay;


import java.util.List;

public record SearchPagedCollection(
        String href,
        int total,
        int limit,
        int offset,
        List<ItemSummary> itemSummaries
) {}

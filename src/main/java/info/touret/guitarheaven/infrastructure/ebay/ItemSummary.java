package info.touret.guitarheaven.infrastructure.ebay;


public record ItemSummary(
        String itemId,
        String title,
        Price price,
        Image image
) {}

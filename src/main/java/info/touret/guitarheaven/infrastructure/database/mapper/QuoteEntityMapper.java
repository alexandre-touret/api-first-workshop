package info.touret.guitarheaven.infrastructure.database.mapper;

import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.infrastructure.database.entity.QuoteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {OrderEntityMapper.class, GuitarEntityMapper.class})
public interface QuoteEntityMapper {
    @InheritInverseConfiguration
    QuoteEntity toQuoteEntity(Quote quote);

    @Mapping(source = "order.orderId", target = "orderId")
    Quote toQuote(QuoteEntity entity);

    List<Quote> toQuotes(List<QuoteEntity> all);
}

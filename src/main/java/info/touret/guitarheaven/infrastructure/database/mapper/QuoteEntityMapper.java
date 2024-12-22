package info.touret.guitarheaven.infrastructure.database.mapper;

import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.infrastructure.database.entity.QuoteEntity;
import org.mapstruct.Mapper;

@Mapper(uses = {OrderEntityMapper.class, GuitarEntityMapper.class})
public interface QuoteEntityMapper {
    QuoteEntity toQuoteEntity(Quote quote);

    Quote toQuote(QuoteEntity entity);
}

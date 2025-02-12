package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.application.dto.QuoteDto;
import info.touret.guitarheaven.domain.model.Quote;
import org.mapstruct.Mapper;

@Mapper
public interface QuoteMapper {
    Quote fromDto(QuoteDto quoteDto);
}

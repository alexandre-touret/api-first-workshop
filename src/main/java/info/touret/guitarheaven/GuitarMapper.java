package info.touret.guitarheaven;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GuitarMapper {

    GuitarDto toGuitarDto(Guitar guitar);

    default GuitarsDto toGuitarsDto(List<Guitar> guitars) {
        return new GuitarsDto(guitars.stream().map(this::toGuitarDto).toList());
    }
}

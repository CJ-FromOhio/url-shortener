package org.tropia.urlshortener.util.mapper;

import org.mapstruct.Mapper;
import org.tropia.urlshortener.entity.Url;
import org.tropia.urlshortener.entity.dto.UrlCreateDto;

@Mapper(componentModel = "spring")
public interface UrlCreateMapper {
    UrlCreateDto map(Url url);
    Url map(UrlCreateDto urlCreateDto);
}

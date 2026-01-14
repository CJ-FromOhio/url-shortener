package org.tropia.urlshortener.util.mapper;

import org.mapstruct.Mapper;
import org.tropia.urlshortener.entity.Url;
import org.tropia.urlshortener.entity.dto.UrlReadDto;

@Mapper(componentModel = "spring")
public interface UrlReadMapper {
    UrlReadDto map(Url url);
    Url map(UrlReadDto urlReadDto);
}

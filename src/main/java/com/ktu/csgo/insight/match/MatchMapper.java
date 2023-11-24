package com.ktu.csgo.insight.match;

import com.ktu.csgo.insight.match.dtos.MatchCreateDto;
import com.ktu.csgo.insight.match.dtos.MatchEditDto;
import com.ktu.csgo.insight.match.dtos.MatchGetDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MatchMapper {

    Match toEntity(MatchCreateDto matchCreateDto);

    MatchCreateDto toDto1(Match match);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Match partialUpdate(MatchCreateDto matchCreateDto, @MappingTarget Match match);

    Match toEntity(MatchEditDto matchEditDto);

    MatchEditDto toDto2(Match match);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Match partialUpdate(MatchEditDto matchEditDto, @MappingTarget Match match);

    Match toEntity(MatchGetDto matchGetDto);

    @AfterMapping
    default void linkMatchMaps(@MappingTarget Match match) {
        match.getMatchMaps().forEach(matchMap -> matchMap.setMatch(match));
    }

    MatchGetDto toDto(Match match);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Match partialUpdate(MatchGetDto matchGetDto, @MappingTarget Match match);
}
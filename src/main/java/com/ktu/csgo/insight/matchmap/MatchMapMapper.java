package com.ktu.csgo.insight.matchmap;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MatchMapMapper {
    MatchMap toEntity(MatchMapDto matchMapDto);

    MatchMapDto toDto(MatchMap matchMap);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MatchMap partialUpdate(MatchMapDto matchMapDto, @MappingTarget MatchMap matchMap);
}
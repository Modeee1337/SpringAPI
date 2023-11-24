package com.ktu.csgo.insight.tournament;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TournamentMapper {
    Tournament toEntity(TournamentDto tournamentDto);

    TournamentDto toDto(Tournament tournament);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tournament partialUpdate(TournamentDto tournamentDto, @MappingTarget Tournament tournament);
}
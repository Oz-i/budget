package com.forbusypeople.budget.mappers;

import com.forbusypeople.budget.repositories.entities.PropertyEntity;
import com.forbusypeople.budget.repositories.entities.RoomsEntity;
import com.forbusypeople.budget.repositories.entities.UserEntity;
import com.forbusypeople.budget.services.dtos.PropertyDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {HousingMaintenanceExpensesMapper.class})
public interface PropertyMapper {

    PropertyDto fromEntityToDto(PropertyEntity entity);

    @Mapping(source = "dto.id", target = "id")
    @Mapping(target = "sold", expression = "java(false)")
    @Mapping(target = "user", expression = "java(user)")
    PropertyEntity fromDtoToEntity(PropertyDto dto,
                                   @Context UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rooms", source = "rooms")
    void updateEntityByDto(@MappingTarget PropertyEntity entity,
                           PropertyDto dto,
                           List<RoomsEntity> rooms);
}

package org.example.mappers;

import org.example.dto.UserDto;
import org.example.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper для {@link UserDto} и {@link UserEntity}
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * @param userEntity {@link UserEntity}
     * @return {@link UserDto}
     */
    UserDto toDto(UserEntity userEntity);

    /**
     * @param userDto {@link UserDto}
     * @return {@link UserEntity}
     */
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDto userDto);

    /**
     * @param userDto    {@link UserDto}
     * @param userEntity {@link UserEntity}
     * @return {@link UserEntity}
     */
    @Mapping(target = "id", ignore = true)
    UserEntity mergeToEntity(UserDto userDto, @MappingTarget UserEntity userEntity);
}

package org.example.service;

import org.example.dto.UserDto;
import org.example.entity.UserEntity;

/**
 * Сервис для {@link UserEntity} и {@link UserEntity}
 */
public interface UserService {

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link UserDto}
     */
    UserDto findById(Long id);


    /**
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    UserDto save(UserDto userDto);

    /**
     * @param id      технический идентификатор {@link UserEntity}
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    UserDto update(Long id, UserDto userDto);

    /**
     * @param id технический идентификатор {@link UserEntity}
     */
    void delete(Long id);
}

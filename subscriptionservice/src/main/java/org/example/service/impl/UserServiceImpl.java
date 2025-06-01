package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.UserEntity;
import org.example.mappers.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация {@link UserService}
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link UserDto}
     */
    @Override
    public UserDto findById(Long id) {
        return mapper.toDto(findUserEntityById(id));
    }

    /**
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        repository.findAll().stream()
                .filter(userEntity -> userEntity.getEmail().equals(userDto.getEmail()))
                .findFirst()
                .ifPresent(userEntity -> {
                    throw new IllegalArgumentException("User already exists");
                });

        final UserEntity userEntity = repository.save(mapper.toEntity(userDto));

        return mapper.toDto(userEntity);
    }

    /**
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    @Override
    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        final UserEntity userEntity = findUserEntityById(id);
        final UserEntity updatedUserEntity = mapper.mergeToEntity(userDto, userEntity);

        return mapper.toDto(updatedUserEntity);
    }

    /**
     * @param id технический идентификатор {@link UserEntity}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        final UserEntity userEntity = findUserEntityById(id);

        repository.delete(userEntity);
    }

    private UserEntity findUserEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}

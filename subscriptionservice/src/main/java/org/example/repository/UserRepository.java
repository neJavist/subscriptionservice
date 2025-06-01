package org.example.repository;

import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link UserEntity}
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

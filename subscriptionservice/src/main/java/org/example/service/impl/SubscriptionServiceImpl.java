package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.SubscriptionDto;
import org.example.entity.SubscriptionEntity;
import org.example.entity.UserEntity;
import org.example.mappers.SubscriptionMapper;
import org.example.repository.SubscriptionRepository;
import org.example.repository.UserRepository;
import org.example.service.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация {@link SubscriptionService}
 */
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper mapper;

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link SubscriptionDto}
     */
    @Override
    public List<SubscriptionDto> findAllById(Long id) {
        final UserEntity userEntity = findUserEntityById(id);

        return mapper.toDtoList(userEntity.getSubscriptions());
    }

    /**
     * @param id              технический идентификатор {@link UserEntity}
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link SubscriptionDto}
     */
    @Override
    @Transactional
    public SubscriptionDto save(Long id, SubscriptionDto subscriptionDto) {
        final UserEntity userEntity = findUserEntityById(id);

        userEntity.getSubscriptions().stream()
                .filter(subscription ->
                        subscription.getServiceName().equals(subscriptionDto.getServiceName())
                )
                .findFirst()
                .ifPresent(subscription -> {
                            throw new IllegalArgumentException("Subscription already exists");
                        }
                );

        SubscriptionEntity subscriptionEntity = mapper.toEntity(subscriptionDto);
        subscriptionEntity.setUser(userEntity);

        return mapper.toDto(subscriptionRepository.save(subscriptionEntity));
    }

    /**
     * @param userId         технический идентификатор {@link UserEntity}
     * @param subscriptionId технический идентификатор {@link SubscriptionEntity}
     */
    @Override
    @Transactional
    public void delete(Long userId, Long subscriptionId) {
        final UserEntity userEntity = findUserEntityById(userId);

        final SubscriptionEntity subscriptionEntity =
                userEntity.getSubscriptions().stream()
                        .filter(subscription -> subscription.getId().equals(subscriptionId))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        userEntity.getSubscriptions().remove(subscriptionEntity);
    }

    /**
     * @return список {@link String}
     */
    @Override
    public List<String> getTopSubscriptions() {
        return subscriptionRepository.getTopSubscriptions().stream()
                .limit(3)
                .toList();
    }

    private UserEntity findUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}

package org.example.service;

import org.example.dto.SubscriptionDto;
import org.example.entity.SubscriptionEntity;
import org.example.entity.UserEntity;

import java.util.List;

/**
 * Сервис для {@link SubscriptionDto} и {@link SubscriptionEntity}
 */
public interface SubscriptionService {

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link SubscriptionDto}
     */
    List<SubscriptionDto> findAllById(Long id);


    /**
     * @param id              технический идентификатор {@link UserEntity}
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link SubscriptionDto}
     */
    SubscriptionDto save(Long id, SubscriptionDto subscriptionDto);

    /**
     * @param userId         технический идентификатор {@link UserEntity}
     * @param subscriptionId технический идентификатор {@link SubscriptionEntity}
     */
    void delete(Long userId, Long subscriptionId);

    /**
     * @return список {@link String}
     */
    List<String> getTopSubscriptions();
}

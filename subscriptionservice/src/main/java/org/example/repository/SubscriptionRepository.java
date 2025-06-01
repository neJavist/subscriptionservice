package org.example.repository;

import org.example.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Репозиторий для {@link SubscriptionEntity}
 */
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query("SELECT s.serviceName " +
            "FROM SubscriptionEntity s " +
            "GROUP BY s.serviceName " +
            "ORDER BY COUNT(s.id) DESC")
    List<String> getTopSubscriptions();
}

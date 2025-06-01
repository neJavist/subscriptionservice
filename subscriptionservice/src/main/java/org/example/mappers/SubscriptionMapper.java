package org.example.mappers;

import org.example.dto.SubscriptionDto;
import org.example.entity.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper для {@link SubscriptionDto} и {@link SubscriptionEntity}
 */
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    /**
     * @param subscriptionEntity {@link SubscriptionEntity}
     * @return {@link SubscriptionDto}
     */
    SubscriptionDto toDto(SubscriptionEntity subscriptionEntity);


    /**
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link SubscriptionEntity}
     */
    @Mapping(target = "id", ignore = true)
    SubscriptionEntity toEntity(SubscriptionDto subscriptionDto);

    /**
     * @param subscriptionEntities список {@link SubscriptionEntity}
     * @return список {@link SubscriptionDto}
     */
    List<SubscriptionDto> toDtoList(List<SubscriptionEntity> subscriptionEntities);
}

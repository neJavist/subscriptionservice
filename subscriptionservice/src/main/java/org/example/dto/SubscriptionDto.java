package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.entity.SubscriptionEntity;

/**
 * Dto для {@link SubscriptionEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Подписка", description = "Dto для подписки")
public class SubscriptionDto {

    @Schema(description = "Уникальный идентификатор подписки")
    Long id;

    @NotNull
    @Schema(description = "Название сервиса")
    String serviceName;
}

package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.entity.UserEntity;

/**
 * Dto для {@link UserEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Пользователь", description = "Dto для пользователя")
public class UserDto {

    @Schema(description = "Уникальный идентификатор пользователя")
    Long id;

    @NotNull
    @Schema(description = "Имя пользователя")
    String name;

    @NotNull
    @Email
    @Schema(description = "Почта пользователя")
    String email;
}

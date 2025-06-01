package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.dto.SubscriptionDto;
import org.example.entity.SubscriptionEntity;
import org.example.entity.UserEntity;
import org.example.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для {@link SubscriptionEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SubscriptionController {

    private final SubscriptionService service;

    /**
     * @param id              технический идентификатор {@link UserEntity}
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link ResponseEntity<SubscriptionDto>}
     */
    @PostMapping("/{id}/subscriptions")
    @Operation(
            summary = "Создать подписку пользователя",
            description = "Создает подписку пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<SubscriptionDto> save(
            @PathVariable Long id,
            @RequestBody SubscriptionDto subscriptionDto) {
        return ResponseEntity.ok(service.save(id, subscriptionDto));
    }

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return список {@link ResponseEntity<SubscriptionDto>}
     */
    @GetMapping("/{id}/subscriptions")
    @Operation(
            summary = "Вернуть все подписки пользователя",
            description = "Возвращает все подписки пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<List<SubscriptionDto>> findAll(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAllById(id));
    }

    /**
     * @param id     технический идентификатор {@link UserEntity}
     * @param sub_id технический идентификатор {@link SubscriptionEntity}
     * @return {@link ResponseEntity<Void>}
     */
    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    @Operation(
            summary = "Удалить подписку пользователя",
            description = "Удаляет подписку пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден/подписка не найдена"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<String> delete(@PathVariable Long id, @PathVariable Long sub_id) {
        service.delete(id, sub_id);
        return ResponseEntity.ok("Подписка удалена.");
    }

    /**
     * @return список {@link ResponseEntity<String>}
     */
    @GetMapping("/subscriptions/top")
    @Operation(
            summary = "Топ 3 подписки по популярности",
            description = "Показывает топ 3 подписки по популярности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<List<String>> getTopSubscriptions() {
        return ResponseEntity.ok(service.getTopSubscriptions());
    }
}
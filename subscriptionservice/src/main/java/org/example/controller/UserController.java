package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для {@link UserEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Контроллер пользователя", description = "Контроллер для работы с пользователем")
public class UserController {

    private final UserService service;

    /**
     * @param userDto {@link UserDto}
     * @return {@link ResponseEntity<UserDto>}
     */
    @PostMapping
    @Operation(
            summary = "Создать пользователя",
            description = "создает пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(service.save(userDto));
    }

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link ResponseEntity<UserDto>}
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Получить пользователя по идентификатору",
            description = "Возвращает пользователя по указанному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param id      технический идентификатор {@link UserEntity}
     * @param userDto {@link UserDto}
     * @return {@link ResponseEntity<UserDto>}
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить информацию о пользователе",
            description = "Обновляет информацию о пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<UserDto> update(
            @PathVariable Long id,
            @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(service.update(id, userDto));
    }

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link ResponseEntity<Void>}
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

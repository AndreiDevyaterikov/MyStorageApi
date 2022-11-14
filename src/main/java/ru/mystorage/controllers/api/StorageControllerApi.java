package ru.mystorage.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.entities.Storage;
import ru.mystorage.models.StorageModel;

import java.util.List;

public interface StorageControllerApi {
    @PostMapping
    @Operation(summary = "Добавить новый склад")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Storage.class)))
                    },
                    description = "Склад успешно добавлен"
            ),
            @ApiResponse(
                    responseCode = "405",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такой склад уже существует"
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Внутренняя ошибка сервиса"
            )
    })
    Storage add(StorageModel storageModel);

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию о складе по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Storage.class)))
                    },
                    description = "Информация о складе"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такого склада не существует"
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Внутренняя ошибка сервиса"
            )
    })
    Storage get(@PathVariable Integer id);

    @GetMapping("/all")
    @Operation(summary = "Получить информацию о всех складах")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Storage.class)))
                    },
                    description = "Информация о всех складах"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Склады не найдены"
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Внутренняя ошибка сервиса"
            )
    })
    List<Storage> getAll();

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить информацию о складе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Информация о складе удалена"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такого склада не существует"
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Внутренняя ошибка сервиса"
            )
    })
    ResponseModel delete(@PathVariable Integer id);

    @PutMapping
    @Operation(summary = "Изменить информацию о складе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Storage.class)))
                    },
                    description = "Информация о складе успешно изменена"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такого склада не существует"
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Внутренняя ошибка сервиса"
            )
    })
    Storage edit(Storage storage);
}

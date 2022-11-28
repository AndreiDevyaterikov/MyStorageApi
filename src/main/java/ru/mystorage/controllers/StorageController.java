package ru.mystorage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mystorage.entities.Storage;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.StorageModel;

import java.util.List;

public interface StorageController {

    /** Эндпоинт добавления нового склада в систему
     * @param storageModel {@link StorageModel}
     * @return {@link Storage}
     * @see ResponseModel
     */
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
            )
    })
    Storage add(@RequestBody StorageModel storageModel);


    /** Эндпоинт поиска склада по id склада
     * @param id  {@link Storage#id}
     * @return {@link Storage}
     * @see ResponseModel
     */
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
            )
    })
    Storage getById(@PathVariable Integer id);

    /** Эндпоинт получения всех складов
     * @return Список -  {@link Storage}
     * @see ResponseModel
     */

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
            )
    })
    List<Storage> getAll();

    /** Эндпоинт удаления склада по {@link Storage#id}
     * @param id  {@link Storage#id}
     * @return {@link ResponseModel}
     */

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
            )
    })
    ResponseModel delete(@PathVariable Integer id);

    /** Эндпоинт изменения данных о складе
     * @param storage сущность с {@link Storage#id} для поиска склада по id
     *                и {@link Storage#name} для установки нового наименования
     *
     * @return {@link Storage}
     * @see ResponseModel
     */
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
            )
    })
    Storage edit(Storage storage);
}

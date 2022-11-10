package ru.mystorage.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.Storage;

public interface StorageControllerApi {
    @PostMapping
    @Operation(summary = "Добавить новый склад")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
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
    ResponseModel add(Storage storage);
}

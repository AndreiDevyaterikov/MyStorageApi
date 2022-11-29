package ru.mystorage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptModel;

public interface DocumentController {

    /**
     * Эндпоинт создания нового поступления
     *
     * @param receiptModel модель для создания нового поступления {@link ReceiptModel}
     * @return {@link ReceiptModel}
     */
    @PostMapping("/receipt")
    @Operation(summary = "Создать новое поступление")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ReceiptModel.class)))
                    },
                    description = "Поступление успешно создано"
            )
    })
    ReceiptModel addNewReceipt(@RequestBody ReceiptModel receiptModel);

    /**
     * Эндпоинт создания новой продажи
     *
     * @param receiptModel модель для создания новой продажи {@link ReceiptModel}
     * @return {@link ReceiptModel}
     */
    @PostMapping("/sale")
    @Operation(summary = "Создать новую продажу")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ReceiptModel.class)))
                    },
                    description = "Продажа успешно создана"
            )
    })
    ReceiptModel addNewSale(@RequestBody ReceiptModel receiptModel);

    /**
     * Эндпоинт создания нового передвижения товара(ов) между складами
     *
     * @param movingBetweenStoragesModel модель для создания нового передвижения {@link MovingBetweenStoragesModel}
     * @return {@link ReceiptModel}
     */
    @PostMapping("/moveToStorage")
    @Operation(summary = "Переместить товары с одного склада на другой")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation =
                                            MovingBetweenStoragesModel.class)))
                    },
                    description = "Товары успешно пемещены"
            )
    })
    MovingBetweenStoragesModel addNewMoving(@RequestBody MovingBetweenStoragesModel movingBetweenStoragesModel);
}

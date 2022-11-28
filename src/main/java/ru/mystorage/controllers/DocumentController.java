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
import ru.mystorage.models.ReceiptOrSaleModel;

public interface DocumentController {

    /**
     * Эндпоинт создания нового поступления
     *
     * @param receiptOrSaleModel модель для создания нового поступления {@link ReceiptOrSaleModel}
     * @return {@link ReceiptOrSaleModel}
     */
    @PostMapping("/receipt")
    @Operation(summary = "Создать новое поступление")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ReceiptOrSaleModel.class)))
                    },
                    description = "Поступление успешно создано"
            )
    })
    ReceiptOrSaleModel addNewReceipt(@RequestBody ReceiptOrSaleModel receiptOrSaleModel);

    /**
     * Эндпоинт создания новой продажи
     *
     * @param receiptOrSaleModel модель для создания новой продажи {@link ReceiptOrSaleModel}
     * @return {@link ReceiptOrSaleModel}
     */
    @PostMapping("/sale")
    @Operation(summary = "Создать новую продажу")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ReceiptOrSaleModel.class)))
                    },
                    description = "Продажа успешно создана"
            )
    })
    ReceiptOrSaleModel addNewSale(@RequestBody ReceiptOrSaleModel receiptOrSaleModel);

    /**
     * Эндпоинт создания нового передвижения товара(ов) между складами
     *
     * @param movingBetweenStoragesModel модель для создания нового передвижения {@link MovingBetweenStoragesModel}
     * @return {@link ReceiptOrSaleModel}
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

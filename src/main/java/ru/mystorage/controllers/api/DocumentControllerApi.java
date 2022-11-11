package ru.mystorage.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mystorage.models.ReceiptOrSaleModel;
import ru.mystorage.models.ResponseModel;

public interface DocumentControllerApi {
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
    ReceiptOrSaleModel addNewReceipt(ReceiptOrSaleModel receiptOrSaleModel);

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
    ReceiptOrSaleModel addNewSale(ReceiptOrSaleModel receiptOrSaleModel);
}

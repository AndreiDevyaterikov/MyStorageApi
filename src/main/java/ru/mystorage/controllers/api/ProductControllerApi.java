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
import ru.mystorage.entities.Product;
import ru.mystorage.entities.Storage;
import ru.mystorage.models.ProductModelWithStorage;
import ru.mystorage.models.ResponseModel;

import java.util.List;

public interface ProductControllerApi {

    @PostMapping
    @Operation(summary = "Добавить новый новар")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    },
                    description = "Товар успешно добавлен"
            ),
            @ApiResponse(
                    responseCode = "405",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такой товар уже существует"
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
    Product add(ProductModelWithStorage productModel);

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию о товаре по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    },
                    description = "Информация о товаре"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такого товара не существует"
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
    Product get(@PathVariable Integer id);

    @GetMapping("/all")
    @Operation(summary = "Получить информацию о всех товарах")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    },
                    description = "Информация о всех товарах"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Товары не найдены"
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
    List<Product> getAll();

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить информацию о товаре")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Информация о товаре удалена"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такого товара не существует"
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
    @Operation(summary = "Изменить информацию о товаре")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Storage.class)))
                    },
                    description = "Информация о товаре успешно изменена"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class)))
                    },
                    description = "Такого товара не существует"
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
    Product edit(Product product);
}

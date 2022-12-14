package ru.mystorage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mystorage.entities.Product;
import ru.mystorage.entities.Storage;
import ru.mystorage.models.ProductModelWithStorage;
import ru.mystorage.models.ResponseModel;

import java.util.List;

@Tag(name = "Product Controller", description = "Контроллер для взаимодействия с сущностью \"Товар\"")
public interface ProductController {

    /**
     * Эндпоинт добавления нового товара
     *
     * @param productModel {@link ProductModelWithStorage}
     * @return {@link Product}
     * @see ResponseModel
     */
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
    Product add(@RequestBody ProductModelWithStorage productModel);

    /**
     * Эндпоинт получения данных о товаре по id
     *
     * @param id {@link Product#id}
     * @return {@link Product}
     * @see ResponseModel
     */

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
            )
    })
    Product get(@PathVariable Integer id);

    /**
     * Эндпоит получения всех товаров
     *
     * @return список - {@link Product}
     */
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
            )
    })
    List<Product> getAll();

    /**
     * Эндпоинт удаления данных о товаре по id
     *
     * @param id {@link Product#id}
     * @return результат выполнения - {@link ResponseModel}
     */
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
            )
    })
    ResponseModel delete(@PathVariable Integer id);

    /**
     * Эндпоинт изменения данных о товаре
     *
     * @param product сущность с {@link Product#id} для поиска товара по id
     *                и остальными полями для изменения данных
     * @return измененная сущность {@link Product}
     * @see ResponseModel
     */
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
            )
    })
    Product edit(@RequestBody  Product product);
}

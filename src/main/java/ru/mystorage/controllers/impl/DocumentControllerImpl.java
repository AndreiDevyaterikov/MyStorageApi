package ru.mystorage.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.DocumentController;
import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptOrSaleModel;
import ru.mystorage.services.DocumentService;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentControllerImpl implements DocumentController {

    private final DocumentService documentService;

    @Override
    public ReceiptOrSaleModel addNewReceipt(ReceiptOrSaleModel receiptOrSaleModel) {
        return documentService.addNewReceipt(receiptOrSaleModel);
    }

    @Override
    public ReceiptOrSaleModel addNewSale(ReceiptOrSaleModel receiptOrSaleModel) {
        return documentService.addNewSale(receiptOrSaleModel);
    }

    @Override
    public MovingBetweenStoragesModel addNewMoving(MovingBetweenStoragesModel movingBetweenStoragesModel) {
        return documentService.addNewMoving(movingBetweenStoragesModel);
    }
}

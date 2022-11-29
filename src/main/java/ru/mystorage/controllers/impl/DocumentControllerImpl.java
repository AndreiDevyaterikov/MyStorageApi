package ru.mystorage.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.DocumentController;
import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptModel;
import ru.mystorage.services.DocumentService;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentControllerImpl implements DocumentController {

    private final DocumentService documentService;

    @Override
    public ReceiptModel addNewReceipt(ReceiptModel receiptModel) {
        return documentService.addNewReceipt(receiptModel);
    }

    @Override
    public ReceiptModel addNewSale(ReceiptModel receiptModel) {
        return documentService.addNewSale(receiptModel);
    }

    @Override
    public MovingBetweenStoragesModel addNewMoving(MovingBetweenStoragesModel movingBetweenStoragesModel) {
        return documentService.addNewMoving(movingBetweenStoragesModel);
    }
}

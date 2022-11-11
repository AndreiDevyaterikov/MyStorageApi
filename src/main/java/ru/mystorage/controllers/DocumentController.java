package ru.mystorage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.api.DocumentControllerApi;
import ru.mystorage.models.ReceiptOrSaleModel;
import ru.mystorage.services.IDocumentService;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController implements DocumentControllerApi {

    private final IDocumentService documentService;

    @Override
    public ReceiptOrSaleModel addNewReceipt(ReceiptOrSaleModel receiptOrSaleModel) {
        return documentService.addNewReceipt(receiptOrSaleModel);
    }

    @Override
    public ReceiptOrSaleModel addNewSale(ReceiptOrSaleModel receiptOrSaleModel) {
        return null;
    }
}

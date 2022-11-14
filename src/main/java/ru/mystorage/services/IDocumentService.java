package ru.mystorage.services;

import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptOrSaleModel;

public interface IDocumentService {
    ReceiptOrSaleModel addNewReceipt(ReceiptOrSaleModel receiptOrSaleModel);

    ReceiptOrSaleModel addNewSale(ReceiptOrSaleModel receiptOrSaleModel);

    MovingBetweenStoragesModel addNewMoving(MovingBetweenStoragesModel movingBetweenStoragesModel);
}

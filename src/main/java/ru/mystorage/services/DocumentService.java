package ru.mystorage.services;

import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptModel;

public interface DocumentService {

    ReceiptModel addNewReceipt(ReceiptModel receiptModel);

    ReceiptModel addNewSale(ReceiptModel receiptModel);

    MovingBetweenStoragesModel addNewMoving(MovingBetweenStoragesModel movingBetweenStoragesModel);
}

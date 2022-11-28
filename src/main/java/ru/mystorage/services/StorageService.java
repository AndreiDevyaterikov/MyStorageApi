package ru.mystorage.services;

import ru.mystorage.entities.Storage;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.StorageModel;

import java.util.List;

public interface StorageService {
    Storage add(StorageModel storageModel);

    Storage getById(Integer id);

    Storage getByName(String storageName);

    List<Storage> getAll();

    ResponseModel delete(Integer id);

    Storage edit(Storage storage);
}

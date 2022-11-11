package ru.mystorage.services;

import ru.mystorage.entities.Storage;
import ru.mystorage.models.ResponseModel;

import java.util.List;

public interface IStorageService {
    ResponseModel add(Storage storage);
    Storage get(Integer id);
    List<Storage> getAll();
    ResponseModel delete(Integer id);
    Storage edit(Storage storage);
}

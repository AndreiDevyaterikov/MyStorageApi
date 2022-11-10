package ru.mystorage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.Storage;
import ru.mystorage.repositories.StorageRepository;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    public ResponseModel add(Storage storage) {
        var existStorage = storageRepository.findById(storage.getId());
        if(existStorage.isPresent()) {
            throw new MyStorageException("Такой склад уже существует", 405);
        }
        storageRepository.save(storage);
        return new ResponseModel(200, "Склад успешно добавлен");
    }

}

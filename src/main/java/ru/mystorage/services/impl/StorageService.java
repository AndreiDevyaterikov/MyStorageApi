package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.entities.Storage;
import ru.mystorage.repositories.StorageRepository;
import ru.mystorage.services.IStorageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService implements IStorageService {
    private final StorageRepository storageRepository;

    @Override
    public ResponseModel add(Storage storage) {
        var existStorage = storageRepository.findById(storage.getId());
        if (existStorage.isPresent()) {
            throw new MyStorageException("Такой склад уже существует", 405);
        }
        storageRepository.save(storage);
        return new ResponseModel(200, "Склад успешно добавлен");
    }

    @Override
    public Storage get(Integer id) {
        var existStorage = storageRepository.findById(id);
        if (existStorage.isPresent()) {
            return existStorage.get();
        } else {
            throw new MyStorageException("Такого склада не существует", 404);
        }
    }

    @Override
    public Storage getByName(String storageName) {
        var existStorage = storageRepository.findByName(storageName);
        if(existStorage.isPresent()) {
            return existStorage.get();
        } else {
            throw new MyStorageException("Такого склада не существует", 404);
        }
    }

    @Override
    public List<Storage> getAll() {
        var storages = storageRepository.findAll();
        if (CollectionUtils.isEmpty(storages)) {
            throw new MyStorageException("Склады не найдены", 404);
        } else {
            return storages;
        }
    }

    @Override
    public ResponseModel delete(Integer id) {
        var storage = get(id);
        storageRepository.delete(storage);
        return new ResponseModel(200, "Информация о складе успешно удалена");
    }

    @Override
    public Storage edit(Storage storage) {
        var existStorage = get(storage.getId());
        existStorage.setName(storage.getName());
        return existStorage;
    }
}

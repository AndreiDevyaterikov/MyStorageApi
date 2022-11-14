package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.mystorage.entities.Storage;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.StorageModel;
import ru.mystorage.repositories.StorageRepository;
import ru.mystorage.services.IStorageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService implements IStorageService {
    private final StorageRepository storageRepository;

    @Override
    public Storage add(StorageModel storageModel) {
        var existStorage = storageRepository.findByName(storageModel.getStorageName());
        if (existStorage.isPresent()) {
            throw new MyStorageException("Такой склад уже существует", 405);
        }
        var storageEntity = Storage.builder()
                .name(storageModel.getStorageName())
                .build();
        storageRepository.save(storageEntity);
        return storageEntity;
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
        if (existStorage.isPresent()) {
            return existStorage.get();
        } else {
            log.info(String.format("Такого склада не существует: %s", storageName));
            throw new MyStorageException(String.format("Такого склада не существует: %s", storageName), 404);
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
        if (storage.getName() == null) {
            throw new MyStorageException("Вы не указали новое имя склада", 405);
        }
        existStorage.setName(storage.getName());
        storageRepository.save(existStorage);
        return existStorage;
    }
}

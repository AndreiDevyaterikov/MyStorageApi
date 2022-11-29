package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.mystorage.constants.Constants;
import ru.mystorage.entities.Storage;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.StorageModel;
import ru.mystorage.repositories.StorageRepository;
import ru.mystorage.services.StorageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;

    @Override
    public Storage add(StorageModel storageModel) {
        var existStorage = storageRepository.findByName(storageModel.getStorageName());
        if (existStorage.isPresent()) {
            var message = String.format(Constants.STORAGE_ALREADY_EXIST, storageModel.getStorageName());
            log.info(message);
            throw new MyStorageException(message, 405);
        }
        var storageEntity = Storage.builder()
                .name(storageModel.getStorageName())
                .build();
        storageRepository.save(storageEntity);
        return storageEntity;
    }

    @Override
    public Storage getById(Integer id) {
        var existStorage = storageRepository.findById(id);
        if (existStorage.isPresent()) {
            return existStorage.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_STORAGE_BY_ID, id);
            log.info(message);
            throw new MyStorageException(message, 404);
        }
    }

    @Override
    public Storage getByName(String storageName) {
        var existStorage = storageRepository.findByName(storageName);
        if (existStorage.isPresent()) {
            return existStorage.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_STORAGE_BY_NAME, storageName);
            log.info(message);
            throw new MyStorageException(message, 404);
        }
    }

    @Override
    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

    @Override
    public ResponseModel delete(Integer id) {
        var storage = getById(id);
        storageRepository.delete(storage);
        var message = String.format(Constants.STORAGE_HAS_BEEN_DELETED, storage.getName());
        log.info(message);
        return new ResponseModel(200, message);
    }

    @Override
    public Storage edit(Storage storage) {
        var existStorage = getById(storage.getId());
        if (storage.getName() == null) {
            throw new MyStorageException("Вы не указали новое имя склада", 405);
        }
        existStorage.setName(storage.getName());
        storageRepository.save(existStorage);
        return existStorage;
    }
}

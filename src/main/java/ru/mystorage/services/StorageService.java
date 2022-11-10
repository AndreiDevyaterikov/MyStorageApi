package ru.mystorage.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.Storage;
import ru.mystorage.repositories.StorageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final StorageRepository storageRepository;

    public ResponseModel add(Storage storage) {
        try {
            var existStorage = storageRepository.findById(storage.getId());
            if (existStorage.isPresent()) {
                throw new MyStorageException("Такой склад уже существует", 405);
            }
            storageRepository.save(storage);
            return new ResponseModel(200, "Склад успешно добавлен");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyStorageException("Внутренняя ошибка сервиса", 500);
        }
    }

    public Storage get(Integer id) {
        try {
            var existStorage = storageRepository.findById(id);
            if (existStorage.isPresent()) {
                return existStorage.get();
            } else {
                throw new MyStorageException("Такого склада не существует", 404);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyStorageException("Внутренняя ошибка сервиса", 500);
        }
    }

    public List<Storage> getAll() {
        try {
            var storages = storageRepository.findAll();
            if (CollectionUtils.isEmpty(storages)) {
                throw new MyStorageException("Склады не найдены", 404);
            } else {
                return storages;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyStorageException("Внутренняя ошибка сераиса", 500);
        }
    }

    public ResponseModel delete(Integer id) {
        var storage = get(id);
        storageRepository.delete(storage);
        return new ResponseModel(200, "Информация о складе успешно удалена");
    }

    public Storage edit(Storage storage) {
        var existStorage = get(storage.getId());
        existStorage.setName(storage.getName());
        return existStorage;
    }
}

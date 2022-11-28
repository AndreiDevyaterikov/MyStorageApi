package ru.mystorage.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.StorageController;
import ru.mystorage.entities.Storage;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.StorageModel;
import ru.mystorage.services.StorageService;

import java.util.List;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageControllerImpl implements StorageController {

    private final StorageService storageService;

    @Override
    public Storage add(StorageModel storageModel) {
        return storageService.add(storageModel);
    }

    @Override
    public Storage getById(Integer id) {
        return storageService.getById(id);
    }

    @Override
    public List<Storage> getAll() {
        return storageService.getAll();
    }

    @Override
    public ResponseModel delete(Integer id) {
        return storageService.delete(id);
    }

    @Override
    public Storage edit(Storage storage) {
        return storageService.edit(storage);
    }
}

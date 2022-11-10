package ru.mystorage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.api.StorageControllerApi;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.models.Storage;
import ru.mystorage.services.StorageService;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController implements StorageControllerApi {

    private final StorageService storageService;

    @Override
    public ResponseModel add(Storage storage) {
        return storageService.add(storage);
    }

    @Override
    public Storage get(Integer id) {
        return storageService.get(id);
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

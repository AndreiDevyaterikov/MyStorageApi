package ru.mystorage.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyStorageException extends RuntimeException {
    private final String description;
    private final Integer httpCode;
}

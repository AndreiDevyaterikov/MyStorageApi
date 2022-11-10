package ru.mystorage.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mystorage.models.ResponseModel;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AwesomeExceptionHandler {

    @ExceptionHandler(MyStorageException.class)
    public ResponseModel myExceptionHandler(MyStorageException exception, HttpServletResponse response) {
        response.setStatus(exception.getHttpCode());
        return new ResponseModel(exception.getHttpCode(),
                exception.getDescription());
    }
}

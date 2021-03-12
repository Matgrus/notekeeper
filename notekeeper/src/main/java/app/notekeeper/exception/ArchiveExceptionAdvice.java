package app.notekeeper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArchiveExceptionAdvice {

    @ExceptionHandler(IncorrectIdValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleIncorrectIdValueException(IncorrectIdValueException ex){
        return ex.getMessage();
    }
}

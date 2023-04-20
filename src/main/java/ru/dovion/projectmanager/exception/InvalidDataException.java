package ru.dovion.projectmanager.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class InvalidDataException extends RuntimeException {

    private BindingResult result;

    public InvalidDataException(BindingResult result) {
        super();
        this.setResult(result);
    }


}

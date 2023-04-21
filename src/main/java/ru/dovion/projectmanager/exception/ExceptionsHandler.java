package ru.dovion.projectmanager.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ModelAndView notFoundHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", HttpStatus.NOT_FOUND);
        modelAndView.addObject("reason", "The required object was not found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("timestamp",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidDataException.class})
    public ModelAndView dateTimeHandler(InvalidDataException ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("reason", "Not valid value");
        for (var error : ex.getResult().getFieldErrors()) {
            modelAndView.addObject("message", error.getDefaultMessage());
        }
        modelAndView.addObject("timestamp",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ModelAndView notReadableMessageHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("reason", "Can`t add a new row to database");
        modelAndView.addObject("message", "В теле запроса присвоен неверный тип данных какому-либо параметру.");
        modelAndView.addObject("timestamp",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BusinessException.class})
    public ModelAndView businessExceptionHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("reason", "Business logic exception");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("timestamp",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {SecurityException.class})
    public ModelAndView securityExceptionHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", HttpStatus.UNAUTHORIZED);
        modelAndView.addObject("reason", "Unauthorized");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("timestamp",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
        return modelAndView;
    }
}

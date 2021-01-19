package taxiApp.springapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import taxiApp.Exceptions.CarIsUsingException;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.Exceptions.NotLoginException;
import taxiApp.springapp.services.representations.ErrorMessage;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage NoEntityExceptionHandler(final NoEntityException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(CarIsUsingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage CarIsUsingExceptionHandler(final CarIsUsingException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorMessage NotLoginExceptionHandler(final NotLoginException e) {
        return new ErrorMessage(e.getMessage());
    }
}

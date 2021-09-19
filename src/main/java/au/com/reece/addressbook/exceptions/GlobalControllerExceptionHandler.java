package au.com.reece.addressbook.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ErrorPayload inputError(Throwable throwable) {
        return new ErrorPayload(HttpStatus.BAD_REQUEST.value(), ErrorType.INVALID_REQUEST_TYPE, throwable.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({IllegalStateException.class})
    @ResponseBody
    public ErrorPayload conflict(Throwable throwable) {
        return new ErrorPayload(HttpStatus.CONFLICT.value(), ErrorType.RESOURCE_EXISTS, throwable.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    public ErrorPayload resourceNotFound(Throwable throwable) {
        return new ErrorPayload(HttpStatus.NOT_FOUND.value(), ErrorType.NOT_FOUND, throwable.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorPayload badRequest(MethodArgumentNotValidException exception) {
        String constructedError = "field '" + exception.getFieldError().getField() + "' " + exception.getFieldError().getDefaultMessage();
        return new ErrorPayload(HttpStatus.BAD_REQUEST.value(), ErrorType.INVALID_REQUEST_TYPE, constructedError);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorPayload badPathVariable(ConstraintViolationException exception) {
        String invalidValue = exception.getConstraintViolations().iterator().next().getInvalidValue().toString();
        String errorMessage = exception.getConstraintViolations().iterator().next().getMessage();
        String constructedError = "value '" + invalidValue + "' is invalid. " + errorMessage;
        return new ErrorPayload(HttpStatus.BAD_REQUEST.value(), ErrorType.INVALID_REQUEST_TYPE, constructedError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ContactMismatchError.class})
    @ResponseBody
    public ErrorPayload inputError(ContactMismatchError error) {
        return new ErrorPayload(HttpStatus.BAD_REQUEST.value(), ErrorType.INVALID_REQUEST_TYPE, error.getMessage());
    }
}

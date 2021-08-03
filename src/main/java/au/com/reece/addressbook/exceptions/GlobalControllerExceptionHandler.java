package au.com.reece.addressbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,
            reason="input request error")  // 400
    @ExceptionHandler(IllegalArgumentException.class)
    public void inputError() {
        // do nothing
    }

    @ResponseStatus(value= HttpStatus.CONFLICT,
            reason="input request error")  // 409
    @ExceptionHandler(IllegalStateException.class)
    public void conflict() {
        // do nothing
    }
}

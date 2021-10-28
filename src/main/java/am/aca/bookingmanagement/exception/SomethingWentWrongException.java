package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class SomethingWentWrongException extends RuntimeException{
}
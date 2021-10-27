package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(reason = "Provided_Information_Is_Invalid", code = HttpStatus.FORBIDDEN)
public class SomethingWentWrongException extends RuntimeException{
}
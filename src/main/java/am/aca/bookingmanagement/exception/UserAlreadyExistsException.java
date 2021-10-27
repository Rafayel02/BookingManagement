package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(reason = "Provided_Email_Is_Already_Registered", code = HttpStatus.FORBIDDEN)
public class UserAlreadyExistsException extends RuntimeException {
}
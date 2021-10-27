package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(reason = "Password_Mismatch", code = HttpStatus.FORBIDDEN)
public class WrongPasswordException extends RuntimeException {
}
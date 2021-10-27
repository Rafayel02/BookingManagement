package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(reason = "User_Does_Not_Exist", code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
}
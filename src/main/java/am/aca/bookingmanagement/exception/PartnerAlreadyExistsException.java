package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(reason = "Partner_Already_Exists", code = HttpStatus.FORBIDDEN)
public class PartnerAlreadyExistsException extends RuntimeException {
}
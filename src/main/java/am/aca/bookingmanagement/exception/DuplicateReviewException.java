package am.aca.bookingmanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(reason = "Duplicate_Review", code = HttpStatus.FORBIDDEN)
public class DuplicateReviewException extends RuntimeException {
}
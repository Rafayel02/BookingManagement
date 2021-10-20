package am.aca.bookingmanagement.exception;

public class PartnerAlreadyExistsException extends RuntimeException {
    public PartnerAlreadyExistsException(final String message) {
        super(message);
    }
}

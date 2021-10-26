package am.aca.bookingmanagement.exception;

public class PartnerNotFoundException extends RuntimeException {

    public PartnerNotFoundException(final String message) {
        super(message);
    }
}
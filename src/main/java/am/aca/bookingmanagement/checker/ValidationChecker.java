package am.aca.bookingmanagement.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationChecker {

    private static final Pattern LAST_NAME_VALIDATOR = Pattern.compile("[A-Z][a-z]{2,20}$");
    private static final Pattern FIRST_NAME_VALIDATOR = Pattern.compile("[A-Z][a-z]{2,20}$");
    private static final Pattern PASSWORD_VALIDATOR =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W|])(?=\\S+$).{8,}$");
    private static final Pattern PARTNER_NAME_VALIDATOR =
            Pattern.compile("[A-Z ]{2,40}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EMAIL_VALIDATOR =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean isPasswordValid(final String password) {
        return PASSWORD_VALIDATOR.matcher(password).matches();
    }

    public boolean isEmailValid(final String email) {
        return EMAIL_VALIDATOR.matcher(email).matches();
    }

    public boolean isFirstNameValid(final String firstName) {
        return FIRST_NAME_VALIDATOR.matcher(firstName).matches();
    }

    public boolean isLastNameValid(final String lastName) {
        return LAST_NAME_VALIDATOR.matcher(lastName).matches();
    }

    public boolean isPartnerNameValid(final String partnerName) {
        return PARTNER_NAME_VALIDATOR.matcher(partnerName).matches();
    }

    public boolean isUserRegistrationValid(final String firstName,
                                           final String lastName,
                                           final String email,
                                           final String password) {
        return isFirstNameValid(firstName)
                && isLastNameValid(lastName)
                && isEmailValid(email)
                && isPasswordValid(password);
    }

    public boolean isPartnerRegistrationValid(final String name,
                                              final String email,
                                              final String password) {
        return isPartnerNameValid(name)
                && isEmailValid(email)
                && isPasswordValid(password);
    }

    public boolean isLoginValid(final String email, final String password) {
        return isEmailValid(email) && isPasswordValid(password);
    }

}
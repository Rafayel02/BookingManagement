package am.aca.bookingmanagement.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationChecker {

    private static final Pattern PASSWORD_VALIDATOR
            = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W|\\_])(?=\\S+$).{8,}$");

    private static final Pattern EMAIL_VALIDATOR =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public boolean isPasswordValid(final String password) {
        return PASSWORD_VALIDATOR.matcher(password).matches();
    }

    public boolean isEmailValid(final String email) {
        return EMAIL_VALIDATOR.matcher(email).matches();
    }


}

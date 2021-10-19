package am.aca.bookingmanagement.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationChecker {

    private static final Pattern  PASSWORD_VALIDATOR
            = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W|\\_])(?=\\S+$).{8,}$");

    public boolean isPasswordValid(final String password) {
        return PASSWORD_VALIDATOR.matcher(password).matches();
    }

}

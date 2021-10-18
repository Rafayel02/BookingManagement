package am.aca.bookingmanagement.dto.userdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginResponseDetails {
    //TODO add field and token field also for login response

    private String firstName;

    private String lastName;

    private String email;
}

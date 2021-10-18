package am.aca.bookingmanagement.dto.userdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequestDetails {
    //TODO add all login request fields, including token

    private String email;

    private String password;
}

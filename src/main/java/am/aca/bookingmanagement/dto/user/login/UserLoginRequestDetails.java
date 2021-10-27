package am.aca.bookingmanagement.dto.user.login;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequestDetails {

    private String email;

    private String password;
}
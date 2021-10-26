package am.aca.bookingmanagement.dto.user.register;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequestDetails {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
package am.aca.bookingmanagement.dto.userdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequestDetails {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}

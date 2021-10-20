package am.aca.bookingmanagement.dto.userdto.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterResponseDetails {

    private String firstName;

    private String lastName;

    private String email;

    private String token;

}

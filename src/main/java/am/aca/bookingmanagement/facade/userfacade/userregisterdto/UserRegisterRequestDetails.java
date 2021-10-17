package am.aca.bookingmanagement.facade.userfacade.userregisterdto;

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

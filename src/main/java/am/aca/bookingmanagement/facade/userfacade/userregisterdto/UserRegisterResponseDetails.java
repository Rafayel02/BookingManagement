package am.aca.bookingmanagement.facade.userfacade.userregisterdto;

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

    //TODO add token field, to return token in response after registration

}

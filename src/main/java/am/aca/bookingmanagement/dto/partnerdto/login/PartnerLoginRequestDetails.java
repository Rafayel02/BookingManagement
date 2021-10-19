package am.aca.bookingmanagement.dto.partnerdto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerLoginRequestDetails {
    //TODO add all login request fields, including token

    private String email;

    private String password;
}

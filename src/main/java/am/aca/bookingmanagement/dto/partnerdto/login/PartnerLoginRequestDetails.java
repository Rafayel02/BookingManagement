package am.aca.bookingmanagement.dto.partnerdto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerLoginRequestDetails {


    private String email;

    private String password;
}

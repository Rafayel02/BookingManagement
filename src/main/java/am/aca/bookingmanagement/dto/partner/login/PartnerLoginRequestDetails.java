package am.aca.bookingmanagement.dto.partner.login;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PartnerLoginRequestDetails {

    private String email;

    private String password;
}
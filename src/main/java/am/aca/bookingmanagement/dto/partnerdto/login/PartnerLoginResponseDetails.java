package am.aca.bookingmanagement.dto.partnerdto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerLoginResponseDetails {
    //TODO add field and token field also for login response

    private String name;

    private String email;

    private double longitude;

    private double latitude;

    private String imageUrl;

    private String address;
}

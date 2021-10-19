package am.aca.bookingmanagement.dto.partnerdto.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerRegisterResponseDetails {

    private String name;

    private String email;

    private double longitude;

    private double latitude;

    private String imageUrl;

    private String address;
}

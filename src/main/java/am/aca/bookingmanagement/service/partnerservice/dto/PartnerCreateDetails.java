package am.aca.bookingmanagement.service.partnerservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerCreateDetails {

    private String name;

    private String email;

    private String password;

    private double longitude;

    private double latitude;

    private String imageUrl;

    private String address;
}

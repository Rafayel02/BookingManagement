package am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerRegisterRequestDetails {

    private String name;

    private String email;

    private String password;

    private double longitude;

    private double latitude;

    private String imageUrl;

    private String address;

}

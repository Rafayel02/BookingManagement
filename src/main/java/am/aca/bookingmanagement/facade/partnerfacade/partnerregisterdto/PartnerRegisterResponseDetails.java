package am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto;

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

    //TODO add token field, to return token in response after registration


}

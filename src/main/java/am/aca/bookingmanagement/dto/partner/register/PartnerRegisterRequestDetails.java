package am.aca.bookingmanagement.dto.partner.register;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<String> partnerCategories;

    private List<String> partnerActivities;
}
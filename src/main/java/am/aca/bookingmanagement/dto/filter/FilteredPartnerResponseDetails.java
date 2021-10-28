package am.aca.bookingmanagement.dto.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FilteredPartnerResponseDetails {

    private Long id;

    private String name;

    private String email;

    private double longitude;

    private double latitude;

    private String address;

    private String imageUrl;

}
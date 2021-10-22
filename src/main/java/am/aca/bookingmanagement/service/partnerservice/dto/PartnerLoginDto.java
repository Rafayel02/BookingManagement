package am.aca.bookingmanagement.service.partnerservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerLoginDto {

    private String email;
    private String password;
}

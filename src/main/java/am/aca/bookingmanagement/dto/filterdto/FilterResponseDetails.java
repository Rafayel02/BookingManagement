package am.aca.bookingmanagement.dto.filterdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilterResponseDetails {

    private List<PartnerDto> partnersList;

}
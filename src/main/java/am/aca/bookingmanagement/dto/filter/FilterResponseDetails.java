package am.aca.bookingmanagement.dto.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilterResponseDetails {

    private List<FilteredPartnerResponseDetails> partnersList;

}
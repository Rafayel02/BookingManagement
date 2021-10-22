package am.aca.bookingmanagement.dto.filterdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilterRequestDetails {

    private List<String> category;

    private Integer review;
}

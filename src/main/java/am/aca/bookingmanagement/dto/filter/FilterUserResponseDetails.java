package am.aca.bookingmanagement.dto.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterUserResponseDetails {
    private String firstName;
    private String lastName;
    private String email;
}

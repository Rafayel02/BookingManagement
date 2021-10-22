package am.aca.bookingmanagement.dto.reviewdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRegisterRequestDetails {
    private String userUuid;
    private String partnerUuid;
    private String comment;
    private Integer rating;
}

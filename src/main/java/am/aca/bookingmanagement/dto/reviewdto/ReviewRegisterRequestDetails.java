package am.aca.bookingmanagement.dto.reviewdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRegisterRequestDetails {
    private Long userId;
    private Long partnerId;
    private String comment;
    private Integer rating;
}

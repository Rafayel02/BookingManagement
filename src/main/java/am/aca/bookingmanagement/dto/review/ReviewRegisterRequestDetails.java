package am.aca.bookingmanagement.dto.review;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRegisterRequestDetails {
    private Long userId;
    private Long partnerId;
    private String comment;
    private Integer rating;
}
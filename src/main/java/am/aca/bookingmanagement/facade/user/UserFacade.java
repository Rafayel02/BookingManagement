package am.aca.bookingmanagement.facade.user;

import am.aca.bookingmanagement.dto.user.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Review;

import java.util.List;

public interface UserFacade {

    UserRegisterResponseDetails register(UserRegisterRequestDetails request);

    UserLoginResponseDetails login(UserLoginRequestDetails request);

    List<Review> getAllReviews(Long id);

}

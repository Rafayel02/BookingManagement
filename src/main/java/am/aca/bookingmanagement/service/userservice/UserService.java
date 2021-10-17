package am.aca.bookingmanagement.service.userservice;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.service.userservice.dto.UserCreateDetails;

public interface UserService {
    User create(UserCreateDetails userCreateDetails);
}

package am.aca.bookingmanagement.service.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Integer> findCategoryIdByType(String type);
}
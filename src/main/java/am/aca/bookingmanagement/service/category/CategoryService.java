package am.aca.bookingmanagement.service.category;

import java.util.Optional;

public interface CategoryService {

    Optional<Integer> findCategoryIdByType(String type);
}
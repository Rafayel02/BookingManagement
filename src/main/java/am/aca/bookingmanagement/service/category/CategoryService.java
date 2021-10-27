package am.aca.bookingmanagement.service.category;

import am.aca.bookingmanagement.entity.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Integer> findCategoryIdByType(String type);

    Optional<Category> findByType(final String type);


}
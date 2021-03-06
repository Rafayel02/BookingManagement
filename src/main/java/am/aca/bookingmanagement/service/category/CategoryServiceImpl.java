package am.aca.bookingmanagement.service.category;

import am.aca.bookingmanagement.entity.Category;
import am.aca.bookingmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryService) {
        this.categoryRepository = categoryService;
    }

    @Override
    public Optional<Integer> findCategoryIdByType(final String type) {
        return categoryRepository.findCategoryIdByType(type);
    }

    @Override
    public Optional<Category> findByType(final String type) {
        return categoryRepository.findByType(type);
    }
}
package am.aca.bookingmanagement.service.category;

import org.springframework.stereotype.Service;
import am.aca.bookingmanagement.repository.CategoryRepository;

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

}
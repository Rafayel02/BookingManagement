package am.aca.bookingmanagement.service.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.repository.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilterServiceImpl implements FilterService {
    private final FilterRepository filterRepository;

    public FilterServiceImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    @Override
    public Optional<List<Partner>> findByCategory(final List<Integer> list) {
        return filterRepository.findByCategory(list);
    }

    @Override
    public Optional<List<Partner>> findAll(final FilterRequestDetails filterRequestDetails) {
        return filterRepository.findAll(filterRequestDetails);
    }

}
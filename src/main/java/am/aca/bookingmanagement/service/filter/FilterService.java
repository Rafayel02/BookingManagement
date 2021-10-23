package am.aca.bookingmanagement.service.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;
import java.util.Optional;

public interface FilterService {

    Optional<List<Partner>> findAll(FilterRequestDetails filterRequestDetails);

    Optional<List<Partner>> findByCategory(List<Integer> List);

    Optional<List<Partner>> findByReview(Integer rating);

    Optional<List<Partner>> findByCategoryAndReview(List<Integer> list, Integer rating);
}
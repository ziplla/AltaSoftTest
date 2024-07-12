package ru.ziplla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.ziplla.entity.Vacancy;
import ru.ziplla.service.VacancyService;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    /**
     * Saves a new vacancy.
     *
     * @param vacancy the vacancy to be saved
     * @return the saved vacancy
     */
    @PutMapping
    public Vacancy saveVacancy(@RequestBody Vacancy vacancy) {
        return vacancyService.saveVacancy(vacancy);
    }

    /**
     * Searches for vacancies based on provided criteria such as name, post, and city.
     *
     * @param name     the name of the vacancy to search for (optional)
     * @param post     the post of the vacancy to search for (optional)
     * @param city     the city of the vacancy to search for (optional)
     * @param page     the page number to retrieve (default is 0)
     * @param size     the number of vacancies per page (default is 10)
     * @param sort     the sorting criteria in the format "property,order" (default is "id,asc")
     * @return a list of vacancies that match the search criteria
     */
    @GetMapping
    public List<Object> searchVacancies(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String post,
                                         @RequestParam(required = false) String city,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id,asc") String sort) {

        String[] sortParts = sort.split(",");
        String sortBy = sortParts[0];
        String sortOrder = sortParts.length > 1 ? sortParts[1] : "asc";

        Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortOrder), sortBy);

        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        List<Object> vacancies = vacancyService.searchVacancies(name, post, city, pageable);

        return vacancies;
    }
}

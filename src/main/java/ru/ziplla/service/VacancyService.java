package ru.ziplla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ziplla.entity.Vacancy;
import ru.ziplla.repository.VacancyRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    /**
     * Saves a new vacancy to the repository.
     *
     * @param vacancy the vacancy to be saved
     * @return the saved vacancy
     */
    public Vacancy saveVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    /**
     * Searches for vacancies based on provided criteria such as name, post, and city.
     *
     * @param name     the name of the vacancy to search for (partial match)
     * @param post     the post of the vacancy to search for
     * @param city     the city of the vacancy to search for
     * @param pageable the pagination information
     * @return a list of vacancies that match the search criteria
     */
    public List<Object> searchVacancies(String name, String post, String city, Pageable pageable) {
        Specification<Vacancy> specification = Specification.where(null);

        if (name != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), name + "%"));
        }

        if (post != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("post"), post));
        }

        if (city != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("city"), city));
        }

        return vacancyRepository.findAll(specification, pageable).getContent();
    }
}

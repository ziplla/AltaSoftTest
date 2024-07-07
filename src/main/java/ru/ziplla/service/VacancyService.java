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

    public Vacancy saveVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

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

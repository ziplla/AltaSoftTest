package ru.ziplla.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ziplla.entity.Vacancy;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Slice<Object> findAll(Specification<Vacancy> specification, Pageable pageable);
}

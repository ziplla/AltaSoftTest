package ru.ziplla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ziplla.entity.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
}

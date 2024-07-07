package ru.ziplla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ziplla.entity.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}

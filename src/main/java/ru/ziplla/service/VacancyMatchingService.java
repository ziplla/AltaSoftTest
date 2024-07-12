package ru.ziplla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ziplla.entity.Applicant;
import ru.ziplla.entity.Vacancy;

import ru.ziplla.repository.ApplicantRepository;
import ru.ziplla.repository.VacancyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VacancyMatchingService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    /**
     * Matches applicants to vacancies based on their position of interest and city.
     *
     * @return Map containing matched applicants and their corresponding vacancies
     */
    public Map<Applicant, Vacancy> matchVacancy() {

        List<Vacancy> allVacancies = vacancyRepository.findAll();
        List<Applicant> allApplicants = applicantRepository.findAll();

        Map<Applicant, Vacancy> matchedApplicants = new HashMap<>();

        for (Applicant applicant : allApplicants) {
            for (Vacancy vacancy : allVacancies) {
                if (vacancy.getPost().equals(applicant.getPositionOfInterest())
                        && vacancy.getCity().equals(applicant.getCity())) {
                    matchedApplicants.put(applicant, vacancy);
                }
            }

        }
        return matchedApplicants;
    }

}

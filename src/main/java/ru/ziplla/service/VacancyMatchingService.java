package ru.ziplla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ziplla.entity.Applicant;
import ru.ziplla.entity.Vacancy;
import ru.ziplla.model.EmailMessage;
import ru.ziplla.repository.ApplicantRepository;
import ru.ziplla.repository.VacancyRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.ziplla.model.EmailMessage.buildEmailMessageModel;

@Service
public class VacancyMatchingService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

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

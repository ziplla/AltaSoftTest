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

    public Map<String, String> buildEmailMessagesTemplates(Map<Applicant, Vacancy> matchedApplicants) {

        Map<String, String> buildedEmailMessages = new HashMap<>();
        for (Map.Entry<Applicant, Vacancy> entry : matchedApplicants.entrySet()) {
            Applicant applicant = entry.getKey();
            Vacancy vacancy = entry.getValue();

            System.out.println(applicant);
            System.out.println(vacancy);

            EmailMessage emailMessage = buildEmailMessageModel(applicant, vacancy);

            System.out.println(emailMessage);

            String applicantEmail = applicant.getEmail();
            String emailText = "Здравствуйте,  " + emailMessage.getApplicantName() + "!\n" +
                    "Информируем вас о новой вакансии на должность: " + emailMessage.getVacancyPost() + ".\n" +
                    "Наименование: " + emailMessage.getVacancyName() + "\n" +
                    "Описание: " + emailMessage.getVacancyDescription() + "\n" +
                    "Уровень зарплаты: " + emailMessage.getVacancySalaryLevel() + "\n" +
                    "Требуемый опыт работы: " + emailMessage.getVacancyRequiredWorkExperience() + "\n" +
                    "С уважением,\n" +
                    "Цифровое Будущее\n" +
                    emailMessage.getEmailMessageDate().toString() + "\n";

            buildedEmailMessages.put(applicantEmail, emailText);
        }
        System.out.println(buildedEmailMessages);
        return buildedEmailMessages;
    }
}

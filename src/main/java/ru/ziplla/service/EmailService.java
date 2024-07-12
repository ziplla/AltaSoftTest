package ru.ziplla.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ziplla.entity.Applicant;
import ru.ziplla.entity.Vacancy;
import ru.ziplla.model.EmailMessage;

import java.util.HashMap;
import java.util.Map;

import static ru.ziplla.model.EmailMessage.buildEmailMessageModel;

@Service
public class EmailService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private VacancyMatchingService vacancyMatchingService;

    /**
     * Scheduled method that sends email notifications to matched applicants
     * for vacancies. Runs daily at midnight.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendEmail() {

        Map<Applicant, Vacancy> matchVacancy = vacancyMatchingService.matchVacancy();

        Map<String, String> buildedEmailMessages = buildEmailMessagesTemplates(matchVacancy);

        for (Map.Entry<String, String> entry : buildedEmailMessages.entrySet()) {
            Map<String, Object> additionalHeaders = new HashMap<>();
            additionalHeaders.put("to", entry.getKey());
            additionalHeaders.put("from", "pzajtsev.01@gmail.com");
            additionalHeaders.put("subject", "Новая вакансия");
            additionalHeaders.put("body", entry.getValue());

            producerTemplate.sendBodyAndHeaders("direct:sendEmail", null, additionalHeaders);
        }
    }

    /**
     * Builds email message templates based on matched applicants and vacancies.
     *
     * @param matchedApplicants Map of matched applicants and their corresponding vacancies
     * @return Map containing applicant email as key and formatted email text as value
     */
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

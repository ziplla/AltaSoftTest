package ru.ziplla.model;

import lombok.Data;
import ru.ziplla.entity.Applicant;
import ru.ziplla.entity.Vacancy;

import java.time.ZonedDateTime;

@Data
public class EmailMessage {

    String applicantName;
    String vacancyPost;
    String vacancyName;
    String vacancyDescription;
    String vacancySalaryLevel;
    String vacancyRequiredWorkExperience;
    String vacancyCity;
    ZonedDateTime emailMessageDate;

    /**
     * Builds an email message model based on the provided applicant and vacancy details.
     *
     * @param applicant the applicant to whom the email will be sent
     * @param vacancy   the vacancy for which the email is being sent
     * @return an {@code EmailMessage} object containing the details to be included in the email
     */
    public static EmailMessage buildEmailMessageModel(Applicant applicant, Vacancy vacancy) {

        EmailMessage emailMessage = new EmailMessage();

        emailMessage.setApplicantName(applicant.getName());
        emailMessage.setVacancyName(vacancy.getName());
        emailMessage.setVacancyDescription(vacancy.getDescription());
        emailMessage.setVacancyPost(vacancy.getPost());
        emailMessage.setVacancySalaryLevel(vacancy.getSalaryLevel().toString());
        emailMessage.setVacancyRequiredWorkExperience(vacancy.getRequiredWorkExperience());
        emailMessage.setVacancyCity(vacancy.getCity());
        emailMessage.setEmailMessageDate(ZonedDateTime.now());

        return emailMessage;
    }
}

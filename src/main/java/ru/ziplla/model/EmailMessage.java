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

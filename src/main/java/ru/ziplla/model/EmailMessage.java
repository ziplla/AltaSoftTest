package ru.ziplla.model;

import lombok.Data;
import ru.ziplla.entity.Applicant;
import ru.ziplla.entity.Vacancy;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class EmailMessage {

    String applicantName;
//    String applicantEmail;
//    String applicantPositionOfInterest;
//    String applicantCity;
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

//        emailMessage.applicantName = applicant.getName();
////        emailMessage.applicantEmail = applicant.getEmail();
////        emailMessage.applicantPositionOfInterest = applicant.getPositionOfInterest();
////        emailMessage.applicantCity = applicant.getCity();
//        emailMessage.vacancyPost = vacancy.getPost();
//        emailMessage.vacancyName = vacancy.getName();
//        emailMessage.vacancyDescription = vacancy.getDescription();
//        emailMessage.vacancySalaryLevel = vacancy.getSalaryLevel().toString();
//        emailMessage.vacancyRequiredWorkExperience = vacancy.getRequiredWorkExperience().toString();
//        emailMessage.vacancyCity = vacancy.getCity();
//        emailMessage.emailMessageDate = ZonedDateTime.now();

//        String emailMessageTemplate = "Здравствуйте,  " + emailMessage.applicantName + "!\n" +
//                "Информируем вас о новой вакансии на должность" + emailMessage.vacancyPost + ".\n" +
//                "Наименование: " + emailMessage.vacancyName + "\n" +
//                "Описание: " + emailMessage.vacancyDescription + "\n" +
//                "Уровень зарплаты: " + emailMessage.vacancySalaryLevel + "\n" +
//                "Требуемый опыт работы: " + emailMessage.vacancyRequiredWorkExperience + "\n" +
//                "С уважением,\n" +
//                "Цифровое Будущее\n" +
//                "«дата»";
//
//        return emailMessageTemplate;
    }
}

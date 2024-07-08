package ru.ziplla.service;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ziplla.entity.Applicant;
import ru.ziplla.entity.Vacancy;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private VacancyMatchingService vacancyMatchingService;

    // 0. метод запускается раз в сутки
    // 1. перебрать вакансии и соискателей
    // 2. отдельный мап с ними
    // 3. берем из мапа соискателя
    // 4. берем от соискателя вакансию
    // 5. берем почту и имя от соискателя
    // 6. берем все данные вакансии
    // 7. составляем письмо
    // 8. отправляем письмо

//    public void sendEmail() {
//        Map<Applicant, Vacancy> matchVacancy = vacancyMatchingService.matchVacancy();
//
//        Map<String, String> buildedEmailMessages = vacancyMatchingService.buildEmailMessagesTemplates(matchVacancy);
//
//        for (Map.Entry<String, String> entry : buildedEmailMessages.entrySet()) {
//            producerTemplate.sendBody(entry.getKey(), entry.getValue());
//        }
//
////        producerTemplate.sendBody("direct:sendEmail", null);
//    }

//    public void sendEmail() {
//
//        Map<Applicant, Vacancy> matchVacancy = vacancyMatchingService.matchVacancy();
//
//        Map<String, String> buildedEmailMessages = vacancyMatchingService.buildEmailMessagesTemplates(matchVacancy);
//
//        Map<String, String> additionalHeaders = new HashMap<>();
//
//        for (Map.Entry<String, String> entry : buildedEmailMessages.entrySet()) {
//            additionalHeaders.put("to", entry.getKey());
//            additionalHeaders.put("from", "pzajtsev.01@gmail.com"); // Установите адрес отправителя
//            additionalHeaders.put("subject", "Test Email"); // Вы можете изменить тему при необходимости
//            additionalHeaders.put("body", entry.getValue());
//
//            // Отправка сообщения через Camel Route
//            producerTemplate.sendBodyAndHeaders("direct:sendEmail", null);
//        }
//
//        // Установка заголовков для email
//    }
public void sendEmail() {

    Map<Applicant, Vacancy> matchVacancy = vacancyMatchingService.matchVacancy();

    Map<String, String> buildedEmailMessages = vacancyMatchingService.buildEmailMessagesTemplates(matchVacancy);

    for (Map.Entry<String, String> entry : buildedEmailMessages.entrySet()) {
        Map<String, Object> additionalHeaders = new HashMap<>();
        additionalHeaders.put("to", entry.getKey());
        additionalHeaders.put("from", "pzajtsev.01@gmail.com"); // Установите адрес отправителя
        additionalHeaders.put("subject", "Test Email"); // Вы можете изменить тему при необходимости
        additionalHeaders.put("body", entry.getValue());

        // Отправка сообщения через Camel Route
        producerTemplate.sendBodyAndHeaders("direct:sendEmail", null, additionalHeaders);
    }
}

}

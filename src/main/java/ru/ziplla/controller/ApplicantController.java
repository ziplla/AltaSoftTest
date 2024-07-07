package ru.ziplla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ziplla.entity.Applicant;
import ru.ziplla.service.ApplicantService;

@RestController
@RequestMapping("/subscriber")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @PutMapping
    public Applicant saveApplicant(@RequestBody Applicant applicant) {
        return applicantService.saveApplicant(applicant);
    }
}

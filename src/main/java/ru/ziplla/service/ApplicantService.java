package ru.ziplla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ziplla.entity.Applicant;
import ru.ziplla.repository.ApplicantRepository;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    /**
     * Saves a new applicant to the repository.
     *
     * @param applicant the applicant to be saved
     * @return the saved applicant
     */
    public Applicant saveApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }
}

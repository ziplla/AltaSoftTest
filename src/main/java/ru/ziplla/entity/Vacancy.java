package ru.ziplla.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vacancy")
@Data
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vacancy_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "post")
    String post;

    @Column(name = "salary_level")
    Long salaryLevel;

    @Column(name = "required_work_experience")
    String requiredWorkExperience;

    @Column(name = "city")
    String city;
}

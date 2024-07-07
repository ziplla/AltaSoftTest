package ru.ziplla.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "applicant_id")
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "name")
    String name;

    @Column(name = "city")
    String city;

    @Column(name = "positionOfInterest")
    String positionOfInterest;
}

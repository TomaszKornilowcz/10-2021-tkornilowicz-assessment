package com.tkornilowicz.assessment.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "localizations")
public class Localization{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street_name", length = 30, nullable = false)
    private String streetName;
    @Column(length = 10, nullable = false)
    private String number;
    @Column(name = "zip_code", length = 6, nullable = false, unique = true)
    private String zipCode;
    @Column(length = 30, nullable = false)
    private String city;

}

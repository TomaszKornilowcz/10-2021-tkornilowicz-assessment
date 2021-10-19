package com.tkornilowicz.assessment.department;

import com.tkornilowicz.assessment.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "localization_id")
    private Localization localization;
}

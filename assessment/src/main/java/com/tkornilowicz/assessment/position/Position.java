package com.tkornilowicz.assessment.position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "positions")
public class Position implements Comparable<Position> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Override
    public int compareTo(Position o) {
        return getId().compareTo(o.getId());
    }
}

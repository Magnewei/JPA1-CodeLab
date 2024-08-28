package app.cupcake.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",unique = false, nullable = false)
    private String name;
    @Column(name = "age",unique = false,nullable = false)
    private int age;
}

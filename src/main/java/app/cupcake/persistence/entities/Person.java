package app.cupcake.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "id",unique = true,nullable = false)
    private int id;
    @Column(name = "name",unique = false, nullable = false)
    private String name;
    @Column(name = "age",unique = false,nullable = false)
    private int age;
}

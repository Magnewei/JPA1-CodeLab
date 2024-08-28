package app.cupcake.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private int id;
    @Column(name = "name",unique = false, nullable = false)
    private String name;
    @Column(name = "teacher",unique = false,nullable = false)
    private String teacher;
    @Column(name = "semester",unique = false,nullable = false)
    private String semester;
}

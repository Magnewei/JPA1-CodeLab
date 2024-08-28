package app.cupcake.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private int id;
    @Column(name = "name",unique = false, nullable = false)
    private String name;
    @Column(name = "phoneNumber",unique = true,nullable = false)
    private String phoneNumber;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "address",unique = false,nullable = false)
    private String address;
    @ElementCollection
    private List<Integer> courseID;
}

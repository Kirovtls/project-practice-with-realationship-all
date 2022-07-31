package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "instructor")
@Getter
@Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long instructor_id;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.MERGE)
   private List<Course> courseList;
}

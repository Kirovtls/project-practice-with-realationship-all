package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long lesson_id;

    private String name;
    private String videoLnk;

   @ManyToOne
   private Course course;

    @OneToMany(fetch=FetchType.EAGER ,cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    List<Task> task;


}

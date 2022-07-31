package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long course_id;
    private String duration;
    private String creatAt;
    private String imageLink;
    private String description;


    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "courseList", cascade = {CascadeType.MERGE, CascadeType.REMOVE} )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Instructor> instructorList;

    @OneToMany(fetch=FetchType.EAGER ,cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "course")
   private List<Lesson> lessonList;

}

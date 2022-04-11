package Homework;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Cacheable
@Table(name = "")
public class Pupils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "university")
    private String university;

    @Column(name = "salary_by_university")
    private int salary_by_university;

    public Pupils() {
    }

    public Pupils(String name, String university, int salary_by_university) {
        this.name = name;
        this.university = university;
        this.salary_by_university = salary_by_university;
    }
}

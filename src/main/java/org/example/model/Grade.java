package org.example.model;

import java.util.HashSet;
import java.util.Set;

public class Grade {
    private Integer grade;
    private Set<Student> students;
    private Set<Subject> subjects;

    Grade(Integer grade){
        this.grade = grade;
        this.students = new HashSet<>();
        this.subjects = new HashSet<>();
    }

    Grade(Integer grade, Set<Student> students, Set<Subject> subjects){
        this.grade = grade;
        this.subjects = subjects;
        this.students = students;
    }

    public Integer getGrade(){
        return grade;
    }

    public Set<Student> getStudents(){
        return students;
    }

    public Set<Subject> getSubjects(){
        return subjects;
    }
}

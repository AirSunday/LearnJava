package org.example.Dto;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
public class StudentMidGradeDTO {
    private String name;
    private String family;
    private int age;
    private int group_number;
    private String mid_grade;

    public  StudentMidGradeDTO(String name, String family, int age, int group_number, double mid_grade){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group_number = group_number;
        DecimalFormat df = new DecimalFormat("#.###");
        this.mid_grade = df.format(mid_grade);
    }
}

package Class;

import java.text.DecimalFormat;

public class Person {
    private String family;
    private String name;
    private int age;
    private int group;
    private int physics;
    private int mathematics;
    private int rus;
    private int literature;
    private int geometry;
    private int informatics;

    public Person(String family, String name, int age, int group, int physics,
                  int mathematics, int rus, int literature, int geometry, int informatics) {
        this.family = family;
        this.name = name;
        this.age = age;
        this.group = group;
        this.physics = physics;
        this.mathematics = mathematics;
        this.rus = rus;
        this.literature = literature;
        this.geometry = geometry;
        this.informatics = informatics;
    }

    public double getMidGrade(){
        double sum = physics + mathematics + rus + literature + geometry + informatics;
        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format(sum / 6.0);
        return Double.parseDouble(formattedValue.replace(',', '.'));
    }

    public boolean isExcellentStudent(){
        return getMidGrade() == 5.0;
    }

    public int getGroup() {
        return group;
    }

    public int getAge() {
        return age;
    }

    public String getFamily() {
        return family;
    }

    public void print(){
        System.out.print(family + " " + name + "; age: " + age + "; group: " + group + "; mid grade: " + getMidGrade());
    }
}

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

    public double GetMidGrade(){
        double sum = physics + mathematics + rus + literature + geometry + informatics;
        double average = sum / 6.0;

        // Форматирование ответа с округлением до 3 знаков после запятой
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return Double.parseDouble(decimalFormat.format(average));
    }

    public boolean IsExcellentStudent(){
        return GetMidGrade() == 5.0;
    }

    public int GetGroup() {
        return group;
    }

    public int GetAge() {
        return age;
    }

    public String GetFamily() {
        return family;
    }

    public void Print(){
        System.out.print(family + " " + name + " age: " + age + " group: " + group);
    }
}

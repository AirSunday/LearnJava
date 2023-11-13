package org.example.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubjectType {
    PHYS("физика"),
    MATH("математика"),
    RUSS("русский"),
    LITE("литература"),
    GEOM("геометрия"),
    INFO("информатика")
    ;
    private final String name;

    public static final String PHYS_NAME = "PHYS";
    public static final String MATH_NAME = "MATH";
    public static final String RUSS_NAME = "RUSS";
    public static final String LITE_NAME = "LITE";
    public static final String GEOM_NAME = "GEOM";
    public static final String INFO_NAME = "INFO";

    public static SubjectType fromName(String name) {
        for (SubjectType subjectType : SubjectType.values()) {
            if (subjectType.name.equals(name)) {
                return subjectType;
            }
        }
        throw new IllegalArgumentException("No such subject type with name: " + name);
    }
}

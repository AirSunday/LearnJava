CREATE TABLE t_subject (
                           id   bigserial PRIMARY KEY,
                           name text
);

CREATE TABLE t_student (
                           id     bigserial PRIMARY KEY,
                           name   text,
                           family text,
                           age    int4
);

CREATE TABLE t_group (
                         id     bigserial PRIMARY KEY,
                         number int4,
                         student_id bigint REFERENCES t_student(id)
);

CREATE TABLE t_grade (
                         id      bigserial PRIMARY KEY,
                         grade   int4,
                         subject_id bigint REFERENCES t_subject(id),
                         student_id bigint REFERENCES t_student(id)
);

CREATE SEQUENCE t_subject_id_seq START 1;
CREATE SEQUENCE t_group_id_seq START 1;
CREATE SEQUENCE t_student_id_seq START 1;

CREATE TABLE t_subject (
   id  BIGINT DEFAULT  nextval('t_subject_id_seq') NOT NULL,
   name VARCHAR(255) NOT NULL,
   constraint pk_t_subject_id primary key(id)
);

CREATE TABLE t_group (
    id BIGINT DEFAULT  nextval('t_group_id_seq') NOT NULL ,
    number INTEGER NOT NULL,
    constraint pk_t_group_id primary key(id)
);

CREATE TABLE t_student (
    id BIGINT DEFAULT  nextval('t_student_id_seq') NOT NULL ,
    name VARCHAR(255) NOT NULL,
    family VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    group_id BIGINT,
    constraint pk_t_student_id primary key(id),
    FOREIGN KEY (group_id) REFERENCES t_group (id)
);

CREATE TABLE t_grade (
     subject_id BIGINT,
     student_id BIGINT,
     grade INTEGER,
     PRIMARY KEY (subject_id, student_id),
     FOREIGN KEY (subject_id) REFERENCES t_subject (id),
     FOREIGN KEY (student_id) REFERENCES t_student (id)
);
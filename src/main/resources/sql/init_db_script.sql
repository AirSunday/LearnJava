CREATE TABLE "group"(
                        "id" bigserial NOT NULL,
                        CONSTRAINT group_pkey PRIMARY KEY (id),
                        "number" bigint NOT NULL
);

CREATE TABLE "student"(
                          "id" bigserial NOT NULL,
                          CONSTRAINT student_pkey PRIMARY KEY (id),
                          "family" character varying(255) NOT NULL,
                          "name" character varying(255) NOT NULL,
                          "age" integer NOT NULL,

                          "group_id" integer NOT NULL,
                          CONSTRAINT fk_group_id FOREIGN KEY (group_id)
                              REFERENCES "group" (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE "subject"(
                          "id" bigserial NOT NULL,
                          CONSTRAINT subject_pkey PRIMARY KEY (id),
                          "name" character varying(255) NOT NULL
);

CREATE TABLE "grade"(
                        "id" bigserial NOT NULL,
                        CONSTRAINT grade_pkey PRIMARY KEY (id),
                        "grade" integer NOT NULL,

                        "subject_id" bigint NOT NULL,
                        CONSTRAINT fk_subject_id FOREIGN KEY (subject_id)
                            REFERENCES subject (id) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE CASCADE,

                        "student_id" bigint NOT NULL,
                        CONSTRAINT fk_student_id FOREIGN KEY (student_id)
                            REFERENCES student (id) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE "mid_grade"(
                        "id" bigserial NOT NULL,
                        CONSTRAINT mid_grade_pkey PRIMARY KEY (id),
                        "grade" DOUBLE PRECISION NOT NULL,

                        "student_id" bigint NOT NULL,
                        CONSTRAINT fk_student_id FOREIGN KEY (student_id)
                            REFERENCES student (id) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE CASCADE
);

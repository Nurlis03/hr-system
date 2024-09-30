CREATE TABLE aud.job_type_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    job_type_title_kg VARCHAR(255) NOT NULL,
    job_type_title_ru VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.position_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    position_title_kg VARCHAR(255) NOT NULL,
    position_title_ru VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.work_schedule_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    work_schedule_title_kg VARCHAR(255) NOT NULL,
    work_schedule_title_ru VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.skill_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    skill_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.vacancy_detail_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    work_schedule_id BIGINT,
    industry_id BIGINT,
    job_type_id BIGINT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.vacancy_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    vacancy_title_ru VARCHAR(255) NOT NULL,
    vacancy_title_kg VARCHAR(255) NOT NULL,
    description_ru TEXT,
    description_kg TEXT,
    salary_from BIGINT NOT NULL,
    salary_to BIGINT NOT NULL,
    experience_years_required BIGINT NOT NULL,
    education_required VARCHAR(255) NOT NULL,
    vacancy_status VARCHAR(255) NOT NULL,
    hr_specialist_id BIGINT,
    vacancy_detail_id BIGINT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.vacancy_comment_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    vacancy_id BIGINT,
    user_id BIGINT,
    comment_text TEXT NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.work_experience_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    exp_from DATE NOT NULL,
    exp_to DATE NOT NULL,
    exp_description TEXT,
    company_id BIGINT,
    job_type_id BIGINT,
    position_id BIGINT,
    applicant_id BIGINT,
    city VARCHAR(255),
    state VARCHAR(255),
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.job_application_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    applicant_id BIGINT,
    vacancy_id BIGINT,
    resume_url TEXT,
    cover_letter TEXT,
    application_status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.applicant_skills_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    applicant_id BIGINT,
    skill_id BIGINT,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

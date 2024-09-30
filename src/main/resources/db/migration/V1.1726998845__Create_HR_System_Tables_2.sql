CREATE TABLE job_type (
    id BIGSERIAL PRIMARY KEY,
    job_type_title_kg VARCHAR(255) NOT NULL,
    job_type_title_ru VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE position (
    id BIGSERIAL PRIMARY KEY,
    position_title_kg VARCHAR(255) NOT NULL,
    position_title_ru VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE work_schedule (
    id BIGSERIAL PRIMARY KEY,
    work_schedule_title_kg VARCHAR(255) NOT NULL,
    work_schedule_title_ru VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE skill (
    id BIGSERIAL PRIMARY KEY,
    skill_name VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE vacancy_detail (
    id BIGSERIAL PRIMARY KEY,
    work_schedule_id BIGINT,
    industry_id BIGINT,
    job_type_id BIGINT,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (work_schedule_id) REFERENCES work_schedule(id),
    FOREIGN KEY (industry_id) REFERENCES industry(id),
    FOREIGN KEY (job_type_id) REFERENCES job_type(id),
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE vacancy (
    id BIGSERIAL PRIMARY KEY,
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
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (hr_specialist_id) REFERENCES hr_specialist(id),
    FOREIGN KEY (vacancy_detail_id) REFERENCES vacancy_detail(id),
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE vacancy_comment (
    id BIGSERIAL PRIMARY KEY,
    vacancy_id BIGINT,
    user_id BIGINT,
    comment_text TEXT NOT NULL,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (vacancy_id) REFERENCES vacancy(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE work_experience (
    id BIGSERIAL PRIMARY KEY,
    exp_from DATE NOT NULL,
    exp_to DATE NOT NULL,
    exp_description TEXT,
    company_id BIGINT,
    job_type_id BIGINT,
    position_id BIGINT,
    applicant_id BIGINT,
    city VARCHAR(255),
    state VARCHAR(255),
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (job_type_id) REFERENCES job_type(id),
    FOREIGN KEY (position_id) REFERENCES position(id),
    FOREIGN KEY (applicant_id) REFERENCES applicant(id),
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE job_application (
    id BIGSERIAL PRIMARY KEY,
    applicant_id BIGINT,
    vacancy_id BIGINT,
    resume_url TEXT,
    cover_letter TEXT,
    application_status VARCHAR(50) NOT NULL,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (applicant_id) REFERENCES applicant(id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancy(id),
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE TABLE applicant_skills (
    applicant_id BIGINT,
    skill_id BIGINT,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    PRIMARY KEY (applicant_id, skill_id),
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id),
    FOREIGN KEY (applicant_id) REFERENCES applicant(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id)
);

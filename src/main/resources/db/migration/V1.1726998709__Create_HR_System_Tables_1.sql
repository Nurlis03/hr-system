CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    lock_expiration_time TIMESTAMP,
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

CREATE TABLE admin (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES "user"(id)
);

CREATE TABLE applicant (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address_ru VARCHAR(255),
    address_kg VARCHAR(255),
    phone_number VARCHAR(255),
    FOREIGN KEY (id) REFERENCES "user"(id)
);

CREATE TABLE company (
    id BIGSERIAL PRIMARY KEY,
    company_title_ru VARCHAR(255) NOT NULL,
    company_title_kg VARCHAR(255) NOT NULL,
    address_ru TEXT NOT NULL,
    address_kg TEXT NOT NULL,
    founded_year BIGINT,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    website VARCHAR(255) NOT NULL,
    logo BYTEA,
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

CREATE TABLE hr_specialist (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    company_id BIGINT,
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (id) REFERENCES "user"(id)
);

CREATE TABLE education (
    id BIGSERIAL PRIMARY KEY,
    edu_from DATE NOT NULL,
    edu_to DATE NOT NULL,
    education_title_kg VARCHAR(255) NOT NULL,
    education_title_ru VARCHAR(255) NOT NULL,
    degree_kg VARCHAR(255) NOT NULL,
    degree_ru VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    state VARCHAR(255),
    gpa VARCHAR(50),
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

CREATE TABLE award_certification (
    id BIGSERIAL PRIMARY KEY,
    award_certification_title_ru VARCHAR(255) NOT NULL,
    award_certification_title_kg VARCHAR(255) NOT NULL,
    description TEXT,
    applicant_id BIGINT,
    deleted_at TIMESTAMP,
    deleted_by BIGINT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    FOREIGN KEY (deleted_by) REFERENCES "user"(id),
    FOREIGN KEY (created_by) REFERENCES "user"(id),
    FOREIGN KEY (updated_by) REFERENCES "user"(id),
    FOREIGN KEY (applicant_id) REFERENCES applicant(id)
);

CREATE TABLE industry (
    id BIGSERIAL PRIMARY KEY,
    industry_title_kg VARCHAR(255) NOT NULL,
    industry_title_ru VARCHAR(255) NOT NULL,
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
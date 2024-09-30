CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE TABLE IF NOT EXISTS aud.revinfo (
    rev BIGSERIAL PRIMARY KEY,
    revtstmp BIGINT
);

CREATE TABLE aud.user_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    lock_expiration_time TIMESTAMP,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.admin_aud (
   id BIGINT NOT NULL,
   rev BIGINT NOT NULL,
   revtype SMALLINT,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   PRIMARY KEY (id, rev),
   FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.applicant_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address_ru VARCHAR(255),
    address_kg VARCHAR(255),
    phone_number VARCHAR(255),
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.company_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    company_title_ru VARCHAR(255) NOT NULL,
    company_title_kg VARCHAR(255) NOT NULL,
    address_ru TEXT NOT NULL,
    address_kg TEXT NOT NULL,
    founded_year BIGINT,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    website VARCHAR(255) NOT NULL,
    logo BYTEA,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.hr_specialist_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    company_id BIGINT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.education_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    edu_from DATE NOT NULL,
    edu_to DATE NOT NULL,
    education_title_kg VARCHAR(255) NOT NULL,
    education_title_ru VARCHAR(255) NOT NULL,
    degree_kg VARCHAR(255) NOT NULL,
    degree_ru VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    state VARCHAR(255),
    gpa VARCHAR(50),
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.award_certification_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    award_certification_title_ru VARCHAR(255) NOT NULL,
    award_certification_title_kg VARCHAR(255) NOT NULL,
    description TEXT,
    applicant_id BIGINT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);

CREATE TABLE aud.industry_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,
    industry_title_kg VARCHAR(255) NOT NULL,
    industry_title_ru VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES aud.revinfo(rev)
);
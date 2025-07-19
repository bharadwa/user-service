CREATE TABLE roles
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    deleted            BIT(1) NULL,
    value              VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE session
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    deleted            BIT(1) NULL,
    token              LONGTEXT NULL,
    expiration_date    datetime NULL,
    user_id            BIGINT NULL,
    status             SMALLINT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    deleted            BIT(1) NULL,
    value              VARCHAR(255) NULL,
    expiration_date    datetime NULL,
    user_id            BIGINT NULL,
    CONSTRAINT pk_tokens PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    deleted            BIT(1) NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    roles_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE tokens
    ADD CONSTRAINT FK_TOKENS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (users_id) REFERENCES users (id);
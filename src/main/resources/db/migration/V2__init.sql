CREATE TABLE roles
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_by VARCHAR(255)          NULL,
    update_by  VARCHAR(255)          NULL,
    is_deleted BIT(1)                NOT NULL,
    value      VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_by      VARCHAR(255)          NULL,
    update_by       VARCHAR(255)          NULL,
    is_deleted      BIT(1)                NOT NULL,
    value           VARCHAR(255)          NULL,
    expiration_date datetime              NULL,
    user_id         BIGINT                NULL,
    CONSTRAINT pk_tokens PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_by VARCHAR(255)          NULL,
    update_by  VARCHAR(255)          NULL,
    is_deleted BIT(1)                NOT NULL,
    name       VARCHAR(255)          NULL,
    email      VARCHAR(255)          NULL,
    password   VARCHAR(255)          NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    roles_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL
);

ALTER TABLE tokens
    ADD CONSTRAINT FK_TOKENS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (users_id) REFERENCES users (id);
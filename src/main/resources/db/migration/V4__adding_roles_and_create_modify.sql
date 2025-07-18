ALTER TABLE roles
    ADD created_date datetime NULL;

ALTER TABLE roles
    ADD last_modified_date datetime NULL;

ALTER TABLE tokens
    ADD created_date datetime NULL;

ALTER TABLE tokens
    ADD last_modified_date datetime NULL;

ALTER TABLE users
    ADD created_date datetime NULL;

ALTER TABLE users
    ADD last_modified_date datetime NULL;

ALTER TABLE roles
DROP
COLUMN created_by;

ALTER TABLE roles
DROP
COLUMN update_by;

ALTER TABLE tokens
DROP
COLUMN created_by;

ALTER TABLE tokens
DROP
COLUMN update_by;

ALTER TABLE users
DROP
COLUMN created_by;

ALTER TABLE users
DROP
COLUMN update_by;
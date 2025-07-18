ALTER TABLE roles
DROP
COLUMN is_deleted;

ALTER TABLE tokens
DROP
COLUMN is_deleted;

ALTER TABLE users
DROP
COLUMN is_deleted;

ALTER TABLE roles
    MODIFY deleted BIT (1) NULL;

ALTER TABLE tokens
    MODIFY deleted BIT (1) NULL;

ALTER TABLE users
    MODIFY deleted BIT (1) NULL;
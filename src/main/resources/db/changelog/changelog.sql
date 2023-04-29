-- changeset liquibase:1

CREATE TABLE USERS
(
    ID_USER    SERIAL,
    FIRST_NAME varchar(255),
    LAST_NAME  varchar(255),
    EMAIL      varchar(255),
    PASSWORD   varchar(255),
    ROLE       varchar(255),
    PRIMARY KEY (ID_USER)
);

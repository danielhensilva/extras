DROP TABLE IF EXISTS mathematician;
CREATE TABLE mathematician
(
    id           INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name         VARCHAR(63) NOT NULL,
    born_date    DATE,
    died_date    DATE,
    created_date TIMESTAMP        NOT NULL DEFAULT CURRENT_DATE
);
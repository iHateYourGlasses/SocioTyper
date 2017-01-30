PRAGMA foreign_keys = 0;

CREATE TABLE sqlitestudio_temp_table AS SELECT *
                                          FROM reinin_signs;

DROP TABLE reinin_signs;

CREATE TABLE reinin_signs (
    id   INTEGER,
    name TEXT (255) UNIQUE,
    bind INT
);

INSERT INTO reinin_signs (
                             id,
                             name,
                             bind
                         )
                         SELECT id,
                                name,
                                bind
                           FROM sqlitestudio_temp_table;

DROP TABLE sqlitestudio_temp_table;

CREATE INDEX bind ON reinin_signs (
    bind
);

CREATE INDEX [2] ON reinin_signs (
    name
);

PRAGMA foreign_keys = 1;

UPDATE reinin_signs
   SET id = '8'
 WHERE name = 'Рациональность';
 UPDATE reinin_signs
   SET id = '9'
 WHERE name = 'Иррациональность';
 UPDATE reinin_signs
   SET id = '10'
 WHERE name = 'Статика';
 UPDATE reinin_signs
   SET id = '11'
 WHERE name = 'Динамика';
 
 PRAGMA foreign_keys = 0;

CREATE TABLE sqlitestudio_temp_table AS SELECT *
                                          FROM reinin_signs;

DROP TABLE reinin_signs;

CREATE TABLE reinin_signs (
    id   INTEGER    PRIMARY KEY,
    name TEXT (255) UNIQUE,
    bind INT
);

INSERT INTO reinin_signs (
                             id,
                             name,
                             bind
                         )
                         SELECT id,
                                name,
                                bind
                           FROM sqlitestudio_temp_table;

DROP TABLE sqlitestudio_temp_table;

CREATE INDEX bind ON reinin_signs (
    bind
);

CREATE INDEX [2] ON reinin_signs (
    name
);

PRAGMA foreign_keys = 1;

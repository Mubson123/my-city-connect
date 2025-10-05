-- =========================================
-- Table principale : CITIZEN
-- =========================================
CREATE TABLE CITIZEN (
                         ID UUID PRIMARY KEY,
                         CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         GENDER VARCHAR(20) NOT NULL,
                         FIRST_NAME VARCHAR(100) NOT NULL,
                         LAST_NAME VARCHAR(100) NOT NULL,
                         BIRTH_DATE DATE NOT NULL,
                         EMAIL VARCHAR(255) NOT NULL UNIQUE,
                         MARITAL_STATUS VARCHAR(20) NOT NULL
);

-- =========================================
-- Table des TITLES -> CITIZEN_TITLES
-- =========================================
CREATE TABLE CITIZEN_TITLES (
                                CITIZEN_ID UUID NOT NULL,
                                TITLE VARCHAR(20) NOT NULL,
                                PRIMARY KEY (CITIZEN_ID, TITLE),
                                CONSTRAINT FK_CITIZEN_TITLES FOREIGN KEY (CITIZEN_ID)
                                    REFERENCES CITIZEN(ID)
                                    ON DELETE CASCADE
);

-- =========================================
-- Table des PHONE_NUMBERS -> CITIZEN_PHONE_NUMBERS
-- =========================================
CREATE TABLE CITIZEN_PHONE_NUMBERS (
                                       CITIZEN_ID UUID NOT NULL,
                                       NUMBER VARCHAR(30) NOT NULL,
                                       PHONE_TYPE VARCHAR(20),
                                       PRIMARY KEY (CITIZEN_ID, NUMBER),
                                       CONSTRAINT FK_CITIZEN_PHONE FOREIGN KEY (CITIZEN_ID)
                                           REFERENCES CITIZEN(ID)
                                           ON DELETE CASCADE
);

-- =========================================
-- Table des ADDRESSES -> CITIZEN_ADDRESSES
-- =========================================
CREATE TABLE CITIZEN_ADDRESSES (
                                   CITIZEN_ID UUID NOT NULL,
                                   TYPE VARCHAR(20) NOT NULL,
                                   STREET VARCHAR(255) NOT NULL,
                                   ZIP VARCHAR(20) NOT NULL,
                                   CITY VARCHAR(100) NOT NULL,
                                   PRIMARY KEY (CITIZEN_ID, TYPE, STREET),
                                   CONSTRAINT FK_CITIZEN_ADDRESS FOREIGN KEY (CITIZEN_ID)
                                       REFERENCES CITIZEN(ID)
                                       ON DELETE CASCADE
);

-- =========================================
-- Table des IDENTIFICATIONS -> CITIZEN_IDENTIFICATION
-- =========================================
CREATE TABLE CITIZEN_IDENTIFICATION (
                                        CITIZEN_ID UUID NOT NULL,
                                        ID_TYPE VARCHAR(20) NOT NULL,
                                        ID_NUMBER VARCHAR(100) NOT NULL,
                                        ISSUED_DATE DATE NOT NULL,
                                        EXPIRATION_DATE DATE NOT NULL,
                                        PRIMARY KEY (CITIZEN_ID, ID_NUMBER),
                                        CONSTRAINT FK_CITIZEN_IDENT FOREIGN KEY (CITIZEN_ID)
                                            REFERENCES CITIZEN(ID)
                                            ON DELETE CASCADE
);

-- =========================================
-- Index pour performances
-- =========================================
CREATE INDEX IDX_CITIZEN_EMAIL ON CITIZEN(EMAIL);
CREATE INDEX IDX_CITIZEN_LASTNAME ON CITIZEN(LAST_NAME);
CREATE INDEX IDX_CITIZEN_CITY ON CITIZEN_ADDRESSES(CITY);
CREATE INDEX IDX_CITIZEN_PHONE_TYPE ON CITIZEN_PHONE_NUMBERS(PHONE_TYPE);
CREATE INDEX IDX_CITIZEN_IDTYPE ON CITIZEN_IDENTIFICATION(ID_TYPE);



-- data.sql pour H2 (compatible Spring Boot)
-- Utilise RANDOM_UUID() pour générer les UUID côté BD (H2)
-- Ne contient PAS de commande PostgreSQL (ex: CREATE EXTENSION)

-- ===========================
-- Insertions : table CITIZEN
-- ===========================
INSERT INTO CITIZEN (ID, CREATED_AT, UPDATED_AT, GENDER, FIRST_NAME, LAST_NAME, BIRTH_DATE, EMAIL, MARITAL_STATUS) VALUES
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-01-15 10:00:00', TIMESTAMP '2025-10-05 15:00:00', 'Male', 'Jean', 'Dupont', '1985-03-12', 'jean.dupont@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-02-10 09:00:00', TIMESTAMP '2025-10-03 18:00:00', 'Female', 'Sophie', 'Martin', '1990-07-25', 'sophie.martin@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-02-11 11:00:00', TIMESTAMP '2025-10-01 19:00:00', 'Male', 'Lucas', 'Bernard', '1998-11-04', 'lucas.bernard@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-03-02 10:30:00', TIMESTAMP '2025-10-05 14:00:00', 'Female', 'Claire', 'Durand', '1987-09-15', 'claire.durand@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-04-12 14:00:00', TIMESTAMP '2025-10-05 12:30:00', 'Male', 'Thomas', 'Petit', '1993-02-08', 'thomas.petit@example.com', 'DIVORCED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-05-01 15:00:00', TIMESTAMP '2025-10-04 17:00:00', 'Female', 'Julie', 'Robert', '2000-06-21', 'julie.robert@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-06-18 13:00:00', TIMESTAMP '2025-10-05 10:00:00', 'Male', 'Nicolas', 'Richard', '1981-12-19', 'nicolas.richard@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-06-22 09:45:00', TIMESTAMP '2025-10-02 10:15:00', 'Female', 'Élodie', 'Moreau', '1995-03-10', 'elodie.moreau@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-07-10 08:00:00', TIMESTAMP '2025-09-25 09:00:00', 'Male', 'Antoine', 'Simon', '1989-10-01', 'antoine.simon@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-08-02 12:30:00', TIMESTAMP '2025-09-29 08:00:00', 'Female', 'Camille', 'Laurent', '1996-08-14', 'camille.laurent@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-09-05 10:00:00', TIMESTAMP '2025-10-05 15:00:00', 'Male', 'David', 'Lefevre', '1978-04-30', 'david.lefevre@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-09-10 16:00:00', TIMESTAMP '2025-10-05 13:00:00', 'Female', 'Alice', 'Mercier', '1992-12-11', 'alice.mercier@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-01 08:15:00', TIMESTAMP '2025-10-01 08:45:00', 'Male', 'Hugo', 'Garcia', '1997-01-22', 'hugo.garcia@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-02 09:15:00', TIMESTAMP '2025-09-30 11:30:00', 'Female', 'Laura', 'Faure', '1984-05-03', 'laura.faure@example.com', 'DIVORCED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-03 12:00:00', TIMESTAMP '2025-10-04 14:00:00', 'Male', 'Mathieu', 'Roux', '1991-09-19', 'mathieu.roux@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-04 15:00:00', TIMESTAMP '2025-10-05 15:00:00', 'Female', 'Manon', 'Vincent', '1999-04-16', 'manon.vincent@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-05 08:00:00', TIMESTAMP '2025-10-05 08:00:00', 'Male', 'Adrien', 'Muller', '1986-06-28', 'adrien.muller@example.com', 'MARRIED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-05 08:30:00', TIMESTAMP '2025-10-05 09:00:00', 'Female', 'Chloé', 'Lemoine', '1993-01-30', 'chloe.lemoine@example.com', 'SINGLE'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-05 09:00:00', TIMESTAMP '2025-10-05 09:30:00', 'Male', 'Paul', 'Blanc', '1980-08-25', 'paul.blanc@example.com', 'DIVORCED'),
                                                                                               (RANDOM_UUID(), TIMESTAMP '2024-10-05 09:15:00', TIMESTAMP '2025-10-05 09:45:00', 'Female', 'Emma', 'Perrin', '2001-02-05', 'emma.perrin@example.com', 'SINGLE');

-- =================================
-- Titles (TITLE) -> CITIZEN_TITLES
-- =================================
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr'  FROM CITIZEN WHERE EMAIL='jean.dupont@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Prof' FROM CITIZEN WHERE EMAIL='sophie.martin@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Ing'  FROM CITIZEN WHERE EMAIL='lucas.bernard@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr'  FROM CITIZEN WHERE EMAIL='claire.durand@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Ing' FROM CITIZEN WHERE EMAIL='thomas.petit@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr' FROM CITIZEN WHERE EMAIL='julie.robert@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Hab' FROM CITIZEN WHERE EMAIL='nicolas.richard@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr' FROM CITIZEN WHERE EMAIL='elodie.moreau@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Prof' FROM CITIZEN WHERE EMAIL='antoine.simon@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Ing' FROM CITIZEN WHERE EMAIL='camille.laurent@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Hab' FROM CITIZEN WHERE EMAIL='david.lefevre@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr' FROM CITIZEN WHERE EMAIL='alice.mercier@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Ing' FROM CITIZEN WHERE EMAIL='hugo.garcia@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Prof' FROM CITIZEN WHERE EMAIL='laura.faure@example.com';
-- Mathieu a deux titres
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr' FROM CITIZEN WHERE EMAIL='mathieu.roux@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Prof' FROM CITIZEN WHERE EMAIL='mathieu.roux@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr' FROM CITIZEN WHERE EMAIL='manon.vincent@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Ing' FROM CITIZEN WHERE EMAIL='adrien.muller@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Prof' FROM CITIZEN WHERE EMAIL='chloe.lemoine@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Hab' FROM CITIZEN WHERE EMAIL='paul.blanc@example.com';
INSERT INTO CITIZEN_TITLES (CITIZEN_ID, TITLE)
SELECT ID, 'Dr' FROM CITIZEN WHERE EMAIL='emma.perrin@example.com';

-- ===========================================
-- Téléphones -> CITIZEN_PHONE_NUMBERS (NUMBER, PHONE_TYPE)
-- ===========================================
-- Numéros principaux (Mobile)
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000001', 'Mobile' FROM CITIZEN WHERE EMAIL='jean.dupont@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000002', 'Mobile' FROM CITIZEN WHERE EMAIL='sophie.martin@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000003', 'Mobile' FROM CITIZEN WHERE EMAIL='lucas.bernard@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000004', 'Mobile' FROM CITIZEN WHERE EMAIL='claire.durand@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000005', 'Mobile' FROM CITIZEN WHERE EMAIL='thomas.petit@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000006', 'Mobile' FROM CITIZEN WHERE EMAIL='julie.robert@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000007', 'Mobile' FROM CITIZEN WHERE EMAIL='nicolas.richard@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000008', 'Mobile' FROM CITIZEN WHERE EMAIL='elodie.moreau@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000009', 'Mobile' FROM CITIZEN WHERE EMAIL='antoine.simon@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000010', 'Mobile' FROM CITIZEN WHERE EMAIL='camille.laurent@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000011', 'Mobile' FROM CITIZEN WHERE EMAIL='david.lefevre@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000012', 'Mobile' FROM CITIZEN WHERE EMAIL='alice.mercier@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000013', 'Mobile' FROM CITIZEN WHERE EMAIL='hugo.garcia@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000014', 'Mobile' FROM CITIZEN WHERE EMAIL='laura.faure@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000015', 'Mobile' FROM CITIZEN WHERE EMAIL='mathieu.roux@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000016', 'Mobile' FROM CITIZEN WHERE EMAIL='manon.vincent@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000017', 'Mobile' FROM CITIZEN WHERE EMAIL='adrien.muller@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000018', 'Mobile' FROM CITIZEN WHERE EMAIL='chloe.lemoine@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000019', 'Mobile' FROM CITIZEN WHERE EMAIL='paul.blanc@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 151 1000020', 'Mobile' FROM CITIZEN WHERE EMAIL='emma.perrin@example.com';

-- Numéros secondaires (Work) pour certains (nés avant 1990)
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000001', 'Work' FROM CITIZEN WHERE EMAIL='jean.dupont@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000002', 'Work' FROM CITIZEN WHERE EMAIL='claire.durand@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000003', 'Work' FROM CITIZEN WHERE EMAIL='nicolas.richard@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000004', 'Work' FROM CITIZEN WHERE EMAIL='david.lefevre@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000005', 'Work' FROM CITIZEN WHERE EMAIL='laura.faure@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000006', 'Work' FROM CITIZEN WHERE EMAIL='adrien.muller@example.com';
INSERT INTO CITIZEN_PHONE_NUMBERS (CITIZEN_ID, NUMBER, PHONE_TYPE)
SELECT ID, '+49 911 2000007', 'Work' FROM CITIZEN WHERE EMAIL='paul.blanc@example.com';

-- ===========================================
-- Adresses -> CITIZEN_ADDRESSES (TYPE, STREET, ZIP, CITY)
-- ===========================================
-- Adresses principales (Main)
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Hauptstrasse 10', '90402', 'Nürnberg' FROM CITIZEN WHERE EMAIL='jean.dupont@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Marktplatz 7',    '91301', 'Forchheim' FROM CITIZEN WHERE EMAIL='sophie.martin@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Königstrasse 22', '91052', 'Erlangen' FROM CITIZEN WHERE EMAIL='lucas.bernard@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Bahnhofstrasse 5', '96047', 'Bamberg' FROM CITIZEN WHERE EMAIL='claire.durand@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Friedrichstrasse 3','90408', 'Nürnberg' FROM CITIZEN WHERE EMAIL='thomas.petit@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Maximilianstrasse 14','91052','Erlangen' FROM CITIZEN WHERE EMAIL='julie.robert@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Ringstrasse 8',    '91301', 'Forchheim' FROM CITIZEN WHERE EMAIL='nicolas.richard@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Goethestrasse 18', '90489', 'Nürnberg' FROM CITIZEN WHERE EMAIL='elodie.moreau@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Markt 1',         '96047', 'Bamberg' FROM CITIZEN WHERE EMAIL='antoine.simon@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Schillerstrasse 6','90762','Fürth' FROM CITIZEN WHERE EMAIL='camille.laurent@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Weinstrasse 9',    '90409','Nürnberg' FROM CITIZEN WHERE EMAIL='david.lefevre@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Bahnhofplatz 4',   '91052','Erlangen' FROM CITIZEN WHERE EMAIL='alice.mercier@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Kaiserstrasse 10', '91301','Forchheim' FROM CITIZEN WHERE EMAIL='hugo.garcia@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Am Stadtpark 15',  '96047','Bamberg' FROM CITIZEN WHERE EMAIL='laura.faure@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Mozartstrasse 2',  '90402','Nürnberg' FROM CITIZEN WHERE EMAIL='mathieu.roux@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Beethovenallee 11','90762','Fürth' FROM CITIZEN WHERE EMAIL='manon.vincent@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Johannisstrasse 5', '91052','Erlangen' FROM CITIZEN WHERE EMAIL='adrien.muller@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Hauptmarkt 3',     '90403','Nürnberg' FROM CITIZEN WHERE EMAIL='chloe.lemoine@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Luitpoldstrasse 17','91301','Forchheim' FROM CITIZEN WHERE EMAIL='paul.blanc@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Main', 'Mozartplatz 4',    '90402','Nürnberg' FROM CITIZEN WHERE EMAIL='emma.perrin@example.com';

-- Adresses secondaires (quelques exemples)
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Secondary', 'Mozartstrasse 5', '90402', 'Nürnberg' FROM CITIZEN WHERE EMAIL='sophie.martin@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Secondary', 'Luitpoldstrasse 2','90762','Fürth' FROM CITIZEN WHERE EMAIL='claire.durand@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Secondary', 'Schillerallee 9', '91052', 'Erlangen' FROM CITIZEN WHERE EMAIL='camille.laurent@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Secondary', 'Mozartstrasse 2', '90402', 'Nürnberg' FROM CITIZEN WHERE EMAIL='manon.vincent@example.com';
INSERT INTO CITIZEN_ADDRESSES (CITIZEN_ID, TYPE, STREET, ZIP, CITY)
SELECT ID, 'Secondary', 'Kleine Gasse 3',  '90403', 'Nürnberg' FROM CITIZEN WHERE EMAIL='chloe.lemoine@example.com';

-- ================================================
-- Identifications -> CITIZEN_IDENTIFICATION
-- (ID_TYPE values: 'Card', 'Password', 'Other')
-- ================================================
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000001', '2015-01-10', '2030-01-10' FROM CITIZEN WHERE EMAIL='jean.dupont@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Password', 'PS000002', '2016-03-05', '2031-03-05' FROM CITIZEN WHERE EMAIL='sophie.martin@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000003', '2014-09-15', '2029-09-15' FROM CITIZEN WHERE EMAIL='lucas.bernard@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000004', '2018-02-20', '2033-02-20' FROM CITIZEN WHERE EMAIL='claire.durand@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Other',    'OT000005', '2017-07-12', '2032-07-12' FROM CITIZEN WHERE EMAIL='thomas.petit@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000006', '2019-04-25', '2034-04-25' FROM CITIZEN WHERE EMAIL='julie.robert@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000007', '2013-11-02', '2028-11-02' FROM CITIZEN WHERE EMAIL='nicolas.richard@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Password', 'PS000008', '2018-05-19', '2033-05-19' FROM CITIZEN WHERE EMAIL='elodie.moreau@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000009', '2016-12-01', '2031-12-01' FROM CITIZEN WHERE EMAIL='antoine.simon@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000010', '2014-01-30', '2029-01-30' FROM CITIZEN WHERE EMAIL='camille.laurent@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000011', '2017-09-09', '2032-09-09' FROM CITIZEN WHERE EMAIL='david.lefevre@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000012', '2015-06-18', '2030-06-18' FROM CITIZEN WHERE EMAIL='alice.mercier@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Other',    'OT000013', '2019-08-14', '2034-08-14' FROM CITIZEN WHERE EMAIL='hugo.garcia@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000014', '2018-10-22', '2033-10-22' FROM CITIZEN WHERE EMAIL='laura.faure@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000015', '2016-11-11', '2031-11-11' FROM CITIZEN WHERE EMAIL='mathieu.roux@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Password', 'PS000016', '2020-02-07', '2035-02-07' FROM CITIZEN WHERE EMAIL='manon.vincent@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000017', '2021-04-12', '2036-04-12' FROM CITIZEN WHERE EMAIL='adrien.muller@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000018', '2022-01-19', '2037-01-19' FROM CITIZEN WHERE EMAIL='chloe.lemoine@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Other',    'OT000019', '2019-05-29', '2034-05-29' FROM CITIZEN WHERE EMAIL='paul.blanc@example.com';
INSERT INTO CITIZEN_IDENTIFICATION (CITIZEN_ID, ID_TYPE, ID_NUMBER, ISSUED_DATE, EXPIRATION_DATE)
SELECT ID, 'Card',     'ID000020', '2020-08-23', '2035-08-23' FROM CITIZEN WHERE EMAIL='emma.perrin@example.com';

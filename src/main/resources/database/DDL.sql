CREATE TABLE USER
(
    id VARCHAR(50) PRIMARY KEY NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    name VARCHAR(200),
    token VARCHAR(200),
    secret VARCHAR(200),
    role VARCHAR(10) NOT NULL,
    sign_in_date DATETIME,
    system_creation_date DATETIME,
    twitter_creation_date DATETIME,
    followers INTEGER,
    following INTEGER,
    language VARCHAR(10),
    location VARCHAR(100),
    profile_image_url VARCHAR(500),
    verified BOOLEAN,
    locked BOOLEAN
);
CREATE UNIQUE INDEX USER_id_uindex ON USER (id);
CREATE UNIQUE INDEX USER_nickname_uindex ON USER (nickname);
ALTER TABLE USER COMMENT = 'taula d''usuaris';


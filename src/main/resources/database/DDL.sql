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
    location VARCHAR(500),
    profile_image_url VARCHAR(500),
    verified BOOLEAN,
    locked BOOLEAN
);
CREATE UNIQUE INDEX USER_id_uindex ON USER (id);
CREATE UNIQUE INDEX USER_nickname_uindex ON USER (nickname);
ALTER TABLE USER COMMENT = 'taula d''usuaris';



CREATE TABLE MONITOR
(
    id VARCHAR(40) PRIMARY KEY NOT NULL,
    author_id VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE  NOT NULL,
    twitter_query VARCHAR(200) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    start_date TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP NULL,
    next_query_string VARCHAR(1000) NULL,
    CONSTRAINT MONITOR_USER_id_fk FOREIGN KEY (author_id) REFERENCES USER (id)
);
CREATE UNIQUE INDEX MONITOR_id_uindex ON MONITOR (id);
CREATE INDEX MONITOR_creation_date_index ON MONITOR (creation_date DESC);
CREATE INDEX MONITOR_active_index ON MONITOR (active, start_date asc);
ALTER TABLE MONITOR COMMENT = 'Taula de cerques programades';


CREATE TABLE TWITTER_EXTRACTION
(
    monitor_id VARCHAR(40) NOT NULL,
    tweet_id VARCHAR(50) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    type VARCHAR(2) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    interacted_status_id VARCHAR(50),
    interacted_user_id VARCHAR(50),
    language VARCHAR(5),
    text VARCHAR(5000),
    CONSTRAINT TWITTER_EXTRACTION_monitor_id_tweet_id_pk PRIMARY KEY (monitor_id, tweet_id),
    CONSTRAINT TWITTER_EXTRACTION_MONITOR_id_fk FOREIGN KEY (monitor_id) REFERENCES MONITOR (id)
);
CREATE UNIQUE INDEX TWITTER_EXTRACTION_monitor_id_tweet_id_uindex ON TWITTER_EXTRACTION (monitor_id, tweet_id);
ALTER TABLE TWITTER_EXTRACTION COMMENT = 'Taula de dades volcades de Twitter des d''un monitor';


CREATE TABLE POLL
(
    id VARCHAR(40) PRIMARY KEY NOT NULL,
    author_id VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    start_proposals_time TIMESTAMP NOT NULL,
    end_proposals_time TIMESTAMP NOT NULL,
    end_voting_time TIMESTAMP NOT NULL,
    CONSTRAINT POLL_USER_id_fk FOREIGN KEY (author_id) REFERENCES USER (id)
);
CREATE UNIQUE INDEX POLL_id_uindex ON POLL (id);
ALTER TABLE POLL COMMENT = 'Taula d''enquestes';
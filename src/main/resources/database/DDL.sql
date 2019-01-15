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
    creation_date TIMESTAMP NULL,
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
    start_event_time TIMESTAMP NOT NULL,
    CONSTRAINT POLL_USER_id_fk FOREIGN KEY (author_id) REFERENCES USER (id)
);
CREATE UNIQUE INDEX POLL_id_uindex ON POLL (id);
ALTER TABLE POLL COMMENT = 'Taula d''enquestes';

CREATE TABLE POLL_PROPOSAL
(
    poll_id VARCHAR(40) NOT NULL,
    author_id VARCHAR(50) NOT NULL,
    hashtag VARCHAR(200) NOT NULL,
    subject VARCHAR(2000) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    CONSTRAINT POLL_PROPOSAL_poll_id_author_id_pk PRIMARY KEY (poll_id, author_id)
);
CREATE UNIQUE INDEX POLL_PROPOSAL_poll_id_author_id_uindex ON POLL_PROPOSAL (poll_id, author_id);
ALTER TABLE POLL_PROPOSAL
ADD CONSTRAINT POLL_PROPOSAL_POLL_id_fk
FOREIGN KEY (poll_id) REFERENCES POLL (id);
ALTER TABLE POLL_PROPOSAL
ADD CONSTRAINT POLL_PROPOSAL_USER_id_fk
FOREIGN KEY (author_id) REFERENCES USER (id);
ALTER TABLE POLL_PROPOSAL COMMENT = 'Taula de propostes de hashtags a votar';

CREATE TABLE POLL_VOTE
(
    poll_id VARCHAR(40) NOT NULL,
    proposal_author_id VARCHAR(50) NOT NULL,
    proposal_voter_id VARCHAR(50) NOT NULL,
    CONSTRAINT POLL_VOTE_poll_id_proposal_author_id_proposal_voter_id_pk PRIMARY KEY (poll_id, proposal_author_id, proposal_voter_id),
    CONSTRAINT POLL_VOTE_POLL_PROPOSAL_poll_id_author_id_fk FOREIGN KEY (poll_id, proposal_author_id) REFERENCES POLL_PROPOSAL (poll_id, author_id),
    CONSTRAINT POLL_VOTE_USER_id_fk FOREIGN KEY (proposal_voter_id) REFERENCES USER (id)
);
ALTER TABLE POLL_VOTE COMMENT = 'Taula de vots a propostes de hashtags';

CREATE TABLE POLL_INVITE
(
    poll_id VARCHAR(40) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    reason VARCHAR(10) NOT NULL,
    CONSTRAINT POLL_INVITE_poll_id_nickname_pk PRIMARY KEY (poll_id, nickname),
    CONSTRAINT POLL_INVITE_POLL_id_fk FOREIGN KEY (poll_id) REFERENCES POLL (id)
);
ALTER TABLE POLL_INVITE COMMENT = 'Taula de usuaris invitats a proposar hashtags';


ALTER TABLE POLL ADD end_ranking_time TIMESTAMP NULL;
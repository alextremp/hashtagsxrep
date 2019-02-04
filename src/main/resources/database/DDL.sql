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
ALTER TABLE TWITTER_EXTRACTION ADD ranked BOOLEAN DEFAULT FALSE NOT NULL;

ALTER TABLE POLL_PROPOSAL ADD cancelation_reason VARCHAR(400) NULL;
ALTER TABLE POLL_PROPOSAL ADD moderator_nickname VARCHAR(20) NULL;
ALTER TABLE POLL_PROPOSAL MODIFY COLUMN creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER moderator_nickname;

CREATE TABLE USER_GROUP
(
    id VARCHAR(20) PRIMARY KEY NOT NULL
);

insert into USER_GROUP (id) values ('TRAM01');
insert into USER_GROUP (id) values ('TRAM02');
insert into USER_GROUP (id) values ('TRAM03');
insert into USER_GROUP (id) values ('TRAM04');
insert into USER_GROUP (id) values ('TRAM05');
insert into USER_GROUP (id) values ('TRAM06');
insert into USER_GROUP (id) values ('TRAM07');
insert into USER_GROUP (id) values ('TRAM08');
insert into USER_GROUP (id) values ('TRAM09');
insert into USER_GROUP (id) values ('TRAM10');
insert into USER_GROUP (id) values ('TRAM11');
insert into USER_GROUP (id) values ('TRAM12');
insert into USER_GROUP (id) values ('TRAM13');
insert into USER_GROUP (id) values ('TRAM14');
insert into USER_GROUP (id) values ('TRAM15');
insert into USER_GROUP (id) values ('TRAM16');
insert into USER_GROUP (id) values ('TRAM17');
insert into USER_GROUP (id) values ('TRAM18');
insert into USER_GROUP (id) values ('TRAM19');
insert into USER_GROUP (id) values ('TRAM20');
insert into USER_GROUP (id) values ('TRAM21');
insert into USER_GROUP (id) values ('TRAM22');
insert into USER_GROUP (id) values ('TRAM23');
insert into USER_GROUP (id) values ('TRAM24');
insert into USER_GROUP (id) values ('TRAM25');
insert into USER_GROUP (id) values ('TRAM26');
insert into USER_GROUP (id) values ('TRAM27');
insert into USER_GROUP (id) values ('TRAM28');
insert into USER_GROUP (id) values ('TRAM29');
insert into USER_GROUP (id) values ('TRAM30');
insert into USER_GROUP (id) values ('TRAM31');
insert into USER_GROUP (id) values ('TRAM32');
insert into USER_GROUP (id) values ('TRAM33');
insert into USER_GROUP (id) values ('TRAM34');
insert into USER_GROUP (id) values ('TRAM35');
insert into USER_GROUP (id) values ('TRAM36');
insert into USER_GROUP (id) values ('TRAM37');
insert into USER_GROUP (id) values ('TRAM38');
insert into USER_GROUP (id) values ('TRAM39');
insert into USER_GROUP (id) values ('TRAM40');
insert into USER_GROUP (id) values ('TRAM41');
insert into USER_GROUP (id) values ('TRAM42');
insert into USER_GROUP (id) values ('TRAM43');
insert into USER_GROUP (id) values ('TRAM44');
insert into USER_GROUP (id) values ('TRAM45');
insert into USER_GROUP (id) values ('TRAM46');
insert into USER_GROUP (id) values ('TRAM47');
insert into USER_GROUP (id) values ('TRAM48');
insert into USER_GROUP (id) values ('TRAM49');
insert into USER_GROUP (id) values ('TRAM50');
insert into USER_GROUP (id) values ('TRAM0 VIP');

CREATE TABLE USER_MEMBERSHIP
(
    group_id VARCHAR(20),
    user_id VARCHAR(40),
    CONSTRAINT USER_MEMBERSHIP_group_id_user_id_pk PRIMARY KEY (group_id, user_id)
);

ALTER TABLE USER_MEMBERSHIP
ADD CONSTRAINT USER_MEMBERSHIP_USER_GROUP_id_fk
FOREIGN KEY (group_id) REFERENCES USER_GROUP (id);
ALTER TABLE USER_MEMBERSHIP
ADD CONSTRAINT USER_MEMBERSHIP_USER_id_fk
FOREIGN KEY (user_id) REFERENCES USER (id);

ALTER TABLE MONITOR CHANGE start_date end_date TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00';
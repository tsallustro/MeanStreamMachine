DROP TABLE IF EXISTS MEDIA;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;
drop table if exists STREAMS;

CREATE TABLE MEDIA (
    media_id UUID NOT NULL,
    title VARCHAR NOT NULL,
    canonical_name VARCHAR,
    file_format VARCHAR(10),
    upload_ts DATE,
    PRIMARY KEY (media_id)
);

create table users(
	username varchar(50) not null primary key,
	password varchar(500) not null,
	enabled boolean not null
);


create table STREAMS(
stream_id UUID not null primary key,
media_id UUID not null,
start_ts timestamp not null,
start_user varchar(16),
foreign key (media_id ) references MEDIA(media_id )
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
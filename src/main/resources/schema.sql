DROP TABLE IF EXISTS MEDIA;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

CREATE TABLE MEDIA (
    media_id UUID NOT NULL,
    title VARCHAR NOT NULL,
    canonical_name VARCHAR,
    file_format VARCHAR(10),
    upload_date DATE,
    PRIMARY KEY (media_id)
);

create table users(
	username varchar(50) not null primary key,
	password varchar(500) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
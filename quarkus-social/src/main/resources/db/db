create database quarkus_social;

create table USUARIO (
    id bigserial not null primary key,
	name varchar(100) not null,
	age integer not null
);

create table POSTS (
    id bigserial not null primary key,
	post_text varchar(150) not null,
	dateTime timestamp,
	usuario_id bigint not null references USUARIO(id)
);

create table FOLLOWERS (
    id bigserial not null primary key,
	usuario_id bigint not null references USUARIO(id),
	follower_id bigint not null references USUARIO(id)
);
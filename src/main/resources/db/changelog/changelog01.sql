--liquibase formatted sql

--changeset MarcinSz1993:1

create table user_model(
    id serial primary key ,
    email varchar(255) unique ,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    role varchar(255),
    username varchar(255) unique
);

create table section(
    id serial primary key ,
    name varchar(255),
    user_id integer references user_model(id)

);

create table section_words (
    section_id integer references section(id),
    words varchar(255)
);
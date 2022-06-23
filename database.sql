CREATE TABLE services(
	id serial primary key,
	name varchar(255) unique
);

CREATE TABLE places(
	id serial primary key,
	name varchar(255) unique
);

CREATE TABLE users(
	id serial primary key,
	firstname varchar(255) NOT NULL,
	lastname varchar(255) NOT NULL,
	cellphone varchar(15) NULL,
	deskphone varchar(15) NULL,
	email varchar(255) NOT NULL,
	service int references services,
	place int references places
);

CREATE TABLE t_user(
	id serial NOT NULL,
	user_email varchar(30) NOT NULL,
	user_password text NOT NULL,
	full_name varchar(30) NOT NUll
);

ALTER TABLE t_user ADD CONSTRAINT t_user_pk PRIMARY KEY(id);

INSERT INTO t_user(user_email ,user_password, full_name) VALUES('system','system','system');
--drop table account;
--drop table costumer;
--drop table transaction;
--drop table transaction_transfer;

CREATE TABLE account(
	acc_number varchar(13) not null,
	acc_owner int NOT NULL,
	acc_date_created timestamp NOT NULL,
	acc_balance decimal(10,3) NOT NUll
);

ALTER table account ADD CONSTRAINT account_pk PRIMARY KEY(acc_number);
ALTER TABLE account
	ADD CONSTRAINT acc_owner_fk FOREIGN KEY(acc_owner)
	REFERENCES costumer(id);

insert into account(acc_number, acc_owner, acc_date_created, acc_balance) values (1111111111111, '1', now(), 100.000);
insert into account(acc_number, acc_owner, acc_date_created, acc_balance) values (1111111111112, '2', now(), 100.000);
insert into account(acc_number, acc_owner, acc_date_created, acc_balance) values (1111111111113, '3', now(), 100.000);
insert into account(acc_number, acc_owner, acc_date_created, acc_balance) values (1111111111114, '4', now(), 100.000);

CREATE TABLE costumer(
	id serial NOT NULL,
	cust_first_name varchar(30) NOT NULL,
	cust_last_name varchar(30),
	cust_birthdate date,
	cust_gender int not NULL,
	cust_address varchar(50),
	cust_city varchar(20),
	cust_postcode char(5)
);

ALTER table costumer ADD constraint costumer_pk PRIMARY KEY(id);

insert into costumer(cust_first_name, cust_gender) values ('Dummy 1', 1);
insert into costumer(cust_first_name, cust_gender) values ('Dummy 2', 1);
insert into costumer(cust_first_name, cust_gender) values ('Dummy 3', 2);
insert into costumer(cust_first_name, cust_gender) values ('Dummy 4', 2);

CREATE TABLE transaction(
	id serial NOT NULL,
	trs_from_account varchar(13) NOT NULL,
	trs_date date not NULL,
	trs_amount decimal(10,3) not null,
	trs_type char(2) not null
);

ALTER table transaction ADD constraint transaction_pk PRIMARY KEY(id);
ALTER TABLE transaction
	ADD CONSTRAINT trs_from_acc_fk FOREIGN KEY(trs_from_account)
	REFERENCES account(acc_number);

insert into transaction(trs_from_account, trs_date, trs_amount, trs_type) values('1111111111111', now(), 10.000, 'DB');
insert into transaction(trs_from_account, trs_date, trs_amount, trs_type) values('1111111111112', now(), 20.000, 'CR');
insert into transaction(trs_from_account, trs_date, trs_amount, trs_type) values('1111111111113', now(), 30.000, 'TF');
insert into transaction(trs_from_account, trs_date, trs_amount, trs_type) values('1111111111114', now(), 40.000, 'DB');

CREATE TABLE transaction_transfer(
	id serial NOT NULL,
	trs_to_account char(13) NOT NULL,
	trs_status int not null
);

ALTER table transaction_transfer ADD constraint transaction_transfer_pk PRIMARY KEY(id);
ALTER TABLE transaction_transfer
	ADD CONSTRAINT id_fk FOREIGN KEY(id)
	REFERENCES transaction(id);
ALTER TABLE transaction_transfer
	ADD CONSTRAINT trs_to_acc_fk FOREIGN KEY(trs_to_account)
	REFERENCES account(acc_number);

insert into transaction_transfer(trs_to_account, trs_status) values ('1111111111112', 1);
insert into transaction_transfer(trs_to_account, trs_status) values ('1111111111113', 0);
insert into transaction_transfer(trs_to_account, trs_status) values ('1111111111114', -1);
insert into transaction_transfer(trs_to_account, trs_status) values ('1111111111111', 1);

--select * from transaction_transfer tt;
--select * from transaction;
--select * from costumer;
--select * from account;
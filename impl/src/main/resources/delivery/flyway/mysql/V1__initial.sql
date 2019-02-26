create table dlv_delivery (
	id binary(16) not null,
	created_date datetime,
	_key varchar(100),
	subject_id varchar(50),
	primary key (id)
) engine=InnoDB;

create table dlv_delivery_result (
	id binary(16) not null,
	address varchar(50),
	delivery_id binary(16),
	method varchar(20),
	requested_date datetime,
	requester_id varchar(50),
	successful bit not null,
	primary key (id)
) engine=InnoDB;

create table dlv_delivery_subject (
	id varchar(50) not null,
	body_template longtext,
	created_by_id varchar(50),
	created_by_name varchar(50),
	created_date datetime,
	last_modified_by_id varchar(50),
	last_modified_by_name varchar(50),
	last_modified_date datetime,
	name varchar(50),
	title_template varchar(150),
	primary key (id)
) engine=InnoDB;

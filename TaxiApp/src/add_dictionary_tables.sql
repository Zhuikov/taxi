use taxi_db2;

drop table if exists OrderStatus;
drop table if exists DriverStatus;
drop table if exists MessageType;

-- -------------------------------------------
create table OrderStatus (
    id int not null,
    status varchar(16),
    primary key (id)
);

insert into OrderStatus (id, status)
values
    (0, "WAIT_FOR_DRIVER"),
    (1, "ACCEPTED"),
    (2, "FINISHED"),
    (3, "DECLINED")
;
-- -------------------------------------------

-- -------------------------------------------
create table DriverStatus (
    id int not null,
	status varchar(8),
	primary key (id)
);

insert into DriverStatus (id, status)
values
	(0, "FREE"),
	(1, "BUSY")
;
-- -------------------------------------------

-- -------------------------------------------
create table MessageType (
	id int not null,
	message_type varchar(8)
);

insert into MessageType (id, message_type)
values
	(0, "ACK"),
	(1, "NACK"),
	(2, "ORDER"),
	(3, "DRIVER"),
	(4, "CV")
;


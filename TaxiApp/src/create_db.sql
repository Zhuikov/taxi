use taxi_db;

create table if not exists Users (
    id int auto_increment primary key,
    `name` varchar(32),
    surname varchar(32),
    login varchar(32),
    phone varchar(11)
);

create table if not exists Owners (
    id_user int primary key,
    foreign key (id_user) references Users(id)
);

create table if not exists Drivers (
    id_user int primary key,
    active bool,
    `online` bool,
    foreign key (id_user) references Users(id)
);

create table if not exists Managers (
	id_user int primary key,
    foreign key (id_user) references Users(id)
);

create table if not exists Clients (
	id_user int primary key,
    foreign key (id_user) references Users(id)
);


create table if not exists OrderStatus (
    id int not null primary key,
    `status` varchar(16)
);
insert into OrderStatus (id, `status`)
values
    (0, "WAIT_FOR_DRIVER"),
    (1, "ACCEPTED"),
    (2, "FINISHED"),
    (3, "DECLINED")
	on duplicate key update id = id
;
create table if not exists Orders (
    id int not null auto_increment primary key,
    dstAddress varchar(128),
    srcAddress varchar(128),
    `status` int,
    id_driver int,
    id_client int,
    foreign key (`status`) references OrderStatus(id),
    foreign key (id_driver) references Drivers(id_user),
    foreign key (id_client) references Clients(id_user)
);


create table if not exists Cars (
    id int not null auto_increment primary key,
	id_owner int,
    license_plate varchar(9),
	model varchar(32),
    foreign key (id_owner) references Owners(id_user)
);

create table if not exists CVs (
    id int not null auto_increment primary key,
    `name` varchar(32),
    surname varchar(32),
    phone varchar(11),
    experience tinyint,
    `read` bool
);


create table if not exists CarToDriver (
	id_driver int,
    id_car int,
    foreign key (id_driver) references Drivers(id_user),
    foreign key (id_car) references Cars(id)
);

create table if not exists MessageType (
    id int not null primary key,
    message_type varchar(16)
);
insert into MessageType (id, message_type)
values
    (0, "ACK"),
    (1, "NACK"),
    (2, "ORDER"),
    (3, "DRIVER"),
    (4, "CV")
	on duplicate key update id = id
;
create table if not exists Messages (
    id int not null auto_increment primary key,
    id_user_from int,
    id_user_to int,
    message_type int,
    payload int,
    foreign key (id_user_from) references Users(id),
    foreign key (id_user_to) references Users(id),
    foreign key (message_type) references MessageType(id)
    
-- 	  type		 payload
--   "DRIVER"	id_driver
--   "ACK/NACK"	-
--   "CV"	id_cv
--   "ORDER"	id_order
);








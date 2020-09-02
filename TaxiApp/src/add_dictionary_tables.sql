use taxi_db;

drop table if exists OrderStatus;

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
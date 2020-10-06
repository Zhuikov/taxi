use taxi_db2;

insert into Cars (id, licensePlate, model, id_owner)
values
    (1, "C432KO178", "Kia Rio", 3),
    (2, "K999OA78", "Opel Astra", 3),
    (3, "O432AO78", "Lada Sedan", 3)
;

insert into Users (id, login, `name`, surname, phone)
values
    (1, "driver1", "Ivan", "Ivanov", "89223347832"),
    (2, "driver2", "Alex", "Alex", "89214320123"),
    (3, "owner", "Andrew", "Losev", "89123428989")
;

insert into Drivers (`active`, status, id_user, id_car, id_order)
values
    (true, 0, 1, 1, null),
    (true, 0, 2, 2, null)
;
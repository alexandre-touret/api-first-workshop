-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
insert into Guitar (id,name,type,price,stock) values (999,'ES 335','ELECTRIC',2500,10);

-- insert into GuitarOrder (discountRequested, totalPrice, createdAt, id, orderId) values (100, 2500, '2004-10-19 10:23:54+02', 999, gen_random_uuid());
insert into GuitarOrder (discountRequested, totalPrice, createdAt, id, orderId)
values (100, 2500, '2004-10-19 10:23:54+02', 999, '292a485f-a56a-4938-8f1a-bbbbbbbbbbb1'::UUID);

insert into Guitar_GuitarOrder(orders_id, guitars_id)
values (999, 999);

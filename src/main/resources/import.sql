-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (1, gen_random_uuid(), 'ES 335', 'ELECTRIC', 2500, 10);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (2, gen_random_uuid(), 'Stratocaster', 'ELECTRIC', 1500, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (3, gen_random_uuid(), 'Les Paul', 'ELECTRIC', 3000, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (4, gen_random_uuid(), 'Yamaha C40', 'CLASSIC', 100, 20);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (5, gen_random_uuid(), 'Martin D-28', 'FOLK', 3500, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (6, gen_random_uuid(), 'Selmer Maccaferri', 'GIPSY', 4500, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (7, gen_random_uuid(), 'Gibson SG', 'ELECTRIC', 2000, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (8, gen_random_uuid(), 'Ibanez RG', 'ELECTRIC', 1100, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (9, gen_random_uuid(), 'Fender Telecaster', 'ELECTRIC', 1800, 8);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (10, gen_random_uuid(), 'Gibson J-45', 'FOLK', 3200, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (11, gen_random_uuid(), 'Taylor 314ce', 'FOLK', 2100, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (12, gen_random_uuid(), 'Takamine GD20-NS', 'FOLK', 500, 15);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (13, gen_random_uuid(), 'Cordoba C5', 'CLASSIC', 300, 10);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (14, gen_random_uuid(), 'Flamenco Negra', 'CLASSIC', 1500, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (15, gen_random_uuid(), 'Gibson L-5', 'JAZZ', 7000, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (16, gen_random_uuid(), 'PRS Custom 24', 'ELECTRIC', 4000, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (17, gen_random_uuid(), 'Jackson Soloist', 'ELECTRIC', 1800, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (18, gen_random_uuid(), 'Epiphone Dot', 'ELECTRIC', 800, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (19, gen_random_uuid(), 'Yamaha Pacifica', 'ELECTRIC', 600, 12);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (20, gen_random_uuid(), 'Gretsch White Falcon', 'ELECTRIC', 6000, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (21, gen_random_uuid(), 'Rickenbacker 330', 'ELECTRIC', 2200, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (22, gen_random_uuid(), 'Washburn HB35', 'ELECTRIC', 700, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (23, gen_random_uuid(), 'Gibson Hummingbird', 'FOLK', 3200, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (24, gen_random_uuid(), 'Seagull S6', 'FOLK', 400, 8);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (25, gen_random_uuid(), 'Ovation Celebrity', 'FOLK', 800, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (26, gen_random_uuid(), 'Guild D-55', 'FOLK', 3500, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (27, gen_random_uuid(), 'Breedlove Oregon', 'FOLK', 2300, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (28, gen_random_uuid(), 'Godin Multiac', 'CLASSIC', 1200, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (29, gen_random_uuid(), 'Alhambra 5P', 'CLASSIC', 700, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (30, gen_random_uuid(), 'Kremona Fiesta', 'CLASSIC', 900, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (31, gen_random_uuid(), 'Ramirez 1a', 'CLASSIC', 8000, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (32, gen_random_uuid(), 'Takamine TC132SC', 'CLASSIC', 1400, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (33, gen_random_uuid(), 'ESP Eclipse', 'ELECTRIC', 2500, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (34, gen_random_uuid(), 'Charvel Pro-Mod', 'ELECTRIC', 1200, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (35, gen_random_uuid(), 'Dean ML', 'ELECTRIC', 900, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (36, gen_random_uuid(), 'Music Man JP15', 'ELECTRIC', 3500, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (37, gen_random_uuid(), 'Schecter Hellraiser', 'ELECTRIC', 1000, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (38, gen_random_uuid(), 'Gibson Firebird', 'ELECTRIC', 2300, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (39, gen_random_uuid(), 'Fender Mustang', 'ELECTRIC', 1000, 8);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (40, gen_random_uuid(), 'Squier Classic Vibe', 'ELECTRIC', 400, 10);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (41, gen_random_uuid(), 'Epiphone Les Paul', 'ELECTRIC', 600, 9);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (42, gen_random_uuid(), 'Fender Jazzmaster', 'ELECTRIC', 1600, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (43, gen_random_uuid(), 'PRS SE Custom 24', 'ELECTRIC', 800, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (44, gen_random_uuid(), 'Ibanez JEM', 'ELECTRIC', 2800, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (45, gen_random_uuid(), 'Yamaha FG800', 'FOLK', 200, 20);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (46, gen_random_uuid(), 'Martin LX1E', 'FOLK', 500, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (47, gen_random_uuid(), 'Guild Starfire IV', 'ELECTRIC', 1600, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (48, gen_random_uuid(), 'Fender Duo-Sonic', 'ELECTRIC', 1000, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (49, gen_random_uuid(), 'Gibson ES-175', 'JAZZ', 4000, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (50, gen_random_uuid(), 'Ibanez Artcore', 'JAZZ', 600, 8);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (51, gen_random_uuid(), 'Epiphone Casino', 'JAZZ', 900, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (52, gen_random_uuid(), 'Godin 5th Avenue', 'JAZZ', 1200, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (53, gen_random_uuid(), 'Yamaha A3R', 'FOLK', 800, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (54, gen_random_uuid(), 'Fender Malibu', 'FOLK', 700, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (55, gen_random_uuid(), 'Gibson J-200', 'FOLK', 4000, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (56, gen_random_uuid(), 'Taylor GS Mini', 'FOLK', 600, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (57, gen_random_uuid(), 'Breedlove  Pursuit', 'FOLK', 900, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (58, gen_random_uuid(), 'Seagull Artist', 'FOLK', 1200, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (59, gen_random_uuid(), 'Cordoba Fusion', 'CLASSIC', 700, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (60, gen_random_uuid(), 'Kremona Verea', 'CLASSIC', 1000, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (61, gen_random_uuid(), 'Ramirez R4', 'CLASSIC', 2000, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (62, gen_random_uuid(), 'Takamine GC5CE', 'CLASSIC', 600, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (63, gen_random_uuid(), 'Alhambra 7C', 'CLASSIC', 1300, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (64, gen_random_uuid(), 'Godin A6 Ultra', 'CLASSIC', 1500, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (65, gen_random_uuid(), 'Yamaha CG192S', 'CLASSIC', 800, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (66, gen_random_uuid(), 'Gibson ES-335', 'ELECTRIC', 3000, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (67, gen_random_uuid(), 'ESP Horizon', 'ELECTRIC', 2700, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (68, gen_random_uuid(), 'Fender Lead II', 'ELECTRIC', 1200, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (69, gen_random_uuid(), 'Gretsch G2622', 'ELECTRIC', 700, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (70, gen_random_uuid(), 'Yamaha APX600', 'FOLK', 400, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (71, gen_random_uuid(), 'Taylor 210ce', 'FOLK', 1500, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (72, gen_random_uuid(), 'Gibson J-15', 'FOLK', 1700, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (73, gen_random_uuid(), 'Ibanez AW54CE', 'FOLK', 300, 8);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (74, gen_random_uuid(), 'Breedlove Discovery', 'FOLK', 600, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (75, gen_random_uuid(), 'Seagull Maritime', 'FOLK', 1000, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (76, gen_random_uuid(), 'Cordoba C9', 'CLASSIC', 1300, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (77, gen_random_uuid(), 'Kremona Sofia', 'CLASSIC', 800, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (78, gen_random_uuid(), 'Ramirez R1', 'CLASSIC', 1500, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (79, gen_random_uuid(), 'Takamine P3FCN', 'CLASSIC', 1600, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (80, gen_random_uuid(), 'Alhambra 9P', 'CLASSIC', 1800, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (81, gen_random_uuid(), 'Gibson Firebird V', 'ELECTRIC', 2500, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (82, gen_random_uuid(), 'Fender Jag-Stang', 'ELECTRIC', 1300, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (83, gen_random_uuid(), 'Epiphone SG', 'ELECTRIC', 500, 10);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (84, gen_random_uuid(), 'Ibanez Talman', 'ELECTRIC', 700, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (85, gen_random_uuid(), 'Jackson Dinky', 'ELECTRIC', 1000, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (86, gen_random_uuid(), 'Dean Cadillac', 'ELECTRIC', 1200, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (87, gen_random_uuid(), 'PRS Santana', 'ELECTRIC', 3000, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (88, gen_random_uuid(), 'Music Man Silhouette', 'ELECTRIC', 2000, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (89, gen_random_uuid(), 'Gibson Explorer', 'ELECTRIC', 2200, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (90, gen_random_uuid(), 'Fender Coronado', 'ELECTRIC', 1600, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (91, gen_random_uuid(), 'Yamaha TRBX304', 'ELECTRIC', 350, 8);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (92, gen_random_uuid(), 'Gretsch G6120', 'ELECTRIC', 2900, 1);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (93, gen_random_uuid(), 'Ibanez SR505', 'ELECTRIC', 700, 6);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (94, gen_random_uuid(), 'Fender Bass VI', 'ELECTRIC', 1400, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (95, gen_random_uuid(), 'Gibson EB', 'ELECTRIC', 1200, 2);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (96, gen_random_uuid(), 'Fender Precision', 'ELECTRIC', 1500, 4);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (97, gen_random_uuid(), 'Music Man StingRay', 'ELECTRIC', 2000, 3);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (98, gen_random_uuid(), 'Epiphone Thunderbird', 'ELECTRIC', 500, 5);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (99, gen_random_uuid(), 'Yamaha BB434', 'ELECTRIC', 600, 7);
INSERT INTO Guitar (id, guitarId, name, type, price, stock)
VALUES (100, gen_random_uuid(), 'Gretsch G2220', 'ELECTRIC', 400, 6);

alter sequence Guitar_seq restart with 101;



insert into GuitarOrder (discountRequested, createdAt, id, orderId)
values (100,  '2004-10-19 10:23:54+02', 999, '292a485f-a56a-4938-8f1a-bbbbbbbbbbb1'::UUID);

insert into Guitar_GuitarOrder(orders_id, guitars_id)
values (999, 100);

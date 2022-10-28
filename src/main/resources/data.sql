INSERT INTO roles(rolename) VALUES('MECHANIC'), ('ADMIN'), ('CASHIER');
INSERT INTO users(email, firstname, lastname, password, username) VALUES('testgebruiker@hotmail.com','Test','Gebruiker','$2a$10$lJNiAqSUnalatf0vJg.3jOsTqv0OVavT3402SFxp0gN/QmE5v4y8C','testgebruiker');
insert into users_roles(users_id, roles_id) values (1,2);
insert into car_parts(name, price,serial_number) values("ketting","2029.03","SN033952312");
insert into car_parts(name, price,serial_number) values("turbo","830.29","SN036754512");
insert into car_parts(name, price,serial_number) values("Vredestijn band","150","SN0375674512");
insert into car_parts(name, price,serial_number) values("Koplamp","90.35","SN036754656512");
insert into car_parts(name, price,serial_number) values("Uitlaat demper","440.10","SN65339124");

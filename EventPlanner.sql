DROP TABLE IF EXISTS Attendee;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS User;

CREATE TABLE IF NOT EXISTS User (
                                    user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) UNIQUE NOT NULL,
                                    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Event (
                                     event_id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                     event_name VARCHAR(255) NOT NULL,
                                     event_date DATE NOT NULL,
                                     event_time TIME,
                                     event_location VARCHAR(255) NOT NULL,
                                     event_description VARCHAR(255),
                                     event_type VARCHAR(255) NOT NULL,
                                     user_id INTEGER,
                                     FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE IF NOT EXISTS Attendee (
                                        attendee_id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                        event_id INTEGER NOT NULL,
                                        attendee_name VARCHAR(255) NOT NULL,
                                        FOREIGN KEY (event_id) REFERENCES Event(event_id)
);


insert into User (username, password) values ( 'admin', 'admin');
insert into User ( username, password) values ( 'Habiba', '1234');
insert into User (username, password) values ( 'Maha', '1234');
insert into User (username, password) values ('Nour', '1234');
insert into User (username, password) values ('Sara', '1234');


insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Graduation', '2021-06-01', '10:00:00', 'Cairo', 'Graduation Ceremony', 'Graduation', 1);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Wedding', '2021-07-01', '10:00:00', 'Cairo', 'Wedding Ceremony', 'Wedding', 1);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Birthday', '2021-08-01', '10:00:00', 'Cairo', 'Birthday Party', 'Birthday', 1);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Graduation', '2021-06-01', '10:00:00', 'Cairo', 'Graduation Ceremony', 'Graduation', 2);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Wedding', '2021-07-01', '10:00:00', 'Cairo', 'Wedding Ceremony', 'Wedding', 2);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Birthday', '2021-08-01', '10:00:00', 'Cairo', 'Birthday Party', 'Birthday', 2);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Graduation', '2021-06-01', '10:00:00', 'Cairo', 'Graduation Ceremony', 'Graduation', 3);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Wedding', '2021-07-01', '10:00:00', 'Cairo', 'Wedding Ceremony', 'Wedding', 3);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Birthday', '2021-08-01', '10:00:00', 'Cairo', 'Birthday Party', 'Birthday', 3);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Graduation', '2021-06-01', '10:00:00', 'Cairo', 'Graduation Ceremony', 'Graduation', 4);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Wedding', '2021-07-01', '10:00:00', 'Cairo', 'Wedding Ceremony', 'Wedding', 4);
insert into Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id) values ('Birthday', '2021-08-01', '10:00:00', 'Cairo', 'Birthday Party', 'Birthday', 4);

insert into Attendee (event_id, attendee_name) values (1, 'Habiba');
insert into Attendee (event_id, attendee_name) values (1, 'Maha');
insert into Attendee (event_id, attendee_name) values (1, 'Nour');
insert into Attendee (event_id, attendee_name) values (1, 'Sara');
insert into Attendee (event_id, attendee_name) values (2, 'Habiba');
insert into Attendee (event_id, attendee_name) values (2, 'Maha');
insert into Attendee (event_id, attendee_name) values (2, 'Nour');
insert into Attendee (event_id, attendee_name) values (2, 'Sara');
insert into Attendee (event_id, attendee_name) values (3, 'Habiba');
insert into Attendee (event_id, attendee_name) values (3, 'Maha');
insert into Attendee (event_id, attendee_name) values (3, 'Nour');
insert into Attendee (event_id, attendee_name) values (3, 'Sara');
insert into Attendee (event_id, attendee_name) values (4, 'Habiba');
insert into Attendee (event_id, attendee_name) values (4, 'Maha');
insert into Attendee (event_id, attendee_name) values (4, 'Nour');
insert into Attendee (event_id, attendee_name) values (4, 'Sara');
insert into Attendee (event_id, attendee_name) values (5, 'Habiba');
insert into Attendee (event_id, attendee_name) values (5, 'Maha');
insert into Attendee (event_id, attendee_name) values (5, 'Nour');
insert into Attendee (event_id, attendee_name) values (5, 'Sara');
insert into Attendee (event_id, attendee_name) values (6, 'Habiba');
insert into Attendee (event_id, attendee_name) values (6, 'Maha');
insert into Attendee (event_id, attendee_name) values (6, 'Nour');
insert into Attendee (event_id, attendee_name) values (6, 'Sara');
insert into Attendee (event_id, attendee_name) values (7, 'Habiba');
insert into Attendee (event_id, attendee_name) values (7, 'Maha');
insert into Attendee (event_id, attendee_name) values (7, 'Nour');
insert into Attendee (event_id, attendee_name) values (7, 'Sara');
insert into Attendee (event_id, attendee_name) values (8, 'Habiba');
insert into Attendee (event_id, attendee_name) values (8, 'Maha');
insert into Attendee (event_id, attendee_name) values (8, 'Nour');
insert into Attendee (event_id, attendee_name) values (8, 'Sara');
insert into Attendee (event_id, attendee_name) values (9, 'Habiba');
insert into Attendee (event_id, attendee_name) values (9, 'Maha');
insert into Attendee (event_id, attendee_name) values (9, 'Nour');
insert into Attendee (event_id, attendee_name) values (9, 'Sara');


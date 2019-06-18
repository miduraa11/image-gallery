INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');

INSERT INTO users(user_id, name, username, email, password) VALUES(1, 'Adam', 'admin', 'admin@gmail.com', '$2a$10$Z4QFIG3Z9EuW9h.Ll4qinOdQFTxRM5VIw8ccMorS3Jd3wyMIafaJ.');
INSERT INTO users(user_id, name, username, email, password) VALUES(2, 'Adam', 'user', 'user@gmail.com', '$2a$10$Z4QFIG3Z9EuW9h.Ll4qinOdQFTxRM5VIw8ccMorS3Jd3wyMIafaJ.');


INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);

INSERT INTO people(people_id, name, user_id) VALUES(1, 'Adam Midura', 1);
INSERT INTO people(people_id, name, user_id) VALUES(2, 'Krystian Ciejka', 1);
INSERT INTO people(people_id, name, user_id) VALUES(3, 'Kamil Burczy', 1);

INSERT INTO tag(tag_id, name) VALUES(1, 'Architektura');
INSERT INTO tag(tag_id, name) VALUES(2, 'Ludzie');
INSERT INTO tag(tag_id, name) VALUES(3, 'Widoki');
INSERT INTO tag(tag_id, name) VALUES(4, 'Wydarzenia');
INSERT INTO tag(tag_id, name) VALUES(5, 'Zwierzęta');
INSERT INTO tag(tag_id, name) VALUES(6, 'Sztuka');
INSERT INTO tag(tag_id, name) VALUES(7, 'Humor');
INSERT INTO tag(tag_id, name) VALUES(8, 'Inne');
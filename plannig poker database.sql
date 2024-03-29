drop database pp;
create database pp;
show databases;
use pp;
show tables;

alter table card
rename column image_url to url

CREATE TABLE room
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    password1 VARCHAR(255) NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    url       VARCHAR(255) NOT NULL
);

CREATE TABLE card
(
    id        BIGINT PRIMARY KEY,
	image_url varchar(255)
);

CREATE TABLE user
(
    username VARCHAR(255) NOT NULL PRIMARY KEY,
    image    VARCHAR(255) NULL,
    room_id  BIGINT       NULL,
    FOREIGN KEY (room_id)
        REFERENCES room (id)
);

CREATE TABLE task
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title        VARCHAR(255)                      NOT NULL,
    description1 VARCHAR(255)                      NOT NULL,
    due          DATETIME(6)                       NOT NULL,
    estimation   INT                               NULL,
    username     VARCHAR(255)                      NULL,
    room_id      BIGINT                            NULL,
    FOREIGN KEY (room_id)
        REFERENCES room (id),
    FOREIGN KEY (username)
        REFERENCES user (username)
);

CREATE TABLE vote
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    estimation INT                               NULL,
    task_id    BIGINT                            NOT NULL,
    username   VARCHAR(255)                      NOT NULL,
    FOREIGN KEY (task_id)
        REFERENCES task (id),
    FOREIGN KEY (username)
        REFERENCES user (username)
);

-- Inserting data into the room table
INSERT INTO room (password1, room_name, url)
VALUES ('123', 'Room A', 'http://localhost:5173/room/1'),
       ('123', 'Room B', 'http://localhost:5173/room/1');

-- Inserting data into the user table
INSERT INTO user (username, image)
VALUES ('john_doe', 'john_doe.jpg'),
       ('jane_smith', 'jane_smith.jpg'),
       ('alice_johnson', 'alice_johnson.jpg');
       
INSERT INTO card (id, image_url)
VALUES 
(0, '../images/Moon.png'),
(1, '../images/Mercury.png'),
(2, '../images/Mars.png'),
(3, '../images/Venus.png'),
(5, '../images/Earth.png'),
(8, '../images/Neptune.png'),
(13, '../images/Uranus.png'),
(20, '../images/Saturn.png'),
(40, '../images/Jupiter.png'),
(100, '../images/Black-hole.png');

select * from card

-- Inserting data into the task table
INSERT INTO task (title, description1, due, estimation, username, room_id)
VALUES ('Task 1', 'Description for Task 1', '2022-01-20 15:30:00.000000', 5, 'john_doe', 1),
       ('Task 2', 'Description for Task 2', '2022-01-22 10:45:00.000000', 8, 'jane_smith', 1),
       ('Task 3', 'Description for Task 3', '2022-01-25 18:00:00.000000', 3, 'john_doe', 2),
       ('Task 4', 'Description for Task 4', '2022-01-30 12:00:00.000000', 7, 'alice_johnson', 2);

-- Inserting data into the vote table
INSERT INTO vote (estimation, task_id, username)
VALUES (5, 1, 'Dulguun');


SELECT *
FROM task;

SELECT *
FROM user;

SELECT *
FROM room;

SELECT *
FROM vote;

select *
from user_joined_room;

show create table task;
show create table user;
show create table room;
show create table vote;
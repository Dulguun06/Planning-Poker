drop database pp;
create database pp;
show databases;
use pp;
show tables;

CREATE TABLE room
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    password1 VARCHAR(255) NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    url       VARCHAR(255) NOT NULL
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
VALUES ('pass123', 'Room A', 'https://rooma.example.com'),
       ('pass456', 'Room B', 'https://roomb.example.com');

-- Inserting data into the user table
INSERT INTO user (username, image)
VALUES ('john_doe', 'john_doe.jpg'),
       ('jane_smith', 'jane_smith.jpg'),
       ('alice_johnson', 'alice_johnson.jpg');

-- Inserting data into the task table
INSERT INTO task (title, description1, due, estimation, username, room_id)
VALUES ('Task 1', 'Description for Task 1', '2022-01-20 15:30:00.000000', 5, 'john_doe', 1),
       ('Task 2', 'Description for Task 2', '2022-01-22 10:45:00.000000', 8, 'jane_smith', 1),
       ('Task 3', 'Description for Task 3', '2022-01-25 18:00:00.000000', 3, 'john_doe', 2),
       ('Task 4', 'Description for Task 4', '2022-01-30 12:00:00.000000', 7, 'alice_johnson', 2);

-- Inserting data into the vote table
INSERT INTO vote (estimation, task_id, username)
VALUES (5, 1, 'john_doe'),
       (8, 2, 'jane_smith'),
       (3, 3, 'john_doe'),
       (7, 4, 'alice_johnson');


SELECT *
FROM task;

SELECT *
FROM user;

SELECT *
FROM room;

SELECT *
FROM vote;

show create table task;
show create table user;
show create table room;
show create table vote;

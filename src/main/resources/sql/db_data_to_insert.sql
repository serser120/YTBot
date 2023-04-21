--РОЛИ
insert into roles (id, description, title)
values (1, 'Роль пользователя', 'USER');
insert into roles (id, description, title)
values (2, 'Роль модератора', 'MODERATOR');

--Юзеры
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), 'kudrin_sergey@mail.ru', 'Сергей',
        'Кудрин', 'serser120', 12345, 2);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '111111111111@mail.ru', 'Иван',
        'Иванов', '11111', 11111, 1);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '2222222222222@mail.ru', 'Петр',
        'Петров', '22222', 22222, 1);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '33333333333@mail.ru', 'Александр',
        'Александров', '33333', 33333, 1);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '4444444444@mail.ru', 'Семен',
        'Семенов', '44444', 44444, 2);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '5555555@mail.ru', 'Виктор',
        'Викторов', '55555', 55555, 2);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '66666@mail.ru', 'Вадим',
        'Вадимов', '66666', 66666, 1);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '77777@mail.ru', 'Дмитрий',
        'Дмитриев', '77777', 77777, 1);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '88888@mail.ru', 'Вадим',
        'Вадимов', '88888', 88888, 1);
INSERT INTO users (id, email, first_name, last_name, login, password, role_id)
VALUES (nextval('user_seq'), '99999@mail.ru', 'Елена',
        'Ленина', '99999', 99999, 2);

--Серверы
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 1079412334373650494, 'Тестовая песочница');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 1088837606177001505, 'Test2');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 2222222222222222222, 'Route66');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 3333333333333333333, 'APEX');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 4444444444444444444, 'Test5');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 5555555555555555555, 'Test6');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 6666666666666666666, 'Test7');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 7777777777777777777, 'Test8');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 8888888888888888888, 'Test9');
INSERT INTO servers (id, discord_id, name)
VALUES (nextval('server_seq'), 9999999999999999999, 'Test10');

--Видео
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 06 : 53', 'Mick Gordon  The Only Thing They Fear Is You  DOOM Eternal OST High Quality',
        'https://www.youtube.com/watch?v=EQmIBHObtCs', 'https://www.youtube.com/embed/EQmIBHObtCs');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 04 : 16', 'Foster The People - Pumped Up Kicks (Official Video)',
        'https://www.youtube.com/watch?v=SDTZ7iX4vTQ', 'https://www.youtube.com/embed/SDTZ7iX4vTQ');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 03 : 15', 'Aaron Smith - Dancin (KRONO Remix) - Lyrics',
        'https://www.youtube.com/watch?v=Cjp6RVrOOW0', 'https://www.youtube.com/embed/Cjp6RVrOOW0');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1000,'10 : 00 : 01', '10 ЧАСОВ АБСОЛЮТНОЙ ТИШИНЫ !!!',
        'https://www.youtube.com/watch?v=0idvYIGCiG8', 'https://www.youtube.com/embed/0idvYIGCiG8');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 01 : 44', 'Enjoykin — Котлетки с Пюрешкой',
        'https://www.youtube.com/watch?v=A1Qb4zfurA8', 'https://www.youtube.com/embed/A1Qb4zfurA8');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 02 : 47', 'SOVIET MARCH - Red Alert 3 - RUSSIAN COVER (Composer James Hannigan)',
        'https://www.youtube.com/watch?v=GKvlt6rpb4Y', 'https://www.youtube.com/embed/GKvlt6rpb4Y');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '10 : 00 : 00', 'Space Doggo 10 Hours',
        'https://www.youtube.com/watch?v=iC1PLC6ljJc', 'https://www.youtube.com/embed/iC1PLC6ljJc');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '3 : 03 : 43', 'PHONK MIX 2023 ※ Phonk 4 Night Drive Mix (Lxst Cxntury Type) | STANCE | JDM | DRIFT ※  Фонк 2023',
        'https://www.youtube.com/watch?v=hSkB9fPQ-go', 'https://www.youtube.com/embed/hSkB9fPQ-go');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 26 : 55', '30 minutes of kirby music to make you feel better 😃',
        'https://www.youtube.com/watch?v=w6JsCnK1Z-M', 'https://www.youtube.com/embed/w6JsCnK1Z-M');
INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier)
VALUES (nextval('video_seq'), 1, '0 : 04 : 53', 'Bomfunk MCs - Freestyler (Video Original Version)',
        'https://www.youtube.com/watch?v=ymNFyxvIdaM','https://www.youtube.com/embed/ymNFyxvIdaM');

--История воспроизведения
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 15:57:06.142331', 2, 1);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 15:57:17.367720', 2, 2);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 16:00:57.203507', 3, 3);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 16:02:09.415054', 3, 2);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 21:57:47.537634', 3, 4);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 21:58:36.177121', 4, 4);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-05 10:41:40.984959', 5, 5);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-05 10:42:12.851908', 5, 6);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-05 10:43:37.947920', 6, 7);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-05 10:44:28.145821', 7, 4);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-06 00:02:21.227642', 8, 4);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-06 00:03:10.080842', 9, 4);
INSERT INTO server_video_history (id, playback_date, server_id, video_id)
VALUES (nextval('server_seq'), '2023-04-04 15:57:06.142331', 10, 4);

--Юзер-Сервер
insert into users_servers (user_id, server_id) VALUES (1, 1);
insert into users_servers (user_id, server_id) VALUES (1, 2);
insert into users_servers (user_id, server_id) VALUES (2, 1);
insert into users_servers (user_id, server_id) VALUES (2, 3);
insert into users_servers (user_id, server_id) VALUES (3, 3);
insert into users_servers (user_id, server_id) VALUES (4, 4);
insert into users_servers (user_id, server_id) VALUES (5, 5);
insert into users_servers (user_id, server_id) VALUES (6, 6);
insert into users_servers (user_id, server_id) VALUES (7, 7);
insert into users_servers (user_id, server_id) VALUES (8, 8);
insert into users_servers (user_id, server_id) VALUES (9, 9);
insert into users_servers (user_id, server_id) VALUES (10, 10);
insert into users_servers (user_id, server_id) VALUES (3, 6);
insert into users_servers (user_id, server_id) VALUES (4, 8);
insert into users_servers (user_id, server_id) VALUES (5, 3);
insert into users_servers (user_id, server_id) VALUES (6, 9);


INSERT INTO article(id, title, content) VALUES (1, 'abc', 'def');
INSERT INTO article(id, title, content) VALUES (2, 'bca', 'def');
INSERT INTO article(id, title, content) VALUES (3, 'cba', 'def');

--dummy data(sql ref)
INSERT INTO article(id, title, content) VALUES (4, 'Soul food?', 'go comment');
INSERT INTO article(id, title, content) VALUES (5, 'Hobby?', 'go go comment');
INSERT INTO article(id, title, content) VALUES (6, 'Job?', 'go go go comment');

--comment dummy data
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'Kim', 'Chicken');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'Choi', 'Curry');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'Lee', 'Pasta');
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'Park', 'Baseball');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'Yoon', 'Reading');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'Pyo', 'Game');
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'Park', 'Doctor');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'Park', 'Dentist');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'Park', 'Nurse');




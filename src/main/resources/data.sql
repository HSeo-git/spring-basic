INSERT INTO article(id, title, content) VALUES (1, 'My favorite Color', 'Purple');
INSERT INTO article(id, title, content) VALUES (2, 'My favorite Food', 'Beef');
INSERT INTO article(id, title, content) VALUES (3, 'My favorite Beverage', 'Coldbrew Coffee');

--dummy data(sql ref)
INSERT INTO article(id, title, content) VALUES (4, 'My favorite spot', 'Amusement Park');
INSERT INTO article(id, title, content) VALUES (5, 'My favorite Flower', 'Cherry Blossom');
INSERT INTO article(id, title, content) VALUES (6, 'My favorite Movie Genre', 'Adventure');

--comment dummy data
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'Y.Kim', 'Mine too!');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'K.Choi', 'Mine is a park!');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'J.Lee', 'Me too!');
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'S.Park', 'Movie-theater');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'M.Yoon', 'Mine is Rose :)');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'J.Pyo', 'I Love it, too!');
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'D.Park', 'I like watching documentaries!');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'Park', 'In my case, action movies!');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'Park', 'Me too!');




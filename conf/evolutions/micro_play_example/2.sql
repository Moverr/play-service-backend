# --- !Ups


INSERT INTO Friend (id, name, surname) VALUES (1, 'Peter', 'First')
INSERT INTO Friend (id, name, surname) VALUES (2, 'Peter', 'Second')
INSERT INTO Friend (id, name, surname) VALUES (3, 'Peter', 'Third')
INSERT INTO Friend (id, name, surname) VALUES (4, 'Tom', 'First')
INSERT INTO Friend (id, name, surname) VALUES (5, 'Tom', 'Second')
INSERT INTO Friend (id, name, surname) VALUES (6, 'Tom', 'Third')
INSERT INTO Friend (id, name, surname) VALUES (7, 'David', 'First')
INSERT INTO Friend (id, name, surname) VALUES (8, 'David', 'Second')
INSERT INTO Friend (id, name, surname) VALUES (9, 'David', 'Third')

# --- !Downs

DELETE FROM Friend
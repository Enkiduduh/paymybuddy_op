CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS transaction (
    id INT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    description VARCHAR(100),
    amount DOUBLE,
    FOREIGN KEY (sender_id) REFERENCES user(id),
    FOREIGN KEY (receiver_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS user_connection (
    uc_id INT PRIMARY KEY,
    user_id INT NOT NULL,
    connection_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (connection_id) REFERENCES user(id)
);

INSERT INTO users (id, username, email, password) VALUES (1, 'John2', 'john2@test.com', 'pass123');
INSERT INTO users (id, username, email, password) VALUES (2, 'Mike.l', 'mike.l@test.com', 'pass456');
INSERT INTO users (id, username, email, password) VALUES (3, 'Sam', 'sam@test.com', 'pass789');
INSERT INTO users (id, username, email, password) VALUES (4, 'Alex_24', 'alex_24@test.com', 'passabc');
INSERT INTO users (id, username, email, password) VALUES (5, 'admin', 'admin@example.com', 'pass123');
INSERT INTO users (id, username, email, password) VALUES (6, 'Mike.l', 'mike.l@test.com', 'pass456');
INSERT INTO users (id, username, email, password) VALUES (7, 'pomme', 'pomme@pomme.fr', 'pomme');
INSERT INTO users (id, username, email, password) VALUES (8, 'joe', 'joe@test.fr', 'joe');
INSERT INTO users (id, username, email, password) VALUES (9, 'poire', 'poire@poire.fr', 'poire');
INSERT INTO users (id, username, email, password) VALUES (10, 'banane', 'banane@banane.fr', 'banane');

INSERT INTO transaction (id, senderID, receiver_id, description, amount) VALUES (1, 8, 10, 'Service', 50.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (2, 9, 10, 'Service', 20.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (3, 10, 8, 'Work', 40.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (4, 7, 8, 'Refund', 20.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (5, 1, 7, 'Service', 55.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (6, 2, 7, 'Service', 10.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (7, 3, 7, 'Food', 12.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (8, 4, 7, 'Gift', 30.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (9, 7, 9, 'Gift', 30.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (10, 7, 10, 'Christmas', 30.0);
INSERT INTO transaction (id, sender_id, receiver_id, description, amount) VALUES (11, 7, 10, 'BubbleTea', 13.0);

INSERT INTO user_connection (user_id, connection_id) VALUES (10, 9);
INSERT INTO user_connection (user_id, connection_id) VALUES (10, 8);
INSERT INTO user_connection (user_id, connection_id) VALUES (10, 7);
INSERT INTO user_connection (user_id, connection_id) VALUES (8, 10);
INSERT INTO user_connection (user_id, connection_id) VALUES (8, 9);
INSERT INTO user_connection (user_id, connection_id) VALUES (8, 7);
INSERT INTO user_connection (user_id, connection_id) VALUES (7, 8);
INSERT INTO user_connection (user_id, connection_id) VALUES (7, 9);
INSERT INTO user_connection (user_id, connection_id) VALUES (7, 10);

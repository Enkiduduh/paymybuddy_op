INSERT INTO User (id, username, email, password) VALUES (1, 'John2', 'john2@test.com', 'pass123');
INSERT INTO User (id, username, email, password) VALUES (2, 'Mike.l', 'mike.l@test.com', 'pass456');
INSERT INTO User (id, username, email, password) VALUES (3, 'Sam', 'sam@test.com', 'pass789');
INSERT INTO User (id, username, email, password) VALUES (4, 'Alex_24', 'alex_24@test.com', 'passabc');

INSERT INTO Transaction (id, senderID, receiverID, description, amount) VALUES (1, 1, 2, 'Service', 50.0);
INSERT INTO Transaction (id, senderID, receiverID, description, amount) VALUES (2, 1, 3, 'Service', 60.0);
INSERT INTO Transaction (id, senderID, receiverID, description, amount) VALUES (3, 2, 3, 'Work', 40.0);
INSERT INTO Transaction (id, senderID, receiverID, description, amount) VALUES (4, 3, 1, 'Refund', 20.0);
INSERT INTO Transaction (id, senderID, receiverID, description, amount) VALUES (5, 4, 2, 'Service', 50.0);

INSERT INTO UserConnection (user_id, connection_id) VALUES (1, 2);
INSERT INTO UserConnection (user_id, connection_id) VALUES (1, 3);
INSERT INTO UserConnection (user_id, connection_id) VALUES (2, 3);
INSERT INTO UserConnection (user_id, connection_id) VALUES (3, 1);
INSERT INTO UserConnection (user_id, connection_id) VALUES (4, 2);
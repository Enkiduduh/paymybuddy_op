CREATE TABLE User (
    id INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE Transaction (
    id INT PRIMARY KEY,
    senderID INT NOT NULL,
    receiverID INT NOT NULL,
    description VARCHAR(100),
    amount DOUBLE,
    FOREIGN KEY (senderID) REFERENCES User(id),
    FOREIGN KEY (receiverID) REFERENCES User(id)
);

CREATE TABLE UserConnection (
    user_id INT NOT NULL,
    connection_id INT NOT NULL,
    PRIMARY KEY (user_id, connection_id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (connection_id) REFERENCES User(id)
);
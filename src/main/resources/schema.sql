CREATE TABLE IF NOT EXISTS User (
    id INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Transaction (
    id INT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    description VARCHAR(100),
    amount DOUBLE,
    FOREIGN KEY (sender_id) REFERENCES User(id),
    FOREIGN KEY (receiver_id) REFERENCES User(id)
);

CREATE TABLE IF NOT EXISTS UserConnection (
    user_id INT NOT NULL,
    connection_id INT NOT NULL,
    PRIMARY KEY (user_id, connection_id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (connection_id) REFERENCES User(id)
);
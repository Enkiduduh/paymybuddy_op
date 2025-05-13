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
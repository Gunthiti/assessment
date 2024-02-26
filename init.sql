DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE user_ticket (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE lottery (
    ticker_id SERIAL PRIMARY KEY,
    ticket VARCHAR(6) NOT NULL,
    price INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    owner_id INTEGER
);

INSERT INTO user_ticket(user_id ,name) VALUES('1','Son Goku');

INSERT INTO lottery (ticket, price, amount, owner_id) VALUES('123456', 80, 1, '1');
INSERT INTO lottery (ticket, price, amount) VALUES('122222', 80, 1);
INSERT INTO lottery (ticket, price, amount) VALUES('123333', 80, 1);
INSERT INTO lottery (ticket, price, amount) VALUES('123444', 80, 1);
INSERT INTO lottery (ticket, price, amount) VALUES('123455', 80, 1);


DROP TABLE IF EXISTS Address_Book;

CREATE TABLE Address_Book (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO Address_Book (id, name) VALUES (1, 'first address book');

INSERT INTO Address_Book (id, name) VALUES (2, 'second address book');

INSERT INTO Address_Book (id, name) VALUES (3, 'third address book');
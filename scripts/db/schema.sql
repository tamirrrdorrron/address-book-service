CREATE TABLE address_book (
  address_book_id INT NOT NULL,
  address_book_name VARCHAR(200) NOT NULL,
  PRIMARY KEY(address_book_id)
);

CREATE TABLE contact (
  contact_id INT NOT NULL,
  contact_full_name VARCHAR(100) NOT NULL,
  contact_mobile_phone VARCHAR(30) NOT NULL,
  contact_address_book_id INT,
  PRIMARY KEY (contact_id),
  CONSTRAINT contact_address_book_id FOREIGN KEY (contact_address_book_id) REFERENCES address_book(address_book_id)
);
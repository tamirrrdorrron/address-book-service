CREATE TABLE address_book (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  branch_number VARCHAR(10) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE contact (
  id INT NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(100) NOT NULL,
  mobile_phone VARCHAR(30) NOT NULL,
  address_book_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (address_book_id) REFERENCES address_book(id)
);
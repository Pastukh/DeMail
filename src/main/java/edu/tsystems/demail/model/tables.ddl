CREATE TABLE users
(
  id INT NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NULL,
  mid_name VARCHAR(30) NULL,
  phone VARCHAR(30) NULL,
  pass VARCHAR(30) NULL
);
CREATE UNIQUE INDEX unique_id ON users ( id );

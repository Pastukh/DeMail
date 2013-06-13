CREATE TABLE test.users
(
  id INT NOT NULL,
  login VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NULL,
  phone VARCHAR(30) NULL,
  pass VARCHAR(30) NULL,
  mailBox INT NOT NULL

);
CREATE UNIQUE INDEX unique_id ON users ( id );

CREATE TABLE test.mail_boxes
(
  id INT NOT NULL,
  user_id INT NOT NULL,
  mail_box VARCHAR(30) NOT NULL,
  dateCreate DATE NULL
);

CREATE TABLE test.folders
(
  id INT NOT NULL,
  name VARCHAR(30) NOT NULL,
  mail_box__id INT NOT NULL
);
CREATE TABLE test.email
(
  id INT NOT NULL,
  from VARCHAR(30) NOT NULL,
  to VARCHAR(30) NOT NULL,
  subject VARCHAR(30) NOT NULL,
  body VARCHAR(30) NOT NULL,
  date DATE NOT NULL,
  read BOOLEAN NOT NULL,
  mail_box_ownner_id INT NOT NULL,
  folder INT NOT NULL
);
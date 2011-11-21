CREATE TABLE Items (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name CHAR(15)  NOT NULL    ,
PRIMARY KEY(id));



CREATE TABLE Teams (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name CHAR(15)  NOT NULL  ,
  project CHAR(15)  NULL  ,
  info VARCHAR  NULL  ,
  active BOOL  NOT NULL DEFAULT 1 ,
  goal VARCHAR  NULL    ,
PRIMARY KEY(id));



CREATE TABLE Groups (
  idGroups INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name CHAR(15)  NULL    ,
PRIMARY KEY(idGroups));



CREATE TABLE Users (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Groups_idGroups INTEGER UNSIGNED  NOT NULL  ,
  pass VARCHAR  NOT NULL  ,
  name CHAR(15)  NULL  ,
  lastname CHAR(15)  NULL  ,
  address CHAR(15)  NULL  ,
  city CHAR(15)  NULL  ,
  email CHAR(15)  NULL  ,
  phone CHAR(15)  NULL  ,
  role INTEGER UNSIGNED  NOT NULL  ,
  professia CHAR(15)  NULL    ,
PRIMARY KEY(id)  ,
INDEX Users_FKIndex1(Groups_idGroups),
  FOREIGN KEY(Groups_idGroups)
    REFERENCES Groups(idGroups)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Users_has_Items (
  Users_id INTEGER UNSIGNED  NOT NULL  ,
  Items_id INTEGER UNSIGNED  NOT NULL  ,
  state BOOL  NOT NULL    ,
PRIMARY KEY(Users_id, Items_id)  ,
INDEX Users_has_Items_FKIndex1(Users_id)  ,
INDEX Users_has_Items_FKIndex2(Items_id),
  FOREIGN KEY(Users_id)
    REFERENCES Users(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Items_id)
    REFERENCES Items(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Groups_has_Items (
  Groups_idGroups INTEGER UNSIGNED  NOT NULL  ,
  Items_id INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(Groups_idGroups, Items_id)  ,
INDEX Groups_has_Items_FKIndex1(Groups_idGroups)  ,
INDEX Groups_has_Items_FKIndex2(Items_id),
  FOREIGN KEY(Groups_idGroups)
    REFERENCES Groups(idGroups)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Items_id)
    REFERENCES Items(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Teams_has_Users (
  Teams_id INTEGER UNSIGNED  NOT NULL  ,
  Users_id INTEGER UNSIGNED  NOT NULL  ,
  confirmed BOOL  NULL DEFAULT 0   ,
PRIMARY KEY(Teams_id, Users_id)  ,
INDEX Teams_has_Users_FKIndex1(Teams_id)  ,
INDEX Teams_has_Users_FKIndex2(Users_id),
  FOREIGN KEY(Teams_id)
    REFERENCES Teams(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Users_id)
    REFERENCES Users(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);





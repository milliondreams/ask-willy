# Orange SCHEMA

# --- !Ups
CREATE TABLE  employment_record  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   name  varchar(100) NOT NULL,
   start  date NOT NULL,
   end  date NOT NULL,
   designation  varchar(45) DEFAULT NULL,
   person_id  int(11) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  emp_person_fk  ( person_id ),
  CONSTRAINT  emp_person_fk  FOREIGN KEY ( person_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  managers  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   person_id  int(11) NOT NULL,
   project_id  int(11) NOT NULL,
   current  tinyint(1) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  managers_person_fk  ( person_id ),
  KEY  managers_project_fk  ( project_id ),
  CONSTRAINT  managers_person_fk  FOREIGN KEY ( person_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT  managers_project_fk  FOREIGN KEY ( project_id ) REFERENCES  projects  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  people  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   name  varchar(150) NOT NULL,
   email  varchar(150) NOT NULL,
   designation  varchar(45) DEFAULT NULL,
   gender  enum('MALE','FEMALE') DEFAULT NULL,
   experience  decimal(3,1) DEFAULT NULL,
   available  tinyint(1) NOT NULL,
   location  varchar(45) DEFAULT NULL,
   employee  tinyint(1) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  UNIQUE KEY  email_UNIQUE  ( email )
);

CREATE TABLE  people_managers  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   person_id  int(11) NOT NULL,
   manager_id  int(11) NOT NULL,
   current  tinyint(1) DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  people_managers_fk1  ( person_id ),
  KEY  people_managers_fk2  ( manager_id ),
  CONSTRAINT  people_managers_fk1  FOREIGN KEY ( person_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT  people_managers_fk2  FOREIGN KEY ( manager_id ) REFERENCES  managers  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  people_project  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   person_id  int(11) NOT NULL,
   project_id  int(11) NOT NULL,
   current  tinyint(1) DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  people_project_fk1  ( person_id ),
  KEY  people_project_fk2  ( project_id ),
  CONSTRAINT  people_project_fk1  FOREIGN KEY ( person_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT  people_project_fk2  FOREIGN KEY ( project_id ) REFERENCES  projects  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  people_skills  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   person_id  int(11) NOT NULL,
   skill_id  int(11) NOT NULL,
   level  enum('NOVICE','INTERMEDIATE','EXPERT') NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  person_skill_fk1  ( person_id ),
  KEY  person_skill_fk2  ( skill_id ),
  CONSTRAINT  person_skill_fk1  FOREIGN KEY ( person_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT  person_skill_fk2  FOREIGN KEY ( skill_id ) REFERENCES  skills  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  projects  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   name  varchar(100) NOT NULL,
   description  text NOT NULL,
   status  enum('ACTIVE','PIPELINE','CLOSED') NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  UNIQUE KEY  name_UNIQUE  ( name )
);

CREATE TABLE  qualifications_records  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   person_id  int(11) NOT NULL,
   qualification  varchar(45) NOT NULL,
   institute  varchar(45) NOT NULL,
   started  year(4) NOT NULL,
   completed  year(4) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  qualifications_records_fk  ( person_id ),
  CONSTRAINT  qualifications_records_fk  FOREIGN KEY ( person_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  requirements  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   project_id  int(11) NOT NULL,
   contact_id  int(11) DEFAULT NULL,
   fill_by  date DEFAULT NULL,
   priority  enum('LOW','NORMAL','CRUCIAL') DEFAULT NULL,
   category  enum('BILLED','SHADOW','PITCH','TENTATIVE') DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  requirements_project_fk  ( project_id ),
  KEY  requirements_person_fk  ( contact_id ),
  CONSTRAINT  requirements_person_fk  FOREIGN KEY ( contact_id ) REFERENCES  people  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT  requirements_project_fk  FOREIGN KEY ( project_id ) REFERENCES  projects  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  requirements_skills  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   requirement_id  int(11) NOT NULL,
   skill_id  int(11) NOT NULL,
   skill_level  varchar(45) DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  requirements_skills_fk1  ( requirement_id ),
  KEY  requirements_skills_fk2  ( skill_id ),
  CONSTRAINT  requirements_skills_fk1  FOREIGN KEY ( requirement_id ) REFERENCES  requirements  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT  requirements_skills_fk2  FOREIGN KEY ( skill_id ) REFERENCES  skills  ( id ) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE  skills  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   skill_name  varchar(45) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  skill_name_UNIQUE  ( skill_name ),
  UNIQUE KEY  id_UNIQUE  ( id )
);

# ---!Downs
DROP TABLE employment_record ;
DROP TABLE managers ;
DROP TABLE people ;
DROP TABLE people_managers ;
DROP TABLE people_project ;
DROP TABLE people_skills ;
DROP TABLE projects ;
DROP TABLE qualifications_records ;
DROP TABLE requirements ;
DROP TABLE requirements_skills ;
DROP TABLE skills ;

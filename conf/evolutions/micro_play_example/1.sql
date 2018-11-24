# --- !Ups

CREATE TABLE Friend (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255),
    surname varchar(255),
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE Friend;
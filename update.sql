/* CREATE DATABASE store; */
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP SEQUENCE IF EXISTS item_id_seq CASCADE ;
DROP SEQUENCE IF EXISTS user_id_seq CASCADE ;

CREATE TABLE items (
  item_id bigint NOT NULL,
  item_name text NOT NULL,
  item_price bigint NOT NULL,
  item_count bigint NOT NULL,
  PRIMARY KEY (item_id)
);

CREATE TABLE users (
  user_id bigint NOT NULL,
  user_name text NOT NULL,
  user_last_name text NOT NULL,
  user_age int NOT NULL,
  user_pos text NOT NULL,
  user_email text NOT NULL,
  item_id bigint NOT NULL,
  status boolean NOT NULL,
  PRIMARY KEY (user_id)
);

ALTER TABLE items
  ADD CONSTRAINT fk_items_id
FOREIGN KEY (item_id) REFERENCES items (item_id) ;

CREATE SEQUENCE item_id_seq;

ALTER TABLE items
    ALTER COLUMN item_id
        SET DEFAULT NEXTVAL('item_id_seq');

CREATE SEQUENCE user_id_seq;

ALTER TABLE users
    ALTER COLUMN user_id
        SET DEFAULT NEXTVAL('user_id_seq');

CREATE INDEX user_index_user_name_user_lastname ON users (user_name, user_last_name);

CREATE INDEX items_index_item_name ON items (item_name);

INSERT INTO "public"."items" ("item_name", "item_price", "item_count") VALUES ('телевизоры', 500000, 5);
INSERT INTO "public"."items" ("item_name", "item_price", "item_count") VALUES ('телефоны', 200000, 5);
INSERT INTO "public"."items" ("item_name", "item_price", "item_count") VALUES ('ноутбуки', 1500000, 5);
INSERT INTO "public"."items" ("item_name", "item_price", "item_count") VALUES ('колонки', 300000, 5);
INSERT INTO "public"."items" ("item_name", "item_price", "item_count") VALUES ('наушники', 10000, 10);















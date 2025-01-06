CREATE TABLE owner
(
                     id                    uuid primary key,
                     name                 varchar not null,
                     role                  varchar not null
                  );


CREATE TABLE pet
(
                   id                    uuid primary key,
                   name                 varchar not null,
                   owner_id                  uuid not null
                );
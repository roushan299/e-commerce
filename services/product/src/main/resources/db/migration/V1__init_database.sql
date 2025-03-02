create table if not exits category
(
  id integer not null primary key,
  description varchar(255),
  name varchar(255)
);

create table if not exits product
(
  id integer not null primary key,
  description varchar(255),
  name varchar(255),
  available_quantity double precision not null,
  price numeric (38, 2),
  category_id integer
      constraint fk1mlasdal references category
);

create sequence if not exits category_seq increment by 50;
create sequence if not exits product_seq increment by 50;
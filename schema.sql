create table if not exists users
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null unique,
    password   text not null
);

create table if not exists partners
(
    id        bigserial primary key,
    name      text             not null,
    email     text             not null unique,
    password  text             not null,
    longitude double precision not null,
    latitude  double precision not null,
    image_url text default 'default',
    address   text             not null,
    rating    int
);

create table if not exists categories
(
    id   serial primary key,
    type text not null
);

create table if not exists activity
(
    id   serial primary key,
    type text not null
);

create table if not exists reviews
(
    id         bigserial not null,
    user_id    bigint    not null,
    partner_id bigint    not null,
    rating     int check (rating > 0 and rating < 6),
    comment    text,
    foreign key (user_id) references users (id),
    foreign key (partner_id) references partners (id),
    constraint reviews_user_id_partner_id_un unique (user_id, partner_id),
    constraint reviews_rating_or_comment_nn check (rating is not null or comment is not null)
);


create table if not exists partners_categories
(
    partner_id bigint not null,
    type_id    int    not null,
    foreign key (partner_id) references partners (id),
    foreign key (type_id) references categories (id)
);

create table if not exists partners_activities
(
    partner_id bigint not null,
    type_id    int    not null,
    foreign key (partner_id) references partners (id),
    foreign key (type_id) references activity (id)
);


select distinct id, name, email, password, longitude, latitude, image_url, address, rating
from (select distinct id, name, email, password, longitude, latitude, image_url, address, rating
      from partners p inner join (select * from partners_categories pc where pc.type_id in (1, 2)) pc on p.id = pc.partner_id) pm
         inner join (select * from partners_activities pa where pa.type_id in (1, 3)) pa on pa.partner_id = pm.id;

insert into partners(name, email, password, longitude, latitude, address, rating)
VALUES ('R','R','R',1.0, 1.0, 'R', 4);

insert into partners(name, email, password, longitude, latitude, address, rating)
VALUES ('R1','R1','R1',1.0, 1.0, 'R1', 2);

insert into partners(name, email, password, longitude, latitude, address, rating)
VALUES ('R2','R2','R2',1.0, 1.0, 'R2', 2);

insert into categories(type) values ('AType');
insert into categories(type) values ('BType');
insert into categories(type) values ('CType');

insert into activity(type) values ('AActivity');
insert into activity(type) values ('BActivity');
insert into activity(type) values ('CActivity');

insert into partners_activities(partner_id, type_id) VALUES (1, 1);
insert into partners_activities(partner_id, type_id) VALUES (1, 2);
insert into partners_activities(partner_id, type_id) VALUES (1, 3);
insert into partners_activities(partner_id, type_id) VALUES (2, 3);
insert into partners_activities(partner_id, type_id) VALUES (3, 2);

insert into partners_categories(partner_id, type_id) VALUES (1, 1);
insert into partners_categories(partner_id, type_id) VALUES (1, 2);
insert into partners_categories(partner_id, type_id) VALUES (1, 3);
insert into partners_categories(partner_id, type_id) VALUES (2, 2);
insert into partners_categories(partner_id, type_id) VALUES (3, 3);

insert into users(first_name, last_name, email, password)
values ('U', 'U', 'U','U');

insert into reviews(user_id, partner_id, rating, comment)
values (1, 1, 3, 'aaaaaaaa');

insert into reviews(user_id, partner_id, rating, comment)
values (1, 2, 3, 'bbbbb');

select distinct id, name, email, password, longitude, latitude, image_url, address, rating
from partners inner join partners_categories pc on partners.id = pc.partner_id;


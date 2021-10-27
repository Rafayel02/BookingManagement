create table if not exists users
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null unique,
    password   text not null,
    uuid       text not null unique
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
    uuid      text             not null unique,
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



select * from categories;

select * from partners_categories;

select * from partners;

select *
from activity;

insert into activity (type) values ('a');

insert into partners_activities (partner_id, type_id) values (1,1);

select * from partners_activities;

select * from partners inner join partners_categories pc on partners.id = pc.partner_id inner join partners_activities pa on partners.id = pa.partner_id;


select * from  partners inner join (select * from partners_categories pc where pc.type_id in :listCat) pc on partners.id = pc.partner_id
            inner join (select * from partners_activities pa where pa.type_id in :listAct) pa on partners.id = pa.partner_id;


insert into partners(name, email, password, longitude, latitude, address, uuid, rating)
VALUES('name2', 'email2', 'password', 12.3, 12.3, 'address','uuueueueuid', 3);

select id, name, email, password, longitude, latitude, image_url, address, uuid, rating
from (select id, name, email, password, longitude, latitude, image_url, address, uuid, rating
from partners p inner join (select * from partners_categories pc where pc.type_id in (select id from categories where categories.type in ('a', 'b'))) pc on p.id = pc.partner_id) pm
inner join (select * from partners_activities pa where pa.type_id in (1)) pa on pa.partner_id = pm.id;

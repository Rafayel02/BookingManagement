create table if not exists users
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null unique,
    password   text not null
);

create table if not exists restaurants
(
    id        bigserial primary key,
    name      text             not null,
    email     text             not null unique,
    password  text             not null,
    longitude double precision not null,
    latitude  double precision not null,
    image_url text default 'default',
    address   text             not null
);

create table if not exists categories
(
    id   serial primary key,
    type text not null
);

create table if not exists reviews
(
    id            bigserial not null,
    user_id       bigint    not null,
    restaurant_id bigint    not null,
    rating        int check (rating > 0 and rating < 6),
    comment       text,
    foreign key (user_id) references users (id),
    foreign key (restaurant_id) references restaurants (id),
    constraint reviews_user_id_restaurant_id_un unique (user_id, restaurant_id),
    constraint reviews_rating_or_comment_nn check (rating is not null or comment is not null)
);


create table if not exists restaurants_categories
(
    restaurant_id bigint not null,
    type_id       int not null,
    foreign key (restaurant_id) references restaurants (id),
    foreign key (type_id) references categories (id)
);


insert into users(first_name, last_name, email, password)
values ('ficsxrst', 'lacsst', 'csa', 'pascszs');
insert into users(first_name, last_name, email, password)
values ('first1', 'last1', 'email1', 'pass1');
insert into restaurants(name, email, password, longitude, latitude, address)
values ('name', 'email', 'pass', 45.02, 44.03, 'add');
insert into restaurants(name, email, password, longitude, latitude, address)
values ('name1', 'email1', 'pass1', 43.02, 18.03, 'add');
insert into categories(type)
values ('china');
insert into categories(type)
values ('mex');
insert into reviews(user_id, restaurant_id, rating, comment)
values (1, 2, 5, 'ok');
insert into reviews(user_id, restaurant_id, rating, comment)
values (2, 1, 4, 'good');
insert into restaurants_categories (restaurant_id, type_id)
values (1, 1);
insert into restaurants_categories (restaurant_id, type_id)
values (2, 2);


select *
from users;



create table if not exists users
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    username   text not null,
    password   text not null
);

create table if not exists restaurants
(
    id       bigserial primary key,
    name     text  not null,
    location jsonb not null,
    address  text  not null
);

create table if not exists categories
(
    id   serial primary key,
    name text not null
);

create table if not exists reviews
(
    user_id       bigint not null,
    restaurant_id bigint not null,
    rating        int check (rating > 0 and rating < 6),
    comment       text,
    foreign key (user_id) references users (id),
    foreign key (restaurant_id) references restaurants (id)
    );

create table if not exists restaurants_categories
(
    restaurant_id bigint not null,
    type_id       bigint not null,
    foreign key (restaurant_id) references restaurants (id),
    foreign key (type_id) references categories (id)
);
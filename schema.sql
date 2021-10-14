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
    longitude double precision not null,
    latitude  double precision not null,
    image     text             default '0',
    address   text             not null
);

create table if not exists categories
(
    id   serial primary key,
    name text not null
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
    constraint user_restaurant_review unique (user_id, restaurant_id)
);


create table if not exists restaurants_categories
(
    restaurant_id bigint not null,
    type_id       bigint not null,
    foreign key (restaurant_id) references restaurants (id),
    foreign key (type_id) references categories (id)
);


insert into users(first_name, last_name, email, password)
values ('first', 'last', 'email', 'pass');
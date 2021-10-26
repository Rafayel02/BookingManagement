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

insert into partners(name, email, password, longitude, latitude, address, rating)
values ('A', 'A','A',12.0, 12.0, 'A', 5);

insert into users(first_name, last_name, email, password)
values ('U','U','U','U');

insert into users(first_name, last_name, email, password)
values ('U2','U2','U2','U2');

select AVG(rating) from reviews where partner_id = id;
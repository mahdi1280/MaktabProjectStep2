create table comment
(
    version  integer,
    score    integer,
    details  varchar,
    offer_id integer
);

create table offer
(
    id  identity primary key,
    version       integer,
    period_of_time  date,
    proposed_price integer,
    created_at     date,
    start_time     date,
    order_id integer,
    user_id integer

);


create table orders
(
    id                  identity  primary key ,
    version             integer,
    proposed_Price       integer,
    created_At           date,
    address             varchar,
    word_Time            date,
    under_service_id    integer,
    status varchar ,
    user_id integer,
    offer_id integer
);

create table service
(
    id       identity primary key,
    version integer,
    title   varchar
);



create table Under_Service
(
    id          identity primary key,
    version    integer,
    base_Price  integer,
    details    varchar,
    service_id integer
);

create table application_user
(
    id         identity primary key,
    version   integer,
        firstname varchar,
    lastname  varchar,
    email     varchar,
    password  varchar,
    deleted   boolean,
    image     bytea,
    created_At date,
    credit    integer,
    status    varchar,
    role      varchar,
    score     integer

);

create table user_under_service(
    user_id integer ,
    under_server_id integer
);


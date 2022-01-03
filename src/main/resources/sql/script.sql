create table hotelDB2.hibernate_sequence
(
    next_val bigint null
);

create table hotelDB2.room_class
(
    id         int auto_increment
        primary key,
    room_class varchar(20) not null,
    constraint class_name_UNIQUE
        unique (room_class)
)
    comment 'consist different class of rooms' charset = utf8;

create table hotelDB2.room
(
    id              int auto_increment
        primary key,
    room_class_id   int    not null,
    room_cost       double not null,
    persons_in_room int    not null,
    constraint room_class_id_fk
        foreign key (room_class_id) references hotelDB2.room_class (id)
)
    comment 'consist rooms infromation' charset = utf8;

create index room_class_id_idx
    on hotelDB2.room (room_class_id);

create table hotelDB2.user
(
    id         bigint      not null
        primary key,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    user_type  varchar(20) not null,
    email      varchar(20) null,
    password   varchar(90) null,
    account    double      not null
)
    charset = utf8;

create table hotelDB2.client_order
(
    client_order_room_id int auto_increment,
    client_id            bigint not null,
    constraint id_UNIQUE
        unique (client_order_room_id),
    constraint client_id_fk
        foreign key (client_id) references hotelDB2.user (id)
)
    charset = utf8;

create index client_id_fk_idx
    on hotelDB2.client_order (client_id);

alter table hotelDB2.client_order
    add primary key (client_order_room_id);

create table hotelDB2.client_request
(
    client_request_id int auto_increment
        primary key,
    client_r_id       bigint not null,
    constraint client_r_id_fk
        foreign key (client_r_id) references hotelDB2.user (id)
)
    charset = utf8;

create index client_r_id_fk_idx
    on hotelDB2.client_request (client_r_id);

create table hotelDB2.`order`
(
    client_order_id int         not null,
    request_id      int         not null,
    room_id         int         not null,
    check_in_date   date        not null,
    check_out_date  date        not null,
    order_status    varchar(50) not null,
    constraint client_order_room_id_fk
        foreign key (client_order_id) references hotelDB2.client_order (client_order_room_id)
            on update cascade on delete cascade,
    constraint room_id_fk
        foreign key (room_id) references hotelDB2.room (id)
)
    charset = utf8;

create index client_order_fk_id_idx
    on hotelDB2.`order` (client_order_id);

create table hotelDB2.request
(
    request_id     int         not null,
    persons_amount int         not null,
    room_class     varchar(10) not null,
    check_in_date  date        not null,
    check_out_date date        not null,
    request_status char(50)    not null,
    constraint request_id_fk
        foreign key (request_id) references hotelDB2.client_request (client_request_id)
            on update cascade on delete cascade
)
    charset = utf8;

create table hotelDB2.orders
(
    client_order_id int          null,
    request_id      int          not null,
    room_id         int          not null,
    check_out_date  date         null,
    check_in_date   date         null,
    order_status    varchar(255) null,
    constraint FKh59mvt18qbnv9htr8ye5s6wov
        foreign key (room_id) references hotelDB2.room (id),
    constraint FKn4ih6xn5k3nf3prpua8jiksxi
        foreign key (request_id) references hotelDB2.request (request_id)
);

create index request_id_fk_idx
    on hotelDB2.request (request_id);

INSERT INTO hotelDB2.room_class (room_class) VALUES ('DELUXE');
INSERT INTO hotelDB2.room_class (room_class) VALUES ('DOUBLE');
INSERT INTO hotelDB2.room_class (room_class) VALUES ('SINGLE');
INSERT INTO hotelDB2.room_class (room_class) VALUES ('SUITE');

INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (1, 46, 1);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (1, 46, 1);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (1, 46, 1);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (1, 46, 1);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (1, 46, 1);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (2, 88, 2);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (2, 88, 2);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (2, 88, 2);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (2, 88, 2);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (3, 124, 3);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (3, 124, 3);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (3, 124, 3);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (4, 250, 4);
INSERT INTO hotelDB2.room (room_class_id, room_cost, persons_in_room) VALUES (4, 250, 4);

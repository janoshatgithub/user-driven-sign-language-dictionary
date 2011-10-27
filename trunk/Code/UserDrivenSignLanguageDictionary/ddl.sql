
    drop table ApplicationUser;

    create table ApplicationUser (
        id bigint not null generated always as identity,
        email varchar(50) not null,
        fullname varchar(50) not null,
        login varchar(20) not null unique,
        password varchar(20) not null,
        userRole varchar(10) not null,
        version integer not null,
        primary key (id)
    );

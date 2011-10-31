
    alter table WordGroup 
        drop constraint fk_wordgroup_applicationuser;

    drop table ApplicationUser;

    drop table WordGroup;

    create table ApplicationUser (
        id bigint not null generated always as identity,
        email varchar(50) not null,
        emailVerificationSent timestamp,
        emailVerified timestamp,
        fullname varchar(50) not null,
        login varchar(20) not null unique,
        password varchar(20) not null,
        userRole varchar(10) not null,
        version integer not null,
        primary key (id)
    );

    create table WordGroup (
        id bigint not null generated always as identity,
        createdDateTime timestamp not null,
        description varchar(250),
        name varchar(30) not null unique,
        version integer not null,
        applicationUser_id bigint not null,
        primary key (id)
    );

    alter table WordGroup 
        add constraint fk_wordgroup_applicationuser 
        foreign key (applicationUser_id) 
        references ApplicationUser;

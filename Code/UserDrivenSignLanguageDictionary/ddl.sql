
    alter table VideoFile 
        drop constraint fk_file_applicationuser;

    alter table VideoFile 
        drop constraint fk_file_word;

    alter table Word 
        drop constraint fk_word_applicationuser;

    alter table WordGroup 
        drop constraint fk_wordgroup_applicationuser;

    alter table WordGroupWordRelation 
        drop constraint fk_wordgroupwordrelation_wordgroup;

    alter table WordGroupWordRelation 
        drop constraint fk_wordgroupwordrelation_word;

    drop table ApplicationUser;

    drop table VideoFile;

    drop table Word;

    drop table WordGroup;

    drop table WordGroupWordRelation;

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

    create table VideoFile (
        id bigint not null generated always as identity,
        fileName varchar(100) not null,
        resourceName varchar(50) not null,
        uploadedDateTime timestamp not null,
        version integer not null,
        toWord_id bigint not null,
        uploadedBy_id bigint not null,
        primary key (id)
    );

    create table Word (
        id bigint not null generated always as identity,
        createdDateTime timestamp not null,
        description varchar(250),
        version integer not null,
        word varchar(50) not null unique,
        requestCreatedBy_id bigint not null,
        primary key (id)
    );

    create table WordGroup (
        id bigint not null generated always as identity,
        createdDateTime timestamp not null,
        description varchar(250),
        name varchar(30) not null unique,
        version integer not null,
        createdBy_id bigint not null,
        primary key (id)
    );

    create table WordGroupWordRelation (
        id bigint not null generated always as identity,
        version integer not null,
        word_id bigint not null,
        wordGroup_id bigint not null,
        primary key (id),
        unique (wordGroup_id, word_id)
    );

    alter table VideoFile 
        add constraint fk_file_applicationuser 
        foreign key (uploadedBy_id) 
        references ApplicationUser;

    alter table VideoFile 
        add constraint fk_file_word 
        foreign key (toWord_id) 
        references Word;

    alter table Word 
        add constraint fk_word_applicationuser 
        foreign key (requestCreatedBy_id) 
        references ApplicationUser;

    alter table WordGroup 
        add constraint fk_wordgroup_applicationuser 
        foreign key (createdBy_id) 
        references ApplicationUser;

    alter table WordGroupWordRelation 
        add constraint fk_wordgroupwordrelation_wordgroup 
        foreign key (wordGroup_id) 
        references WordGroup;

    alter table WordGroupWordRelation 
        add constraint fk_wordgroupwordrelation_word 
        foreign key (word_id) 
        references Word;

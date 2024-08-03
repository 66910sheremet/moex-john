--liquibase formatted sql
--changeset temnikov_do:000-create-table-securities

create table securities (
    id          BIGSERIAL                   not null
        constraint securities_pk primary key,
    sec_id varchar(51) not null,
    shortname varchar(189) ,
    regnumber varchar(189) not null,
    name varchar(765) not null,
    isin varchar(51) not null,
    is_traded bigint not null,
    emitent_id bigint ,
    emitent_title varchar(765) ,
    emitent_inn varchar(30),
    emitent_okpo varchar(24) ,
    gosreg varchar(189) ,
    type varchar(93) ,
    groupp varchar(93) ,
    primary_boardid varchar(12) ,
    marketprice_boardid varchar(12) ,
    create_date timestamp without time zone not null,
    update_date timestamp without time zone not null
);

comment on table securities is 'актив';
comment on column securities.id is 'уникальный идентификатор';

create index securities_securities_pk
    on securities (id);
create index securities_securities_sec_id
    on securities (sec_id);
create index securities_securities_isin
    on securities (isin);
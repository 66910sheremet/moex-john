--liquibase formatted sql
--changeset temnikov_do:000-create-table-securities

create table securities_spec (
    id          BIGSERIAL                   not null
            constraint securities_spec_pk primary key,
    sec_id varchar(51) not null,
    name varchar(189) not null,
    title varchar(765) not null,
    valuee varchar(6144) not null,
    type varchar(21) not null,
    sort_order bigint not null,
    is_hidden bigint not null,
    precisionn bigint
);

comment on table securities_spec is 'спецификация актива';
comment on column securities_spec.sec_id is 'уникальный идентификатор';

create index securities_spec_securities_spec_id
    on securities_spec (sec_id);
create index securities_spec_name
    on securities_spec (name);
create index securities_spec_valuee
    on securities_spec (valuee);

CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE;

create table roles (id bigint not null, name varchar(255), primary key (id));
create table user_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
create table users (id bigint not null, password varchar(255), username varchar(255), primary key (id));
alter table user_roles add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles;
alter table user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;
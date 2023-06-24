Show databases;

use jdbc;
create table users(user_id int primary key auto_increment,name varchar(255),
email varchar(250) not null unique,Phoneno bigint,Date_Created timestamp default current_timestamp,
Date_updated timestamp default current_timestamp); 
desc users;
insert into users(name,email,phoneno) values ("Pooja Sharma","poojasharmaje@gmail.com",987654321);
select * from users;


use jdbc;
create table roles(role_id int primary key auto_increment,role_name varchar(255),
Date_Created timestamp default current_timestamp,
Date_updated timestamp default current_timestamp); 
desc roles;
select * from roles;
insert into roles(role_id,role_name) values(1234,"Admin");
insert into roles(role_id,role_name) values(2435,"User");

use jdbc;
create table user_roles(sno int primary key auto_increment,role_id int ,user_id int ,constraint myroleidfk foreign key (role_id) references roles(role_id),
constraint myuseridfk foreign key (user_id) references users(user_id) ); 
desc roles;
select * from roles;


use jdbc;
create table admin(admin_id int primary key auto_increment,admin_name varchar(255),admin_role varchar(255));
desc admin;

use jdbc;
create table admin_approves_user(sno int primary key auto_increment,admin_id int,user_id int,
constraint myadminidfk2 foreign key (admin_id) references admin(admin_id),
constraint myusersidfk2 foreign key (user_id) references users(user_id));
desc admin;

use jdbc;
show tables;

select * from users;

desc users;


use jdbc;
create table user(username varchar(250) not null unique,password varchar(250) not null,role varchar(250));
insert into user(username,password,role) values("poojasharma29","12345","User");
insert into user(username,password,role) values("amantiwari8861","1234","Admin");


show tables;

desc user;
select * from user;

create table attendance(attendance_id int primary key auto_increment,username varchar(255),ADate date,
constraint attendance_rule foreign key(username) references user(username)
);

create table user_attendance(id int primary key auto_increment,username varchar(255),attendance_id int,
constraint uname foreign key(username) references user(username),
constraint attendance foreign key(attendance_id) references attendance(attendance_id));



select * from user;
select * from attendance;
use jdbc;
create table leave_details(leave_id int primary key auto_increment,username varchar(255),casual_leave int default 10 ,sick_leave int default 15,
earned_leave int default 15,constraint username_rule foreign key(username) references user(username));
insert into leave_details(username) values("poojasharma29");

select * from leave_details;

create table leave_details_soft(leave_id int primary key auto_increment,username varchar(255),casual_leave int default 10 ,sick_leave int default 15,
earned_leave int default 15,constraint username_rule2 foreign key(username) references user(username));
select * from leave_details_soft;
insert into leave_details_soft(username) values("poojasharma29");

select * from leave_details_soft;

use jdbc;
create table leave_request(req_id int unique auto_increment,username varchar(255),ldate date,
is_approved bool default false,leave_type varchar(255),primary key(username,ldate),
constraint user_leave foreign key(username) references user(username));

insert into leave_request values(1,"poojasharma29",curdate(),false,"sick Leave");
insert into leave_request values(2,"poojasharma29",curdate(),false,"sick Leave");-- error
insert into leave_request values(3,"poojasharma29",curdate(),false,"sick Leave");
select * from leave_request;
select * from leave_details;

drop table leave_request;

update leave_request set is_approved=false where username="poojasharma29";

set @cas=(select casual_leave from leave_details where username='poojasharma29'  );
update leave_details set casual_leave=@cas+1 where username='poojasharma29';


set @cas=(select casual_leave from leave_details where username=?  ) ;update leave_details set casual_leave=@cas+1 where username=?;


create user sw201912022 identified by cometrue;
grant connect, resource, dba to sw201912022;
drop user sw201912022;
drop table blog201912022;

GRANT CREATE SESSION, CREATE TABLE, CREATE VIEW, resource TO sw201912022;

create sequence seq_b201912022 increment by 1 start with 1;

create table b201912022
(
    id  number(11) not null primary key,
    name    varchar2(30) not null,
    email   varchar2(30) not null,
    title   varchar2(50),
    content varchar2(100),
    regdate varchar2(200)
);

drop table b201912022;
insert into blog201912022 values(seq_blog201912022.nextval, 'sw', 'sw@induk.ac.kr', '블로그제목', '블로그내용');
insert into blog201912022 values(seq_blog201912022.nextval, 'sw2', 'sw@induk.ac.kr2', '블로그제목2', '블로그내용2');
insert into blog201912022 values(seq_blog201912022.nextval, 'sw999', 'sw@induk.ac.kr2', '블로그제목2999', '블로그내용299933');
select * from b201912022;
drop table b201912022;
create sequence seq_member increment by 1 start with 1;

create sequence seq_m increment by 1 start with 1;
create table member (
                        id number(11) not null primary key,
                        email varchar2(30) not null unique,
                        pw varchar2(20) not null,
                        name varchar2(30) not null,
                        phone varchar2(30),
                        address varchar2(50)
);

create table m201912022 (
                        id number(11) not null primary key,
                        email varchar2(30) not null unique,
                        pw varchar2(20) not null,
                        name varchar2(30) not null,
                        phone varchar2(30),
                        address varchar2(50)
);

-- 테이블 내용 추가
insert into member values(seq_member.nextval,
                          'yongkeun@induk.ac.kr', 'cometrue', 'yongkeun', '', '');
insert into member values(seq_member.nextval,
                          'root@induk.ac.kr', 'cometrue', 'root', '', '');

insert into m201912022 values(seq_member.nextval,
                          'yongkeun@induk.ac.kr', 'cometrue', 'yongkeun', '', '');
insert into m201912022 values(seq_member.nextval,
                          'root@induk.ac.kr', 'cometrue', 'root', '', '');


select * from member;

select * from m201912022;
commit;
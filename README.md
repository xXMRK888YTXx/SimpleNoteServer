# SimpleNoteServer
Simple server for learning client-server architecture on Ktor

# SQL Schemes

I use MySQL, but you can use the DBMS that you like

If you change DBMS, replice driver in [build.gradle.kts](https://github.com/xXMRK888YTXx/SimpleNoteServer/blob/master/build.gradle.kts#enroll-beta)

> Account table:

```
Create table Accounts (
id int not null unique auto_increment,
email varchar(100) not null unique,
hashPassword varchar(128) not null,
salt varchar(32) not null,
    
primary key(id)
)

```
> Token table 

```
create table Tokens(
accountId int not null,
token varchar(128) not null unique,
userAgent varchar(128) not null,
    
foreign key (accountId) references Accounts(id) on update cascade on delete cascade 
)

```
> Note table
```
create table Notes(
noteId int not null unique auto_increment,
accountOwnerId int not null,
noteTitle varchar(100) not null,
noteText Text not null,
    
primary key(noteId),
foreign key (accountOwnerId) references Accounts(id) on update cascade on delete cascade
)
```

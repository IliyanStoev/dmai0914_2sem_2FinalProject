
use TicketingConsole

create table employee ( 
cpr varchar(40) primary key(cpr),
fname varchar(30),
lname varchar(30),
email varchar(40),
phone varchar(40),
password varchar(30),
tokens int,
isManager bit );



create table event ( 
eid int identity(1,1) not null primary key(eid),
name varchar(40),
description varchar(400),
budjet decimal(10,5),
startDate varchar(50),
deadlineDate varchar(50));



create table ticketType ( 
ttid int identity(1,1) not null primary key(ttid),
name varchar(40),
description varchar(400),
price decimal(10,5),
amount int,
inStock int,
eid int,
foreign key (eid) references event(eid));


create table ticket ( 
barcode bigint not null primary key(barcode),
ttid int,
foreign key (ttid) references ticketType(ttid));


create table receipt ( 
receiptNo int primary key(receiptNo),
rDate varchar(50),
amount int);



create table booking ( 
bid int identity(1,1) not null primary key(bid),
date varchar(50),
status varchar(40),
total decimal(10,5),
cpr varchar(40),
receiptNo int,
foreign key (cpr) references employee(cpr),
foreign key (receiptNo) references receipt(receiptNo));


create table bookingLine ( 
quantity int,
bid int,
ttid int,
foreign key (bid) references booking(bid),
foreign key (ttid) references ticketType(ttid));


create table eStatistics (
ticketsSold int,
bookingNo int,
eid int,
foreign key (eid) references event(eid));


create table empEvent ( 
cpr varchar(40),
eid int,
foreign key (cpr) references employee(cpr),
foreign key (eid) references event(eid));

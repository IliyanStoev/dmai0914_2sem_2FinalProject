
use TicketingConsole

create table employee ( 
cpr varchar(40) primary key(cpr),
fname varchar(30),
lname varchar(30),
email varchar(40),
phone varchar(40),
password varchar(30),
tokens int,
isManager bit,
company varchar(50) );



create table event ( 
eid int identity(1,1) not null primary key(eid),
name varchar(40),
description varchar(400),
budget decimal(10,5),
evDate date,
cpr varchar(40), 
foreign key (cpr) references employee(cpr));



create table ticketType ( 
ttid int identity(1,1) not null primary key(ttid),
name varchar(40),
description varchar(400),
price decimal(10,5),
inStock int,
eid int,
foreign key (eid) references event(eid));


create table ticket ( 
barcode varchar(60) primary key(barcode),
bid int,
foreign key (bid) references booking(bid));


create table receipt ( 
receiptNo int primary key(receiptNo),
rDate varchar(50),
amount int);



create table booking ( 
bid int identity(1,1) not null primary key(bid),
bdate date,
status varchar(40),
total decimal(10,5),
gname varchar(40),
gphone varchar(40),
gemail varchar(40),
quantity int,
ttid int,
cpr varchar(40),
receiptNo int,
foreign key (ttid) references ticketType(ttid),
foreign key (cpr) references employee(cpr),
foreign key (receiptNo) references receipt(receiptNo));


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

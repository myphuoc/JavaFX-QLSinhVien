use master 
go

CREATE DATABASE SV

use SV
go

create table Students_Info(
	Id_Student int Not Null,
	First_Name_Student nvarchar(40),
	Last_Name_Student nvarchar(40),
	Birthday date,
	Average float,
	Constraint fk_Id_Student Primary Key(Id_Student)
)
create table City(
	Id_City int,
	Id_Student int,
	Name_City nvarchar(30),
	Primary Key (Id_City), 
	Constraint pk_Id_Student_City Foreign Key (Id_Student) References Students_Info(Id_Student)
)
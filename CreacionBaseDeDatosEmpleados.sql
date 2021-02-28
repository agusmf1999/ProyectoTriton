DROP DATABASE IF EXISTS empresa;
CREATE DATABASE empresa;
CREATE TABLE empresa.empleados(
	nombre_completo varchar(50) not null,
	fecha_nac date not null,
	ci int not null,
	salario_base int not null,
	fecha_ingreso date not null,
  baja_logica bool not null,
  CONSTRAINT CHK_ci CHECK (ci<100000000),
  CONSTRAINT CHK_fecha_nac CHECK (fecha_nac<'2005-01-01'),
	CONSTRAINT PK_empleado primary key (ci)
);


INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Juan Martin Suarez Lopez', '1992-06-22', 45668714, 32000, '2015-05-06',false);
INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Abelardo Lirio Rosadilla', '1976-08-20', 25466872, 40000, '2000-12-22',false);
INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Emilia Natalia Fernandez Ferna', '1980-08-20', 32569764, 35000, '2005-06-02',false);
INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Roberto Gomensoro Marquez', '1999-08-20', 44589764, 25000, '2020-04-05',false);
INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Sebastian Curbelo Sosa', '1990-08-20', 44789301, 50000, '2010-01-15',false);
INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Martin Roberto Soldado Smith', '1995-08-20', 45796321, 45000, '2018-03-30',false);
INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values('Camila Alejandra Mendoza Mader', '1998-08-20', 54123798, 30000, '2019-08-10',false);
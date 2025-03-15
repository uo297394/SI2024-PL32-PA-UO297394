--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table if EXISTS Colegiados;
drop table if EXISTS Cursos;
drop table if EXISTS Inscripciones;
drop table if EXISTS Sesiones;
drop table if EXISTS Periciales;
drop table if EXISTS Cuotas;
drop table if EXISTS Otros;
drop table if EXISTS Solicitante;

create table Colegiados (id int primary key not null, nombre varchar(20) not null, apellido varchar(40) not null,
	DNI varchar(9), direccion varchar(60),correo varchar(50), telefono varchar(20), fecha_nacimiento date, numero_cuenta int not null,
 	banco varchar(20),precolegiados boolean, estado_solicitud enum, fecha_solicitud date, titulacion varchar(40),
 	solicitud_perito boolean, es_perito boolean, orden_TAP int);
 
create table Cursos (id int primary key not null, titulo_curso varchar(20), descripcion varchar(60),
	fecha_inicio_curso date, fecha_fin_curso date, duracion int ,max_plazas int,
 	colectivos varchar(20), fecha_inicio_inscripcion date, fecha_fin_inscripcion date);

create table Inscripciones (id int primary key not null, idColegiado int, idOtros int,
 	idCurso int,fechaInscripcion date, estado enum,FOREIGN KEY (idColegiado) REFERENCES Colegiados(id),
 	FOREIGN KEY (idCurso) REFERENCES Cursos(id),FOREIGN KEY (idOtros) REFERENCES Otros(id));

create table Sesiones (id int primary key not null, idCurso int, 
	nombre_sesion varchar(50), fecha_sesion  date, hora_inicio time,
    duracion int, FOREIGN KEY (idCurso) REFERENCES Cursos(id));
    
create table Periciales (id int auto_increment primary key,idColegiado int, idSolicitante int not null, descripcion varchar(100) not null, estado enum, 
	 caracter enum,FOREIGN KEY (idColegiado) REFERENCES Colegiados(id), FOREIGN KEY (idSolicitante) REFERENCES Solicitante(id));
	  
create table Cuotas (id int primary key not null, idCurso int, cuota int not null,
 	colectivo varchar(40) not null,FOREIGN KEY (idCurso) REFERENCES Cursos(id));
 	
create table Otros (id int primary key not null, nombre varchar(20) not null, apellido varchar(40) not null,
	DNI varchar(9), direccion varchar(60),correo varchar(50), telefono varchar(20), fecha_nacimiento date);

create table Solicitante (id int auto_increment primary key ,nombre varchar(20) not null, apellidos varchar(40) not null, DNI varchar(9) not null,
	 direccion varchar(60),correo varchar(50), telefono varchar(20), fecha_nacimiento date);

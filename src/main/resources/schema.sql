--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table if EXISTS Colegiados;
drop table if EXISTS Cursos;
drop table if EXISTS Inscripciones;

create table Colegiados (id int primary key not null, nombre varchar(20) not null, apellido varchar(40) not null,
DNI varchar(9), direccion varchar(60), fecha_nacimiento date, numero_cuenta int not null,
 banco varchar(20),precolegiados boolean, estado_solicitud enum, fecha_solicitud date);
 
create table Cursos (id int primary key not null, titulo_curso varchar(20), descripcion varchar(60),
fecha_inicio_curso date, fecha_fin_curso date, duracion int ,max_plazas int,cuota float,
 colectivos varchar(20), fecha_inicio_inscripcion date, fecha_fin_inscripcion date, 
 check(fecha_inicio_curso>fecha_fin_inscripcion));

create table Inscripciones (id int primary key not null, idColegiado int,
 idCurso int,fechaInscripcion date,FOREIGN KEY (idColegiado) REFERENCES Colegiados(id),
 FOREIGN KEY (idCurso) REFERENCES Cursos(id));


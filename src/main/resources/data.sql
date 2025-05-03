-- Inserciones para la tabla Colegiados
DELETE FROM Colegiados;
INSERT INTO Colegiados (id, nombre, apellido, DNI, direccion, correo, telefono, fecha_nacimiento, numero_cuenta, banco, precolegiados, estado_solicitud, fecha_solicitud, titulacion, solicitud_perito, es_perito, orden_TAP,motivosCancelacion)
VALUES 
(1, 'Juan', 'Pérez', '12345678A', 'Calle Falsa 123', 'juan.perez@example.com', '600111222', '1985-04-23', 987654321, 'Banco1', FALSE, 'aprobado', '2024-01-10', 'ingenieria mecanica', FALSE, TRUE, 1, NULL);


-- Inserciones para la tabla Cursos
delete from Cursos;
INSERT INTO Cursos (id, titulo_curso, descripcion, fecha_inicio_curso, fecha_fin_curso, duracion, max_plazas, fecha_inicio_inscripcion, fecha_fin_inscripcion, lista_espera)
VALUES 
(1, 'No admito inscripciones', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 1, NULL, NULL,1),
(2, 'Mi plazo ya pasó', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 1, '2000-02-01', '2000-05-31',0),
(3, 'Todavía no es mi plazo', 'Introducción a SQL', '2040-06-01', '2040-06-30', 30, 1, '2039-02-01', '2039-05-31',1),
(4, 'Estoy en plazo', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 1, '2024-02-01', '2026-05-31',0),
(5, 'Admito lista de espera', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 1, '2024-02-01', '2026-05-31',1),
(6, 'Yo no', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 1, '2024-02-01', '2026-05-31',0),
(7, 'No estoy lleno', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 1, '2024-02-01', '2026-05-31',0);
-- Inserciones para la tabla Inscripciones
delete from Inscripciones;
INSERT INTO Inscripciones (id, idColegiado, idCurso, fechaInscripcion, colectivo, deuda, estado)
VALUES 
(1, 1, 5, '2024-05-05','Estudiantes', 0, 0),
(2, 1, 6, '2024-06-10','Estudiantes', 5, 1);


delete from Sesiones;
INSERT INTO Sesiones (id, idCurso, nombre_sesion, fecha_sesion, hora_inicio, duracion)
VALUES 
(1, 1, 'Introducción a SQL', '2027-06-01', '10:00', 2),
(2, 1, 'Consultas Básicas', '2027-06-08', '10:00', 2),
(3, 2, 'Conceptos Avanzados de Python', '2027-07-10', '09:30', 3),
(4, 3, 'Fundamentos de Machine Learning', '2027-09-01', '14:00', 3),
(5, 4, 'HTML y CSS', '2024-10-15', '16:00', 2),
(6, 5, 'Introducción a la Ciberseguridad', '2024-11-20', '11:00', 3);

delete from Cuotas;
INSERT INTO Cuotas(id,idCurso, cuota, colectivo)
VALUES
(1,1,50.0,'Estudiantes'),
(2,2,50.0,'Estudiantes'),
(3,3,50.0,'Estudiantes'),
(4,4,50.0,'Estudiantes'),
(5,5,50.0,'Estudiantes'),
(6,6,50.0,'Estudiantes'),
(7,7,50.0,'Estudiantes');

delete from Otros;
INSERT INTO Otros (id, nombre, apellido, DNI, direccion, correo, telefono, fecha_nacimiento) VALUES
(1, 'Juan', 'Pérez', '12345678Z', 'Calle Falsa 123, Madrid', 'juan.perez@email.com', '600123456', '1985-04-15');



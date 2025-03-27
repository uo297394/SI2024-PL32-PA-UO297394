-- Inserciones para la tabla Colegiados
DELETE FROM Colegiados;
INSERT INTO Colegiados (id, nombre, apellido, DNI, direccion, correo, telefono, fecha_nacimiento, numero_cuenta, banco, precolegiados, estado_solicitud, fecha_solicitud, titulacion, solicitud_perito, es_perito, orden_TAP)
VALUES 
(1, 'Juan', 'Pérez', '12345678A', 'Calle Falsa 123', 'juan.perez@example.com', '600111222', '1985-04-23', 987654321, 'Banco1', FALSE, 'aprobado', '2024-01-10', 'ingenieria mecanica', FALSE, TRUE, 1),
(2, 'María', 'Gómez', '87654321B', 'Avenida Siempre Viva', 'maria.gomez@example.com', '600333444', '1990-07-15', 123456789, 'Banco2', TRUE, 'pendiente', '2024-02-05', 'ingenieria en telecomunicaciones', FALSE, FALSE, 2),
(3, 'Carlos', 'López', '11223344C', 'Plaza Mayor 5', 'carlos.lopez@example.com', '600555666', '1982-12-01', 111222333, 'Banco3', FALSE, 'rechazado', '2024-03-12', 'ingenieria informatica', TRUE, FALSE, 3),
(4, 'Ana', 'Fernández', '44332211D', 'Calle Luna 8', 'ana.fernandez@example.com', '600777888', '1995-09-30', 444555666, 'Banco1', TRUE, 'aprobado', '2024-04-20', 'ingenieria industrial', TRUE, FALSE, 4),
(5, 'Luis', 'Martínez', '66778899E', 'Camino del Sol 21', 'luis.martinez@example.com', '600999000', '1988-06-14', 777888999, 'Banco2', FALSE, 'pendiente', '2024-05-25', 'ingenieria agricola', FALSE, FALSE, 5),
(6, 'Sofía', 'Martín', '33445566F', 'Calle Real 12', 'sofia.martin@example.com', '600666777', '1992-03-10', 222333444, 'Banco3', TRUE, 'aprobado', '2024-06-01', 'ingenieria quimica', FALSE, TRUE, 6),
(7, 'Diego', 'Ramírez', '99887766G', 'Av. del Sol 34', 'diego.ramirez@example.com', '600777888', '1980-11-20', 555666777, 'Banco1', FALSE, 'aprobado', '2024-07-15', 'ingenieria mecanica', FALSE, TRUE, 7),
(8, 'Lucía', 'Hernández', '55667788H', 'Calle del Prado 22', 'lucia.hernandez@example.com', '600888999', '1985-05-05', 888777666, 'Banco2', TRUE, 'aprobado', '2024-08-20', 'ingenieria electronica', FALSE, TRUE, 8),
(9, 'Miguel', 'Sánchez', '44556677I', 'Plaza del Sol 10', 'miguel.sanchez@example.com', '600999111', '1979-10-15', 444333222, 'Banco3', FALSE, 'aprobado', '2024-09-10', 'ingenieria civil', FALSE, TRUE, 9),
(10, 'Carla', 'Jiménez', '22334455J', 'Avenida de la Paz 55', 'carla.jimenez@example.com', '600111333', '1993-07-07', 333222111, 'Banco1', TRUE, 'aprobado', '2024-10-05', 'ingenieria industrial', FALSE, TRUE, 10);


-- Inserciones para la tabla Cursos
delete from Cursos;
INSERT INTO Cursos (id, titulo_curso, descripcion, fecha_inicio_curso, fecha_fin_curso, duracion, max_plazas, fecha_inicio_inscripcion, fecha_fin_inscripcion)
VALUES 
(1, 'SQL Básico', 'Introducción a SQL', '2027-06-01', '2027-06-30', 30, 20, '2024-02-01', '2026-05-31'), 
(2, 'Python Avanzado', 'Programación avanzada en Python', '2027-07-10', '2027-08-10', 30, 25, '2024-06-01', '2026-07-05'), 
(3, 'Machine Learning', 'Fundamentos de ML', '2027-09-01', '2027-10-01', 30, 15, '2026-08-01', '2026-08-31'), 
(4, 'Desarrollo Web', 'Creación de aplicaciones web', '2024-10-15', '2024-11-15', 30, 30, '2024-09-01', '2024-10-10'),
(5, 'Ciberseguridad', 'Seguridad en sistemas', '2024-11-20', '2024-12-20', 30, 20, '2024-10-01', '2024-11-15'), 
(6, 'SQL Básico II', 'Introducción a SQL', '2024-06-01', '2024-06-30', 30, 20, NULL, NULL),
(7, 'Python Avanzado II', 'Programación avanzada en Python', '2024-07-10', '2024-08-10', 30, 25, NULL, NULL),
(8, 'Machine Learning II', 'Fundamentos de ML', '2024-09-01', '2024-10-01', 30, 15, NULL, NULL),
(9, 'Desarrollo Web II', 'Creación de aplicaciones web', '2024-10-15', '2024-11-15', 30, 30, NULL, NULL),
(10, 'Ciberseguridad II', 'Seguridad en sistemas', '2024-11-20', '2024-12-20', 30, 20, NULL, NULL);


-- Inserciones para la tabla Inscripciones
delete from Inscripciones;
INSERT INTO Inscripciones (id, idColegiado, idCurso, fechaInscripcion,colectivo, estado)
VALUES 
(1, 1, 1, '2024-05-05','Estudiantes', 0),
(2, 2, 2, '2024-06-10','Estudiantes', 1),
(3, 3, 3, '2024-08-15','Estudiantes', 2),
(4, 4, 4, '2024-09-20','Estudiantes', 1),
(5, 5, 5, '2024-10-25','Estudiantes', 1),
(6, 2, 1, '2024-05-05','Estudiantes', 0);
INSERT INTO Inscripciones (id, idOtros, idCurso, fechaInscripcion,colectivo, estado)
VALUES 
(7, 1, 1, '2024-05-05','Estudiantes', 1),
(8, 2, 2, '2024-06-08','Estudiantes', 1),
(9, 3, 3, '2024-08-15','Estudiantes', 2),
(10,4, 4, '2024-09-20','Estudiantes', 1),
(11,5, 5, '2024-10-25','Estudiantes', 1),
(12, 2, 1, '2024-05-05','Estudiantes', 0);

delete from Sesiones;
INSERT INTO Sesiones (id, idCurso, nombre_sesion, fecha_sesion, hora_inicio, duracion)
VALUES 
(1, 1, 'Introducción a SQL', '2027-06-01', '10:00', 2),
(2, 1, 'Consultas Básicas', '2027-06-08', '10:00', 2),
(3, 2, 'Conceptos Avanzados de Python', '2027-07-10', '09:30', 3),
(4, 3, 'Fundamentos de Machine Learning', '2027-09-01', '14:00', 3),
(5, 4, 'HTML y CSS', '2024-10-15', '16:00', 2),
(6, 5, 'Introducción a la Ciberseguridad', '2024-11-20', '11:00', 3);


delete from Solicitante;
INSERT INTO Solicitante(id,nombre, apellidos, DNI,direccion,correo, telefono, fecha_nacimiento)
VALUES
(1, 'Manolo', 'Preciado', '46723479Z','Calle Falsa 33','manolitopreci@gmail.com','600111222','1957-08-28'),
(2, 'Pedro', 'Picapiedra', '46723579Y','Calle de los amores 54','pedritoGOAT@gmail.com','74151622','1989-9-18'),
(3, 'Bulma', 'Kakarot', '76423479II','Peña del cristo 43','vegetrunks@gmail.com','330111256','1957-08-28');


delete from Cuotas;
INSERT INTO Cuotas(id,idCurso, cuota, colectivo)
VALUES
(1,1,50.0,'Estudiantes'),
(2,2,50.0,'Estudiantes'),
(3,3,50.0,'Estudiantes'),
(4,4,50.0,'Estudiantes'),
(5,5,50.0,'Estudiantes'),
(6,6,50.0,'Estudiantes'),
(7,7,50.0,'Estudiantes'),
(8,8,50.0,'Estudiantes'),
(9,9,50.0,'Estudiantes'),
(10,10,50.0,'Estudiantes'),
(11,1,10.0,'Profesores'),
(12,1,20.0,'Empresa'),
(13,2,50.0,'Estudiantes'),
(14,3,50.0,'Estudiantes'),
(15,4,50.0,'Estudiantes');


delete from Periciales;
INSERT INTO Periciales(id ,idColegiado, idSolicitante, descripcion, estado, caracter)
VALUES
(1,NULL,1,'Extraer metadatos de la foto','pendiente','Urgente'),
(2,NULL,3,'Análisis forense de un disco duro','pendiente','Urgente'),
(3,NULL,5,'Aalizar geolocalización de un movil','pendiente','Normal'),
(4,NULL,2,'Inspeccionar coche accidentado','pendiente','Normal');

delete from Otros;
INSERT INTO Otros (id, nombre, apellido, DNI, direccion, correo, telefono, fecha_nacimiento) VALUES
(1, 'Juan', 'Pérez', '12345678Z', 'Calle Falsa 123, Madrid', 'juan.perez@email.com', '600123456', '1985-04-15'),
(2, 'María', 'Gómez', '23456789X', 'Av. Principal 45, Barcelona', 'maria.gomez@email.com', '611234567', '1990-07-22'),
(3, 'Carlos', 'López', '34567890V', 'Plaza Mayor 12, Valencia', 'carlos.lopez@email.com', '622345678', '1982-12-10'),
(4, 'Ana', 'Martínez', '38527190T', 'C/ Secundaria 8, Sevilla', 'ana.martinez@email.com', '633456789', '1995-05-30'),
(5, 'Pedro', 'Fernández', '56789012D', 'Paseo del Río 99, Zaragoza', NULL, '644567890', '1978-09-05');

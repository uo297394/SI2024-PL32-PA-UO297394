-- Inserciones para la tabla Colegiados
delete from Colegiados;
INSERT INTO Colegiados (id, nombre, apellido, DNI, direccion, fecha_nacimiento, numero_cuenta, banco, precolegiados, estado_solicitud, fecha_solicitud, titulacion)
VALUES 
(1, 'Juan', 'Pérez', '12345678A', 'Calle Falsa 123', '1985-04-23', 987654321, 'Banco1', FALSE, 'aprobado', '2024-01-10', 'ingenieria mecanica'),
(2, 'María', 'Gómez', '87654321B', 'Avenida Siempre Viva', '1990-07-15', 123456789, 'Banco2', TRUE, 'pendiente', '2024-02-05', 'ingenieria en telecomunicaciones'),
(3, 'Carlos', 'López', '11223344C', 'Plaza Mayor 5', '1982-12-01', 111222333, 'Banco3', FALSE, 'rechazado', '2024-03-12', 'ingenieria informatica'),
(4, 'Ana', 'Fernández', '44332211D', 'Calle Luna 8', '1995-09-30', 444555666, 'Banco1', TRUE, 'aprobado', '2024-04-20', 'ingenieria industrial'),
(5, 'Luis', 'Martínez', '66778899E', 'Camino del Sol 21', '1988-06-14', 777888999, 'Banco2', FALSE, 'pendiente', '2024-05-25', 'ingenieria agricola');

-- Inserciones para la tabla Cursos
delete from Cursos;
INSERT INTO Cursos (id, titulo_curso, descripcion, fecha_inicio_curso, fecha_fin_curso, duracion, max_plazas, cuota, colectivos, fecha_inicio_inscripcion, fecha_fin_inscripcion)
VALUES 
(1, 'SQL Básico', 'Introducción a SQL', '2024-06-01', '2024-06-30', 30, 20, 50.0, 'Estudiantes', '2024-05-01', '2024-05-31'),
(2, 'Python Avanzado', 'Programación avanzada en Python', '2024-07-10', '2024-08-10', 30, 25, 100.0, 'Profesionales', '2024-06-01', '2024-07-05'),
(3, 'Machine Learning', 'Fundamentos de ML', '2024-09-01', '2024-10-01', 30, 15, 150.0, 'Investigadores', '2024-08-01', '2024-08-31'),
(4, 'Desarrollo Web', 'Creación de aplicaciones web', '2024-10-15', '2024-11-15', 30, 30, 80.0, 'Estudiantes', '2024-09-01', '2024-10-10'),
(5, 'Ciberseguridad', 'Seguridad en sistemas', '2024-11-20', '2024-12-20', 30, 20, 120.0, 'Empresas', '2024-10-01', '2024-11-15');

-- Inserciones para la tabla Inscripciones
delete from Inscripciones;
INSERT INTO Inscripciones (id, idColegiado, idCurso, fechaInscripcion)
VALUES 
(1, 1, 1, '2024-05-05'),
(2, 2, 2, '2024-06-10'),
(3, 3, 3, '2024-08-15'),
(4, 4, 4, '2024-09-20'),
(5, 5, 5, '2024-10-25');

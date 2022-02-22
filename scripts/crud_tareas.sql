CREATE DATABASE tareas;

DROP TABLE IF EXISTS tareas.`tarea`;
CREATE TABLE tareas.`tarea` (
  `identificador` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `vigente` bit(1) NOT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

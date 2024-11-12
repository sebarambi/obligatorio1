CREATE DATABASE  IF NOT EXISTS `obligatorio_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `obligatorio_database`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: obligatorio_database
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `idCiudad` int NOT NULL,
  `nombreCiudad` varchar(30) DEFAULT NULL,
  `idPais` int DEFAULT NULL,
  PRIMARY KEY (`idCiudad`),
  KEY `idPais` (`idPais`),
  CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Buenos Aires',1),(2,'Córdoba',1),(3,'Mendoza',1),(4,'São Paulo',2),(5,'Río de Janeiro',2),(6,'Brasilia',2),(7,'Santiago',3),(8,'Valparaíso',3),(9,'Concepción',3),(10,'Bogotá',4),(11,'Medellín',4),(12,'Cali',4),(13,'Ciudad de México',5),(14,'Guadalajara',5),(15,'Monterrey',5),(16,'Lima',6),(17,'Cusco',6),(18,'Arequipa',6),(19,'Montevideo',7),(20,'Punta del Este',7),(21,'Salto',7);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacion` (
  `idHabitacion` int NOT NULL,
  `capacidadCamas` int NOT NULL,
  `camaDoble` tinyint(1) NOT NULL,
  `idTipoHab` int NOT NULL,
  `aireAcondicionado` tinyint(1) NOT NULL,
  `balcon` tinyint(1) NOT NULL,
  `idHotel` int NOT NULL,
  PRIMARY KEY (`idHabitacion`),
  KEY `idTipoHab` (`idTipoHab`),
  KEY `idHotel` (`idHotel`),
  CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`idTipoHab`) REFERENCES `tipohabitacion` (`idTipoHab`),
  CONSTRAINT `habitacion_ibfk_2` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,1,0,1,1,0,1),(2,2,1,2,1,1,1),(3,3,0,3,1,0,2),(4,4,1,4,1,1,2),(5,2,0,5,1,0,3),(6,2,1,6,1,1,3),(7,1,0,1,1,0,4);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `idHotel` int NOT NULL,
  `nombreHotel` varchar(30) NOT NULL,
  `idPais` int NOT NULL,
  `idCiudad` int NOT NULL,
  `cantidadEstrellas` int NOT NULL,
  `direccion` varchar(50) NOT NULL,
  PRIMARY KEY (`idHotel`),
  KEY `idPais` (`idPais`),
  KEY `idCiudad` (`idCiudad`),
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`),
  CONSTRAINT `hotel_ibfk_2` FOREIGN KEY (`idCiudad`) REFERENCES `ciudad` (`idCiudad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Hotel Buenos Aires',1,1,5,'Av. Corrientes 1000, Buenos Aires'),(2,'Hotel Río de Janeiro',2,8,4,'Av. Atlântica 2000, Río de Janeiro'),(3,'Hotel Santiago',3,7,4,'Paseo Bulnes 500, Santiago'),(4,'Hotel Bogotá',4,10,3,'Calle 26 # 50-40, Bogotá'),(5,'Hotel Ciudad de México',5,13,5,'Paseo de la Reforma 300, Ciudad de México'),(6,'Hotel Lima',6,16,3,'Av. Pardo 2000, Lima'),(7,'Hotel Punta del Este',7,20,5,'Avenida Gorlero 900, Punta del Este');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huesped`
--

DROP TABLE IF EXISTS `huesped`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huesped` (
  `idHuesped` int NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `aPaterno` varchar(30) NOT NULL,
  `aMaterno` varchar(30) NOT NULL,
  `idTipoDoc` int NOT NULL,
  `numDocumento` varchar(15) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `idPais` int NOT NULL,
  PRIMARY KEY (`idHuesped`),
  KEY `idTipoDoc` (`idTipoDoc`),
  KEY `idPais` (`idPais`),
  CONSTRAINT `huesped_ibfk_1` FOREIGN KEY (`idTipoDoc`) REFERENCES `tipodocumento` (`idTipoDoc`),
  CONSTRAINT `huesped_ibfk_2` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huesped`
--

LOCK TABLES `huesped` WRITE;
/*!40000 ALTER TABLE `huesped` DISABLE KEYS */;
INSERT INTO `huesped` VALUES (1,'Juan','Pérez','González',1,'12345678','1980-05-15','091234567',1),(2,'María','López','Ramírez',2,'A12345678','1990-08-20','091876543',2),(3,'Carlos','Gómez','Martínez',1,'23456789','1985-03-10','091345678',3),(4,'Ana','Rodríguez','Fernández',3,'B98765432','1992-07-25','091765432',4),(5,'Luis','Martínez','García',1,'34567890','1975-11-05','091234567',5),(6,'Sofía','Fernández','Pérez',2,'C12345678','1988-02-12','091876543',6),(7,'Pedro','Sánchez','Vázquez',3,'D87654321','2000-06-30','091987654',7);
/*!40000 ALTER TABLE `huesped` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocupaciones`
--

DROP TABLE IF EXISTS `ocupaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocupaciones` (
  `idOcupacion` int NOT NULL AUTO_INCREMENT,
  `idHabitacion` int DEFAULT NULL,
  `idReserva` int DEFAULT NULL,
  `idHuesped` int DEFAULT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `reservada_por_sistema` tinyint(1) DEFAULT NULL,
  `precio` int DEFAULT NULL,
  PRIMARY KEY (`idOcupacion`),
  KEY `idHabitacion` (`idHabitacion`),
  KEY `idHuesped` (`idHuesped`),
  KEY `idReserva` (`idReserva`),
  CONSTRAINT `ocupaciones_ibfk_1` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  CONSTRAINT `ocupaciones_ibfk_2` FOREIGN KEY (`idHuesped`) REFERENCES `huesped` (`idHuesped`),
  CONSTRAINT `ocupaciones_ibfk_3` FOREIGN KEY (`idReserva`) REFERENCES `reservas` (`idReserva`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocupaciones`
--

LOCK TABLES `ocupaciones` WRITE;
/*!40000 ALTER TABLE `ocupaciones` DISABLE KEYS */;
INSERT INTO `ocupaciones` VALUES (19,1,22,1,'2024-11-15','2024-11-20',1,500),(20,2,23,2,'2024-11-16','2024-11-18',1,300),(21,3,24,3,'2024-11-18','2024-11-22',1,750),(22,4,25,4,'2024-11-10','2024-11-12',1,400),(23,5,26,5,'2024-11-19','2024-11-25',1,600),(24,2,NULL,2,'2024-11-21','2024-11-22',0,300);
/*!40000 ALTER TABLE `ocupaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `idPais` int NOT NULL,
  `nombrePais` varchar(20) NOT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Argentina'),(2,'Brasil'),(3,'Chile'),(4,'Colombia'),(5,'México'),(6,'Perú'),(7,'Uruguay');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `idReserva` int NOT NULL AUTO_INCREMENT,
  `idHuesped` int NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `cantidadPersonas` int NOT NULL,
  `observaciones` text,
  `fechaReserva` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `idHabitacion` int DEFAULT NULL,
  `precioTotal` int DEFAULT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `idHuesped` (`idHuesped`),
  KEY `fk_idHabitacion` (`idHabitacion`),
  CONSTRAINT `fk_idHabitacion` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`idHuesped`) REFERENCES `huesped` (`idHuesped`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (22,1,'2024-11-15','2024-11-20',2,'Vacaciones en familia','2024-11-12 20:59:30',1,500),(23,2,'2024-11-16','2024-11-18',1,'Viaje de negocios','2024-11-12 20:59:30',2,300),(24,3,'2024-11-18','2024-11-22',3,'Evento especial','2024-11-12 20:59:30',3,750),(25,4,'2024-11-10','2024-11-12',2,'Luna de miel','2024-11-12 20:59:30',4,400),(26,5,'2024-11-19','2024-11-25',1,'Turismo','2024-11-12 20:59:30',5,600),(27,1,'2024-11-15','2024-11-20',2,'Reserva de vacaciones','2024-11-12 21:07:05',1,500),(28,7,'2024-11-15','2024-11-22',1,'','2024-11-12 21:12:05',7,3500);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifa` (
  `idTarifa` int NOT NULL,
  `monto` int DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  PRIMARY KEY (`idTarifa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,500,'2024-01-01'),(2,900,'2024-01-01'),(3,1500,'2024-01-01'),(4,2500,'2024-01-01'),(5,1200,'2024-01-01'),(6,5000,'2024-01-01');
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipodocumento` (
  `idTipoDoc` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`idTipoDoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumento`
--

LOCK TABLES `tipodocumento` WRITE;
/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
INSERT INTO `tipodocumento` VALUES (1,'DNI'),(2,'Pasaporte'),(3,'Cédula de Identidad'),(4,'Licencia de Conducir'),(5,'Tarjeta de Residencia');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipohabitacion`
--

DROP TABLE IF EXISTS `tipohabitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipohabitacion` (
  `idTipoHab` int NOT NULL,
  `descripcion` varchar(30) DEFAULT NULL,
  `idTarifa` int DEFAULT NULL,
  PRIMARY KEY (`idTipoHab`),
  KEY `idTarifa` (`idTarifa`),
  CONSTRAINT `tipohabitacion_ibfk_1` FOREIGN KEY (`idTarifa`) REFERENCES `tarifa` (`idTarifa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipohabitacion`
--

LOCK TABLES `tipohabitacion` WRITE;
/*!40000 ALTER TABLE `tipohabitacion` DISABLE KEYS */;
INSERT INTO `tipohabitacion` VALUES (1,'Individual',1),(2,'Doble',2),(3,'Suite Junior',3),(4,'Suite Ejecutiva',4),(5,'Familiar',5),(6,'Suite Presidencial',6);
/*!40000 ALTER TABLE `tipohabitacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-12 18:15:58

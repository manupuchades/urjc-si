-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `iata` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'Madrid','España','MAD','Adolfo Suarez Madrid-Barajas'),(2,'Barcelona','España','BCN','Josep Tarradellas Barcelona-El Prat'),(3,'Palma de Mallorca','España','PMI','Palma de Mallorca'),(4,'Malaga','España','AGP','Malaga-Costa del Sol'),(5,'Alicante','España','ALC','Alicante-Elche'),(6,'Madrid','España','ECV','Madrid-Cuatro Vientos');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_member`
--

DROP TABLE IF EXISTS `crew_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_member`
--

LOCK TABLES `crew_member` WRITE;
/*!40000 ALTER TABLE `crew_member` DISABLE KEYS */;
INSERT INTO `crew_member` VALUES (1,'Company','001','pilot01','lastName01','Pilot'),(2,'Company','002','pilot02','lastName02','Pilot'),(3,'Company','102','co-pilot02','co-lastName02','Co-Pilot'),(4,'Company','003','pilot03','lastName03','Pilot');
/*!40000 ALTER TABLE `crew_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `builder` varchar(255) DEFAULT NULL,
  `flight_hours` int NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;
/*!40000 ALTER TABLE `plane` DISABLE KEYS */;
INSERT INTO `plane` VALUES (1,'Airbus',100000,'A320','REF001'),(2,'Boeing',1000,'737','REF002'),(3,'Airbus',40000,'A350','REF003'),(4,'Airbus',2000,'A321','REF004');
/*!40000 ALTER TABLE `plane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `departure_date_time` datetime(6) DEFAULT NULL,
  `flight_duration` double NOT NULL,
  `flight_id` varchar(255) DEFAULT NULL,
  `arrival_id` bigint DEFAULT NULL,
  `departure_id` bigint DEFAULT NULL,
  `plane_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpe5jkhsyl71o408sf4936fy3l` (`arrival_id`),
  KEY `FKaxqek9h4f7km4qg67twbx2go5` (`departure_id`),
  KEY `FK7p9fvp6d7uh9cgn47uet8a8nb` (`plane_id`),
  CONSTRAINT `FK7p9fvp6d7uh9cgn47uet8a8nb` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`),
  CONSTRAINT `FKaxqek9h4f7km4qg67twbx2go5` FOREIGN KEY (`departure_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKpe5jkhsyl71o408sf4936fy3l` FOREIGN KEY (`arrival_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'Company','2021-02-14 19:50:06.074799',2.5,'MON00',2,1,1),(2,'Company','2021-02-14 22:50:06.074799',2.5,'MON03',2,1,1),(3,'Company','2021-02-14 19:50:06.074799',2.5,'MON00',3,1,2),(4,'Company','2021-02-14 20:50:06.074799',2.5,'MON03',3,1,2);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_crew`
--

DROP TABLE IF EXISTS `flight_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_crew` (
  `flight_id` bigint NOT NULL,
  `crew_member_id` bigint NOT NULL,
  PRIMARY KEY (`flight_id`,`crew_member_id`),
  KEY `FKc2eyh6mssrxikhpw1l0f578a` (`crew_member_id`),
  CONSTRAINT `FKc2eyh6mssrxikhpw1l0f578a` FOREIGN KEY (`crew_member_id`) REFERENCES `crew_member` (`id`),
  CONSTRAINT `FKdxicgx6j3pmoveat3b67tnj49` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_crew`
--

LOCK TABLES `flight_crew` WRITE;
/*!40000 ALTER TABLE `flight_crew` DISABLE KEYS */;
INSERT INTO `flight_crew` VALUES (1,1),(2,2),(2,3),(3,4);
/*!40000 ALTER TABLE `flight_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mechanic`
--

DROP TABLE IF EXISTS `mechanic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mechanic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `seniority` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mechanic`
--

LOCK TABLES `mechanic` WRITE;
/*!40000 ALTER TABLE `mechanic` DISABLE KEYS */;
INSERT INTO `mechanic` VALUES (1,'Iberia','MEC001','MecName01','MecSurname01','Master',2000),(2,'Iberia','MEC002','MecName02','MecSurname02','Master',2000),(3,'Iberia','MEC003','MecName03','MecSurname03','Master',2000);
/*!40000 ALTER TABLE `mechanic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `review_duration` double DEFAULT NULL,
  `review_type` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `airport_id` bigint DEFAULT NULL,
  `mechanic_id` bigint DEFAULT NULL,
  `plane_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfiifebyp058mg84wscmxrxyyk` (`airport_id`),
  KEY `FKqbvyg26idtfd92sgqpy6h2wt` (`mechanic_id`),
  KEY `FKe1kp6wijwg4mn3979be8uwgjm` (`plane_id`),
  CONSTRAINT `FKe1kp6wijwg4mn3979be8uwgjm` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`),
  CONSTRAINT `FKfiifebyp058mg84wscmxrxyyk` FOREIGN KEY (`airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKqbvyg26idtfd92sgqpy6h2wt` FOREIGN KEY (`mechanic_id`) REFERENCES `mechanic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'Monthly review','2021-02-10',12.5,'MONTHLY','2021-02-07',NULL,1,1),(2,'Monthly review','2021-01-10',12.5,'MONTHLY','2021-01-07',NULL,2,1),(3,'Monthly review','2021-01-10',12.5,'MONTHLY','2021-01-07',NULL,1,2);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-14 20:54:44

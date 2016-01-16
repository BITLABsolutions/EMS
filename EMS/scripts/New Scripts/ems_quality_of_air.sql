-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `quality_of_air`
--

DROP TABLE IF EXISTS `quality_of_air`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_of_air` (
  `quality_id` int(11) NOT NULL AUTO_INCREMENT,
  `co2` int(11) DEFAULT NULL,
  `o2` int(11) DEFAULT NULL,
  `n2` int(11) DEFAULT NULL,
  `humadity` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `sensor_id` varchar(45) DEFAULT NULL,
  `measuring_frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`quality_id`),
  KEY `sensor_id` (`sensor_id`),
  CONSTRAINT `quality_of_air_ibfk_1` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quality_of_air`
--

LOCK TABLES `quality_of_air` WRITE;
/*!40000 ALTER TABLE `quality_of_air` DISABLE KEYS */;
INSERT INTO `quality_of_air` (`quality_id`, `co2`, `o2`, `n2`, `humadity`, `time`, `sensor_id`, `measuring_frequency`) VALUES (1,25,1,74,5,'2009-09-22 10:00:00','15',NULL),(2,24,2,74,10,'2009-09-24 10:00:00','15',NULL);
/*!40000 ALTER TABLE `quality_of_air` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-15 21:00:26

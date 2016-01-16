CREATE DATABASE  IF NOT EXISTS `ems` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ems`;
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
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `sensor_id` varchar(20) NOT NULL,
  `street` varchar(45) DEFAULT NULL,
  `nearest_junction` varchar(45) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`sensor_id`,`latitude`,`longitude`),
  UNIQUE KEY `sensor_id_UNIQUE` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('1','Main street','Thotalanga',6.9139988,79.9001589),('10','Main street','Thotalanga',6.9402808,79.8969883),('11','Navam mawatha','Gangaaramaya',6.9183299,79.9270311),('12','Borella road','Navala',6.948017,79.9692228),('13','Navala road','Maradana',6.938017,79.9093),('14','De seram road','Modara',6.91878017,79.912234),('15','Main street','Thotalanga',6.92878017,79.92234325),('16','Mihindu mawatha','Keselwatta',6.91878017,79.92391313),('17','Baseline road','Angoda',6.9580117,79.89912312),('18','Horton place','Nelum Pokuna',6.90878017,79.8892228),('19','de mal road','Kollupitiya',6.9281017,79.8923228),('2','unionPlace','Lipton',6.934878017,79.8692228),('20','Fathima road','Bambalapitiya',6.93878017,79.879122228),('3','Slave island','navaloka',6.94878017,79.8812321),('4','Galle road','Wellawaththa',6.93878017,79.89453492228),('5','Main street','Thotalanga',6.9277878017,79.8745228),('6','main','maradana',6.935878017,79.8685345432228),('7','Galle road','Galle face',6.93878017,79.854692228),('8','Queen street','harbour',6.91878017,79.853692228),('9','King street','Pettah',6.94718017,79.862692228);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-16 17:30:11

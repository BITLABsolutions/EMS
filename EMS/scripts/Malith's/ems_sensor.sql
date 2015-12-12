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
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sensor` (
  `sensor_id` varchar(10) NOT NULL,
  `serial_no` varchar(45) NOT NULL,
  `installed_date` date DEFAULT NULL,
  `measure_types` varchar(45) NOT NULL,
  PRIMARY KEY (`sensor_id`,`serial_no`),
  UNIQUE KEY `sensor_id_UNIQUE` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
INSERT INTO `sensor` (`sensor_id`, `serial_no`, `installed_date`, `measure_types`) VALUES ('2','12345','2015-12-12','temperature'),('3','123545','2015-12-12','temperature'),('6','123445','2015-12-12','temperature');
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-12 19:39:05
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `ems`.`sensor_BEFORE_INSERT` BEFORE INSERT ON `sensor` FOR EACH ROW
BEGIN
	IF NOT (NEW.measure_types = "temperature" or  NEW.measure_types = "rainfall" or NEW.measure_types = "wind" or NEW.measure_types = "quality_of_air") THEN
        SIGNAL SQLSTATE '22023'
		SET MESSAGE_TEXT = 'invalid value for sensor type, it should be temperature, rainfall, wind or quality_of_air';
    END IF;
END$$
DELIMITER ;
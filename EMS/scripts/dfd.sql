use ems;

CREATE DATABASE  IF NOT EXISTS `emds`;
USE `emds`;

DROP TABLE IF EXISTS `sensor`;

CREATE TABLE `sensor` (
  `sensor_id` varchar(10) NOT NULL,
  `serial_no` varchar(45) NOT NULL,
  `installed_date` date DEFAULT NULL,
  `measure_types` varchar(45) NOT NULL,
  PRIMARY KEY (`sensor_id`,`serial_no`),
  UNIQUE KEY `sensor_id_UNIQUE` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `sensor_id` varchar(20) NOT NULL,
  `street` varchar(45) DEFAULT NULL,
  `nearest_junction` varchar(45) DEFAULT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  PRIMARY KEY (`sensor_id`,`longitude`,`latitude`),
  FOREIGN KEY (`sensor_id`,`longitude`,`latitude`) REFERENCES sensor(sensor_id),
  UNIQUE KEY `sensor_id_UNIQUE` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
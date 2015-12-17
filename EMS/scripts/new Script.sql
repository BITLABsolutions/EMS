CREATE DATABASE  IF NOT EXISTS `emlls`;
USE `emlls`;


DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `nic` varchar(12) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `access_level` int(11) NOT NULL,
  `street` varchar(100) DEFAULT NULL,
  `town` varchar(100) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `emp_id_UNIQUE` (`emp_id`),
  UNIQUE KEY `nic_UNIQUE` (`nic`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `sensor`;

CREATE TABLE `sensor` (
  `sensor_id` varchar(10) ,
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
  UNIQUE KEY `sensor_id_UNIQUE` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




DROP TABLE IF EXISTS `notification`;
CREATE TABLE notification(
notification_id INT  AUTO_INCREMENT,
sensor_id varchar(10) NOT NULL,
genarated_date date NOT NULL,
message varchar(500) NOT NULL,
priority varchar(10),
PRIMARY KEY(notification_id),
FOREIGN KEY (sensor_id) REFERENCES sensor(sensor_id)
);

DROP TABLE IF EXISTS `destination`;
CREATE TABLE destination(
notification_id INT,
destiny varchar(30),
PRIMARY KEY(notification_id,destiny),
FOREIGN KEY (notification_id) REFERENCES notification(notification_id)
);


  DROP TABLE IF EXISTS temperature;
CREATE TABLE temperature (
  temperature_id INT NOT NULL AUTO_INCREMENT,
  temp_value INT NULL,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency INT NULL,
  PRIMARY KEY (temperature_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );

  
  DROP TABLE IF EXISTS rainfall;
CREATE TABLE rainfall (
  rainfall_id INT NOT NULL AUTO_INCREMENT,
  height INT NULL,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency INT NULL,
  PRIMARY KEY (rainfall_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );

  
  DROP TABLE IF EXISTS wind;
  CREATE TABLE wind (
  wind_id INT NOT NULL AUTO_INCREMENT,
  speed INT NULL,
  direction INT NULL,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency INT NULL,
  PRIMARY KEY (wind_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );

  
  DROP TABLE IF EXISTS quality_of_air;
  CREATE TABLE quality_of_air (
  quality_id INT NOT NULL AUTO_INCREMENT,
  co2 INT NULL,
  o2 INT NULL,
  n2 INT NULL,
  humadity INT NULL,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency INT NULL,
  PRIMARY KEY (quality_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );




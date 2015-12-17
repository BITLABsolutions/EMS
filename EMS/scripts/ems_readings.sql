  
CREATE TABLE temperature (
  temperature_id INT NOT NULL AUTO_INCREMENT,
  temp_value INT NULL,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency INT NULL,
  PRIMARY KEY (temperature_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );

CREATE TABLE rainfall (
  rainfall_id INT NOT NULL AUTO_INCREMENT,
  height INT NULL,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency INT NULL,
  PRIMARY KEY (rainfall_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );

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



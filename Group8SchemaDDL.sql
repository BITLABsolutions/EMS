//Malith

create database if not exists ems;
use ems;

CREATE TABLE employee(
emp_id int(10),
first_name varchar(50), 
last_name varchar(100), 
NIC varchar(10) UNIQUE NOT NULL, 
username varchar(100) UNIQUE NOT NULL, 
password varchar(100), 
access_level char NOT NULL, 
street varchar(100), 
town varchar(100),
phone varchar(12),
primary key (emp_id),
);


// prabath
CREATE TABLE maintains(
emp_id int(10),
sensor_id int(10),
rep_date date not null,
rep_time time,
rep_detail varchar(500)
PRIMARY KEY(employee_id,sensor_id,rep_date,rep_time),
FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
FOREIGN KEY (sensor_id) REFERENCES sensor(sensor_id)
)

CREATE TABLE location(
sensor_id int(10),
street varchar(50) not null, 
nearest_junction varchar(50), 
longitude numeric(5,2), 
latitude numeric(5,2),
PRIMARY KEY(sensor_id,longitude,latitude),
FOREIGN KEY (sensor_id) REFERENCES sensor(sensor_id)
)


//chamin

CREATE TABLE sensor (
sensor_id varchar(10),
serial_num varchar(20) NOT NULL,
installed_date date,
PRIMARY KEY (sensor_id)
);

CREATE TABLE notification(
notification_id INT  AUTO_INCREMENT,
sensor_id varchar(10) NOT NULL,
genarated_date date NOT NULL,
message varchar(500) NOT NULL,
priority varchar(10),
PRIMARY KEY(notification_id),
FOREIGN KEY (sensor_id) REFERENCES sensor(sensor_id)
);

CREATE TABLE destination(
notification_id INT,
destiny varchar(30),
PRIMARY KEY(notification_id,destiny),
FOREIGN KEY (notification_id) REFERENCES notification(notification_id)
);






//Chanaka

CREATE TABLE property_record (
  record_id INT NOT NULL AUTO_INCREMENT,
  time DATETIME NULL,
  sensor_id VARCHAR(45) NULL,
  measuring_frequency VARCHAR(45) NULL,
  PRIMARY KEY (record_id),
  FOREIGN KEY(sensor_id) REFERENCES sensor(sensor_id)
  );
  
CREATE TABLE temperature (
  temperature_id INT NOT NULL AUTO_INCREMENT,
  temp_value VARCHAR(45) NULL,
  PRIMARY KEY (temperature_id),
  FOREIGN KEY(temperature_id) REFERENCES property_record(record_id)
  );

CREATE TABLE rainfall (
  rainfall_id INT NOT NULL AUTO_INCREMENT,
  height VARCHAR(45) NULL,
  PRIMARY KEY (rainfall_id),
  FOREIGN KEY(rainfall_id) REFERENCES property_record(record_id)
  );

  CREATE TABLE wind (
  wind_id INT NOT NULL AUTO_INCREMENT,
  speed VARCHAR(45) NULL,
  direction VARCHAR(45) NULL,
  PRIMARY KEY (wind_id),
  FOREIGN KEY(wind_id) REFERENCES property_record(record_id)
  );

  CREATE TABLE quality_of_air (
  quality_id INT NOT NULL AUTO_INCREMENT,
  co2_per VARCHAR(45) NULL,
  o2_per VARCHAR(45) NULL,
  PRIMARY KEY (quality_id),
  FOREIGN KEY(quality_id) REFERENCES property_record(record_id)
  );

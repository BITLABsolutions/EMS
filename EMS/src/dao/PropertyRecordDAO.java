package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vo.QualityOfAir;
import vo.Rainfall;
import vo.Temperature;
import vo.Wind;

public class PropertyRecordDAO {

    private final Connection myConn;

    public PropertyRecordDAO(Connection myConn) throws IOException, SQLException {
        this.myConn = myConn;
    }

    public Connection getMyConn() {
        return myConn;
    }

    public void addTemperature(Temperature temperatureObject) throws SQLException {
        try {
            String query = "INSERT INTO temperature (temp_value, time, sensor_id, measuring_frequency) values (?,?,?,?)";
            PreparedStatement preStmt = myConn.prepareStatement(query);
            preStmt.setInt(1, temperatureObject.getTempValue());
            preStmt.setTimestamp(2, temperatureObject.getTime());
            preStmt.setString(3, temperatureObject.getSensorID());
            preStmt.setInt(4, temperatureObject.getMeasuringFrequency());

            preStmt.execute();
            myConn.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.out.println("No such foreign key");
            } else {
                System.out.println("Error in addTemperature method");
            }
        }
    }

    public void addWind(Wind windObject) throws SQLException {
        try {
            String query = "INSERT INTO wind (speed, direction, time, sensor_id, measuring_frequency) values (?,?,?,?,?)";
            PreparedStatement preStmt = myConn.prepareStatement(query);
            preStmt.setInt(1, windObject.getSpeed());
            preStmt.setInt(2, windObject.getDirection());
            preStmt.setTimestamp(3, windObject.getTime());
            preStmt.setString(4, windObject.getSensorID());
            preStmt.setInt(5, windObject.getMeasuringFrequency());

            preStmt.execute();
            myConn.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.out.println("No such foreign key");
            } else {
                System.out.println("Error in addWind method");
            }
        }
    }

    public void addRainfall(Rainfall rainfallObject) throws SQLException {
        try {
            String query = "INSERT INTO rainfall (height, time, sensor_id, measuring_frequency) values (?,?,?,?)";
            PreparedStatement preStmt = myConn.prepareStatement(query);
            preStmt.setInt(1, rainfallObject.getHeight());
            preStmt.setTimestamp(2, rainfallObject.getTime());
            preStmt.setString(3, rainfallObject.getSensorID());
            preStmt.setInt(4, rainfallObject.getMeasuringFrequency());

            preStmt.execute();
            myConn.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.out.println("No such foreign key");
            } else {
                System.out.println("Error in addRainfall method");
            }
        }
    }

    public void addQualityOfAir(QualityOfAir qualityOfAirObject) throws SQLException {
        try {
            String query = "INSERT INTO quality_of_air (co2, o2, n2, humadity, time, sensor_id, measuring_frequency) values (?,?,?,?,?,?,?)";
            PreparedStatement preStmt = myConn.prepareStatement(query);
            preStmt.setInt(1, qualityOfAirObject.getCo2());
            preStmt.setInt(2, qualityOfAirObject.getO2());
            preStmt.setInt(3, qualityOfAirObject.getN2());
            preStmt.setInt(4, qualityOfAirObject.getHumadity());
            preStmt.setTimestamp(5, qualityOfAirObject.getTime());
            preStmt.setString(6, qualityOfAirObject.getSensorID());
            preStmt.setInt(7, qualityOfAirObject.getMeasuringFrequency());

            preStmt.execute();
            myConn.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                System.out.println("No such foreign key");
            } else {
                System.out.println("Error in addQualityOfAir method");
            }
        }
    }

    public List<Temperature> getAllTemperature() throws SQLException {

        List<Temperature> temperatureList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT temp_value, time, sensor_id, measuring_frequency FROM temperature");

            while (myRs.next()) {

                Temperature tempTemperature = new Temperature(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                temperatureList.add(tempTemperature);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return temperatureList;
    }

    public List<Wind> getAllWind() throws SQLException {

        List<Wind> windList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT speed, direction, time, sensor_id, measuring_frequency FROM wind");

            while (myRs.next()) {
                Wind tempWind = new Wind(myRs.getInt(1), myRs.getInt(2), myRs.getTimestamp(3), myRs.getString(4), myRs.getInt(5));
                windList.add(tempWind);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return windList;
    }

    public List<Rainfall> getAllRainfall() throws SQLException {

        List<Rainfall> rainfallList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT height, time, sensor_id, measuring_frequency FROM rainfall");

            while (myRs.next()) {

                Rainfall tempRainfall = new Rainfall(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                rainfallList.add(tempRainfall);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return rainfallList;
    }

    public List<QualityOfAir> getAllQualityOfAir() throws SQLException {

        List<QualityOfAir> qualityOfAirList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT co2, o2, n2, humadity, time, sensor_id, measuring_frequency FROM quality_of_air");

            while (myRs.next()) {
                QualityOfAir tempQualityOfAir = new QualityOfAir(myRs.getInt(1), myRs.getInt(2), myRs.getInt(3), myRs.getInt(4), myRs.getTimestamp(5), myRs.getString(6), myRs.getInt(7));
                qualityOfAirList.add(tempQualityOfAir);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return qualityOfAirList;
    }

    public List<Temperature> searchTemperature(String sensorID) throws SQLException {

        List<Temperature> temperatureList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT temp_value, time, sensor_id, measuring_frequency FROM temperature WHERE sensor_id = '" + sensorID + "'");

            while (myRs.next()) {

                Temperature tempTemperature = new Temperature(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                temperatureList.add(tempTemperature);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return temperatureList;
    }

    public List<Wind> searchWind(String sensorID) throws SQLException {

        List<Wind> windList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT speed, direction, time, sensor_id, measuring_frequency FROM wind WHERE sensor_id = '" + sensorID + "'");

            while (myRs.next()) {
                Wind tempWind = new Wind(myRs.getInt(1), myRs.getInt(2), myRs.getTimestamp(3), myRs.getString(4), myRs.getInt(5));
                windList.add(tempWind);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return windList;
    }

    public List<Rainfall> searchRainfall(String sensorID) throws SQLException {

        List<Rainfall> rainfallList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT height, time, sensor_id, measuring_frequency FROM rainfall WHERE sensor_id = '" + sensorID + "'");

            while (myRs.next()) {

                Rainfall tempRainfall = new Rainfall(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                rainfallList.add(tempRainfall);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return rainfallList;
    }

    public List<QualityOfAir> searchQualityOfAir(String sensorID) throws SQLException {

        List<QualityOfAir> qualityOfAirList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT co2, o2, n2, humadity, time, sensor_id, measuring_frequency FROM quality_of_air WHERE sensor_id = '" + sensorID + "'");

            while (myRs.next()) {
                QualityOfAir tempQualityOfAir = new QualityOfAir(myRs.getInt(1), myRs.getInt(2), myRs.getInt(3), myRs.getInt(4), myRs.getTimestamp(5), myRs.getString(6), myRs.getInt(7));
                qualityOfAirList.add(tempQualityOfAir);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return qualityOfAirList;
    }

    public List<Temperature> searchTemperature(Date startDate, Date endDate) throws SQLException {

        List<Temperature> temperatureList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT temp_value, time, sensor_id, measuring_frequency FROM temperature WHERE '" + startDate + "' <  time and time < '" + endDate + "'");

            while (myRs.next()) {

                Temperature tempTemperature = new Temperature(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                temperatureList.add(tempTemperature);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return temperatureList;
    }

    public List<Wind> searchWind(Date startDate, Date endDate) throws SQLException {

        List<Wind> windList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT speed, direction, time, sensor_id, measuring_frequency FROM wind WHERE '" + startDate + "' <  time and time < '" + endDate + "'");

            while (myRs.next()) {
                Wind tempWind = new Wind(myRs.getInt(1), myRs.getInt(2), myRs.getTimestamp(3), myRs.getString(4), myRs.getInt(5));
                windList.add(tempWind);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return windList;
    }

    public List<Rainfall> searchRainfall(Date startDate, Date endDate) throws SQLException {

        List<Rainfall> rainfallList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT height, time, sensor_id, measuring_frequency FROM rainfall WHERE '" + startDate + "' <  time and time < '" + endDate + "'");

            while (myRs.next()) {

                Rainfall tempRainfall = new Rainfall(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                rainfallList.add(tempRainfall);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return rainfallList;
    }

    public List<QualityOfAir> searchQualityOfAir(Date startDate, Date endDate) throws SQLException {

        List<QualityOfAir> qualityOfAirList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT co2, o2, n2, humadity, time, sensor_id, measuring_frequency FROM quality_of_air WHERE '" + startDate + "' <  time and time < '" + endDate + "'");

            while (myRs.next()) {
                QualityOfAir tempQualityOfAir = new QualityOfAir(myRs.getInt(1), myRs.getInt(2), myRs.getInt(3), myRs.getInt(4), myRs.getTimestamp(5), myRs.getString(6), myRs.getInt(7));
                qualityOfAirList.add(tempQualityOfAir);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return qualityOfAirList;
    }

    public List<Temperature> searchTemperature(Date date) throws SQLException {
        Timestamp start = new Timestamp(date.getTime());
        Timestamp end = new Timestamp(date.getTime() + (1 * 24 * 60 * 60 * 1000));
        return searchTemperature(start, end);
    }

    public List<Wind> searchWind(Date date) throws SQLException {
        Timestamp start = new Timestamp(date.getTime());
        Timestamp end = new Timestamp(date.getTime() + (1 * 24 * 60 * 60 * 1000));
        return searchWind(start, end);
    }

    public List<Rainfall> searchRainfall(Date date) throws SQLException {
        Timestamp start = new Timestamp(date.getTime());
        Timestamp end = new Timestamp(date.getTime() + (1 * 24 * 60 * 60 * 1000));
        return searchRainfall(start, end);
    }

    public List<QualityOfAir> searchQualityOfAir(Date date) throws SQLException {
        Timestamp start = new Timestamp(date.getTime());
        Timestamp end = new Timestamp(date.getTime() + (1 * 24 * 60 * 60 * 1000));
        return searchQualityOfAir(start, end);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Temperature> searchTemperature(float longitude, float latitude) throws SQLException {

        List<Temperature> temperatureList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT temp_value, time, sensor_id, measuring_frequency FROM temperature NATURAL JOIN location WHERE longitude = '"+longitude+"' and latitude = '"+latitude+"'");

            while (myRs.next()) {

                Temperature tempTemperature = new Temperature(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                temperatureList.add(tempTemperature);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return temperatureList;
    }

    public List<Wind> searchWind(float longitude, float latitude) throws SQLException {

        List<Wind> windList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT speed, direction, time, sensor_id, measuring_frequency FROM wind NATURAL JOIN location WHERE longitude = '"+longitude+"' and latitude = '"+latitude+"'");

            while (myRs.next()) {
                Wind tempWind = new Wind(myRs.getInt(1), myRs.getInt(2), myRs.getTimestamp(3), myRs.getString(4), myRs.getInt(5));
                windList.add(tempWind);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return windList;
    }

    public List<Rainfall> searchRainfall(float longitude, float latitude) throws SQLException {

        List<Rainfall> rainfallList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT height, time, sensor_id, measuring_frequency FROM rainfall NATURAL JOIN location WHERE longitude = '"+longitude+"' and latitude = '"+latitude+"'");

            while (myRs.next()) {

                Rainfall tempRainfall = new Rainfall(myRs.getInt(1), myRs.getTimestamp(2), myRs.getString(3), myRs.getInt(4));
                rainfallList.add(tempRainfall);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return rainfallList;
    }

    public List<QualityOfAir> searchQualityOfAir(float longitude, float latitude) throws SQLException {

        List<QualityOfAir> qualityOfAirList = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT co2, o2, n2, humadity, time, sensor_id, measuring_frequency FROM quality_of_air NATURAL JOIN location WHERE longitude = '"+longitude+"' and latitude = '"+latitude+"'");

            while (myRs.next()) {
                QualityOfAir tempQualityOfAir = new QualityOfAir(myRs.getInt(1), myRs.getInt(2), myRs.getInt(3), myRs.getInt(4), myRs.getTimestamp(5), myRs.getString(6), myRs.getInt(7));
                qualityOfAirList.add(tempQualityOfAir);
            }
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
        return qualityOfAirList;
    }
}

package testing;

import common.DbConnector;
import dao.PropertyRecordDAO;
import dao.SensorDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import vo.Temperature;


public class Just_to_test {

    public static void main(String args[]) throws IOException, SQLException, ParseException {
        DbConnector d = new DbConnector();
        Connection c = d.getMyConn();
        PropertyRecordDAO p = new PropertyRecordDAO(c);

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //Date td = sdf.parse("2015/12/12");
        //Timestamp date = new Timestamp(td.getTime());
        //Timestamp startDate = Timestamp.valueOf("2015-12-11 10:57:09");
        //p.addTemperature(new Temperature());

        //Timestamp endDate = Timestamp.valueOf("2015-12-15 10:57:09");
        //p.addTemperature(t);
        //p.searchTemperature(45, 54);
        //p.getAllTemperature();
        //System.out.println(p.searchTemperature(45, 54).size());
        
        
        SensorDAO sd = new SensorDAO(c);
        
        String [] serial = {"abcd", "bcde", "cdef", "defg", "efgh", "fghi", "ghij", "hijk", "ijkl", "jklm", "klmn", "lmno", "mnop", "nopq", "opqr", "pqrs", "qrst", "rstu", "stuv", "tuvw"};
        for (int i = 0; i<5; i++){
            
            Timestamp date = new Timestamp(new java.util.Date().getTime());

            //sd.insertSensor(new Sensor(Integer.toString(i), serial[i], date, "quality_of_air"));
            //p.addTemperature(new Temperature(i, date, null, i));
        }
        
        
        
    }
}

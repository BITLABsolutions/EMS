package common;

import dao.PropertyRecordDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vo.Temperature;


public class Just_to_test {

    public static void main(String args[]) throws IOException, SQLException, ParseException {
        DbConnector d = new DbConnector();
        Connection c = d.getMyConn();
        PropertyRecordDAO p = new PropertyRecordDAO(c);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date td = sdf.parse("2015/12/12");
        //Timestamp date = new Timestamp(td.getTime());
        //Timestamp startDate = Timestamp.valueOf("2015-12-11 10:57:09");
        Timestamp date = new Timestamp(new java.util.Date().getTime());
        Temperature t = new Temperature(11, date, "ab", 11);

        //Timestamp endDate = Timestamp.valueOf("2015-12-15 10:57:09");
        //p.addTemperature(t);
        //p.searchTemperature(45, 54);
        //p.getAllTemperature();
        System.out.println(p.searchTemperature(45, 54).size());
    }
}

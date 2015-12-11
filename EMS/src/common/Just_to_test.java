package common;

import dao.PropertyRecordDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Just_to_test {
    public static void main(String args[]) throws IOException, SQLException{
        DbConnector d = new DbConnector();
        Connection c = d.getMyConn();
        PropertyRecordDAO p = new PropertyRecordDAO(c);
        p.addTemperature();
    }
}

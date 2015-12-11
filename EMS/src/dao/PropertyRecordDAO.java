package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PropertyRecordDAO {
    private final Connection myConn;

    public PropertyRecordDAO(Connection myConn) throws IOException, SQLException {
        this.myConn = myConn;
    }

    public Connection getMyConn() {
        return myConn;
    }
    
    public void addTemperature() throws SQLException{
        String query = "insert into temperature (temperature_id, temp_value)values (?,?)";
        PreparedStatement preStmt = myConn.prepareStatement(query);
        preStmt.setInt(1, 1);
        preStmt.setString(2, "25");
        
        preStmt.execute();
        myConn.close();
    }

    
    
}
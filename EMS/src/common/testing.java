/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import dao.MaintainsDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vo.Maintain;

/**
 *
 * @author prabath s
 */
public class testing {
    public static void main(String[] args) throws IOException, SQLException{
        DbConnector dbConnector=DbConnector.getInstance();
        MaintainsDAO maintainsDAO=new MaintainsDAO(dbConnector.getMyConn());
        Date date=java.util.Calendar.getInstance().getTime();
        
        Maintain maintain=new Maintain(2,"2",date,new java.sql.Time(date.getTime()),"");
        maintainsDAO.insertMaintanaceRecord(maintain);
    }
    
}

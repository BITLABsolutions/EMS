package vo;




/**
 * Destination generated by chamin
 */
public class Destination  implements java.io.Serializable {


     private Integer notificationId;
     private String destiny;

    public Destination() {
    }

    public Destination(int notificationId, String destiny) {
       this.notificationId = notificationId;
       this.destiny = destiny;
    }
   
    public int getNotificationId() {
        return this.notificationId;
    }
    
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }
    public String getDestiny() {
        return this.destiny;
    }
    
    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }


   

}


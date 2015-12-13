/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author Malith
 */
public class Employee {
    private int emp_id;
    private String first_name;
    private String last_name;
    private String nic;
    private String username;
    private String password;
    private int access_level;//Admin - 1, Technician - 2, normal - 3, else - 0
    private String street;
    private String town;
    private String phone;
    private String sex;

    public Employee(int emp_id, String first_name, String last_name, String nic, String username, String password, int access_level, String street, String town, String phone, String sex) {
        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nic = nic;
        this.username = username;
        this.password = password;
        this.access_level = access_level;
        this.street = street;
        this.town = town;
        this.phone = phone;
        this.sex = sex;
    }

    
    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccess_level() {
        return access_level;
    }

    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSummary(){
        String temp = "<html><body>";
        
        String name = "";
        if(first_name != null & !"".equals(first_name)){name+= "<h1><font color=\"#00BFFF\">"+first_name+"</font>&#160";}    
         if(last_name != null & !"".equals(last_name)){name+= "<font color=\"#00BFFF\">"+last_name+"</font></h1>";} 
        
        temp="<TABLE ALIGN=CENTER WIDTH=\"90%\" BORDER=0 CELLSPACING=1 CELLPADDING=1><caption>"+name+"</caption>";   
                       
        temp+=                                                           "<tr><td><b>Employee ID</b></td><td>"+emp_id+"</td></tr>";
        if(nic != null & !"".equals(nic)){temp+=                         "<tr><td><b>NIC</b></td><td>"+nic+"</td></tr>";}
        if(username != null & !"".equals(username)){temp+=               "<tr><td><b>User Name</b></td><td>"+username+"</td></tr>";}
        if(access_level != 0 & !"".equals(access_level)){temp+=       "<tr><td><b>Access Level</b></td><td>"+access_level+"</td></tr>";}
        if(street != null & !"".equals(street)){temp+=                   "<tr><td><b>Street</b></td><td>"+street +"</td></tr>";}
        if(town != null & !"".equals(town)){temp+=                       "<tr><td><b>Town</b></td><td>"+town+"</td></tr>";}
        if(phone != null & !"".equals(phone)){temp+=                     "<tr><td><b>Phone</b></td><td>"+phone+"</td></tr>";}
        if(sex != null & !"".equals(sex)){temp+=                         "<tr><td><b>Sex</b></td><td>"+sex+"</td></tr>";}
        temp+= "</table></body></html>";
        return temp;
    }
}

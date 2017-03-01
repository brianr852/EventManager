/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmanager;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brian
 */
public class MySqlConnect 
{
    Connection conn = null;
    
    public static Connection ConnectDB()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///events","root","password");
            return conn;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
         public boolean add(String email, String event, String desc, String month, String day, String year, String loc)
        {
            String Query = "INSERT INTO event (EMAIL, EVENT_NAME, EVENT_DESCR, EVENT_DATE, EVENT_LOC) "
                    + "VALUES ('"+email+"', '"+event+"' , '"+desc+"' , "
                    + "'"+month + day + year+"' , "
                    + "'"+loc+"')";
             try{
           Connection con = ConnectDB();
           Statement stm = con.createStatement();
           stm.execute(Query);
           return true;      
        }
        
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
             return false;
        }
         
         
        public boolean addInvite(String event, String email)
        {
            String Query = "INSERT INTO invite (EVENT_NAME, INVITE_EMAIL) "
                    + "VALUES ('"+event+"', '"+email+"')";
             try{
           Connection con = ConnectDB();
           Statement stm = con.createStatement();
           stm.execute(Query);
           return true;      
        }
        
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
             return false;
        }
        
        
         public boolean update(String id, String email, String event, String desc, String month, String day, String year, String loc)
        {
            String Query = "UPDATE event SET EMAIL = '"+email+"', EVENT_NAME = '"+event+"' ,EVENT_DESCR = '"+desc+"' , EVENT_DATE = '"+month + day + year+"' "
                    + ", EVENT_LOC ='"+loc+"' WHERE ID = "+id+"";
             try{
           Connection con = ConnectDB();
           Statement stm = con.createStatement();
           stm.execute(Query);
           return true;      
        }
        
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
             return false;
        }
        
           
         public boolean delete(String id)
        {
           String Query = "DELETE FROM event WHERE id =" + id +"";
                  // + "EVENT_NAME = '"+event+"' ,EVENT_DESCR = '"+desc+"' , EVENT_DATE = '"+month + day + year+"' "
                  //  + ", EVENT_LOC ='"+loc+"', EVENT_CREATOR ='"+name+"' WHERE EVENT_ID = '"+id+"'";
           try{
           Connection con = ConnectDB();
           Statement stm = con.createStatement();
           stm.executeUpdate(Query);
           return true;      
        }
        
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
             return false;
        }
    
    
       public DefaultTableModel getData()
        {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("EMAIL");
        model.addColumn("EVENT_NAME");
        model.addColumn("EVENT_DESCR");
        model.addColumn("EVENT_DATE");
        model.addColumn("EVENT_LOC");
        String Que = "Select * from event";
        try{
            Connection con = ConnectDB();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(Que);
          
            while (rs.next())
            {
                String id = rs.getString(1);
                String email = rs.getString(2);
                String event = rs.getString(3);
                String descr = rs.getString(4);
                String date = rs.getString(5);
                String loc = rs.getString(6);
                model.addRow(new String [] {id, email, event, descr, date, loc});
     
            }
            return model;
          
        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
        }
    
}

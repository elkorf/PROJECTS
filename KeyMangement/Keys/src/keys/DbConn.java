/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keys;

/**
 *
 * @author elkorf
 */
import java.sql.Connection;
import java.sql.DriverManager;



public class DbConn {
    static Connection connection ;

    public static Connection connect() throws Exception {
       
        try {
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/KeyDatabase;create=true","root", "123");            
            return connection;
        } catch (Exception e) {
            System.out.println("Exception in datbase connection : " + e.toString());
            
        }
        return null;
    }
}

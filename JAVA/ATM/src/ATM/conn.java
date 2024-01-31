//package ATM;
//import javax.swing.plaf.nimbus.State;
//import java.sql.*;
//public class conn {
//        Statement s;
//        public conn(){
//            try{
//                Connection con;
//                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","Shreya@2001");
//                System.out.println("Connection Established");
//                s=con.createStatement();
//            }catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
package ATM;
import java.sql.*;

public class conn {
    Statement s;

    public conn() {
        try {
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "Shreya@2001");
            System.out.println("Connection Established");
            s = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

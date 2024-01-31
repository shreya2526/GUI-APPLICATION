package ATM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class deductAmount {
    public deductAmount(String pin, String amt) {
        try {
            conn conn = new conn();
            String query1 = "UPDATE bank SET balance = balance - " + amt + " WHERE account_number = (SELECT account_number FROM atm WHERE pin = " + pin + ")";
            int rowsAffected = conn.s.executeUpdate(query1);
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) updated successfully.");
            } else {
                System.out.println("No rows updated.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

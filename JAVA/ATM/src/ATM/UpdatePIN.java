package ATM;

import javax.swing.*;

public class UpdatePIN {
    UpdatePIN(String PIN){
        try{
            conn con = new conn();
            String oldPin=ChangePIN.pin;
            String query = "UPDATE ATM SET PIN = '" + PIN + "' WHERE PIN = '" + oldPin + "';";
            int rowsAffected = con.s.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) updated successfully.");
            } else {
                System.out.println("No rows updated.");
            }
            JOptionPane.showMessageDialog(null, "PIN Updated Successfully!");
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}

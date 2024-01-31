package ATM;

import javax.swing.*;

public class AddATM {
    AddATM(String acc, String pin){
        try{
            conn con = new conn();
            String query = "INSERT INTO ATM VALUES('" + acc + "', '" + pin + "');";
            int rowsAffected = con.s.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) inserted successfully.");
            } else {
                System.out.println("No rows updated.");
            }
            JOptionPane.showMessageDialog(null, "ATM activated on "+acc+" Account number Successfully!");
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}

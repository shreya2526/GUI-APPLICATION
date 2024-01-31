//package ATM;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//
//public class Credit extends JFrame implements ActionListener {
//
//    JTextField tfaccNo, tfAmt;
//    //to hide ATM pin
//    JPasswordField pinField;
//    JButton Submit, Back;
//
//    static String pin, amount,acc_no;
//
//    Credit() {
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//
//        JLabel name = new JLabel("Credit Money");
//        name.setBounds(250, 20, 400, 40);
//        name.setFont(new Font("serif", Font.PLAIN, 30));
//        name.setForeground(Color.BLACK);
//        add(name);
//
//        JLabel warning1 = new JLabel("(Credited amount Should not cross INR 20,000)");
//        warning1.setBounds(150, 70, 1200, 20);
//        warning1.setFont(new Font("serif", Font.PLAIN, 20));
//        warning1.setForeground(Color.blue);
//        add(warning1);
//
//        JLabel warning2 = new JLabel("(Warning: Avoid crediting money to the account linked\n with the ATM PIN you are using!)");
//        warning2.setBounds(0, 90, 1200, 30);
//        warning2.setFont(new Font("serif", Font.PLAIN, 20));
//        warning2.setForeground(Color.RED);
//        add(warning2);
//
//        JLabel accNo = new JLabel("Enter Account Number");
//        accNo.setBounds(120, 140, 200, 50);
//        accNo.setFont(new Font("serif", Font.PLAIN, 20));
//        add(accNo);
//        tfaccNo = new JTextField();
//        tfaccNo.setBounds(440, 150, 200, 30);
//        add(tfaccNo);
//
//        JLabel amt = new JLabel("Enter Amount");
//        amt.setBounds(120, 190, 200, 50);
//        amt.setFont(new Font("serif", Font.PLAIN, 20));
//        add(amt);
//        tfAmt = new JTextField();
//        tfAmt.setBounds(440, 200, 200, 30);
//        add(tfAmt);
//
//        JLabel pin = new JLabel("Enter PIN");
//        pin.setBounds(120, 240, 200, 50);
//        pin.setFont(new Font("serif", Font.PLAIN, 20));
//        add(pin);
//        pinField = new JPasswordField(4);
//        pinField.setBounds(440, 250, 200, 30);
//        add(pinField);
//
//        Submit = new JButton("Submit");
//        Submit.setBounds(380, 340, 100, 40);
//        Submit.setBackground(Color.GREEN);
//        Submit.setForeground(Color.BLACK);
//        Submit.addActionListener(this);
//        add(Submit);
//
//        Back = new JButton("Back");
//        Back.setBounds(200, 340, 100, 40);
//        Back.setBackground(Color.RED);
//        Back.setForeground(Color.WHITE);
//        Back.addActionListener(this);
//        add(Back);
//
//        setSize(700, 450);
//        setLocation(330, 50);
//        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        while(true) {
//            warning1.setVisible(false);
//            try {
//                Thread.sleep(500);
//            } catch (Exception e){
//
//            }
//
//            warning1.setVisible(true);
//            try {
//                Thread.sleep(1500);
//            } catch (Exception e){
//
//            }
//        }
//    }
//
//
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource()==Back) {
//            setVisible(false);
//            new Main();
//        } else if (ae.getSource()==Submit) {
//            try{
//                conn conn=new conn();
//                char []password=pinField.getPassword();
//                pin=new String(password);
//                String query="SELECT EXISTS ( SELECT 1 FROM ATM WHERE pin = "+pin+")AS pin_exists;";
//                boolean rs =conn.s.execute(query);
//                amount=tfAmt.getText();
//                acc_no=tfaccNo.getText();
//                if(rs){
//                    JOptionPane.showMessageDialog(null, amount+" credited to "+acc_no+" successfully!");
//                }
//                new deductAmount(pin,amount);
//                setVisible(false);
//                new creditPage();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        new Credit();
//    }
//}
package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Credit extends JFrame implements ActionListener {

    JTextField tfaccNo, tfAmt;
    JPasswordField pinField;
    JButton Submit, Back;

    static String pin, amount, acc_no;

    Credit() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Credit Money");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.BLACK);
        add(name);

        JLabel warning1 = new JLabel("(Credited amount Should not cross INR 20,000)");
        warning1.setBounds(150, 70, 600, 20);
        warning1.setFont(new Font("serif", Font.PLAIN, 20));
        warning1.setForeground(Color.blue);
        add(warning1);

        JLabel accNo = new JLabel("Enter Account Number");
        accNo.setBounds(120, 140, 200, 50);
        accNo.setFont(new Font("serif", Font.PLAIN, 20));
        add(accNo);
        tfaccNo = new JTextField();
        tfaccNo.setBounds(340, 150, 200, 30);
        add(tfaccNo);

        JLabel amt = new JLabel("Enter Amount");
        amt.setBounds(120, 190, 200, 50);
        amt.setFont(new Font("serif", Font.PLAIN, 20));
        add(amt);
        tfAmt = new JTextField();
        tfAmt.setBounds(340, 200, 200, 30);
        add(tfAmt);

        JLabel pinLabel = new JLabel("Enter PIN");
        pinLabel.setBounds(120, 240, 200, 50);
        pinLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(pinLabel);
        pinField = new JPasswordField(4);
        pinField.setBounds(340, 250, 200, 30);
        add(pinField);

        Submit = new JButton("Submit");
        Submit.setBounds(380, 340, 100, 40);
        Submit.setBackground(Color.GREEN);
        Submit.setForeground(Color.BLACK);
        Submit.addActionListener(this);
        add(Submit);

        Back = new JButton("Back");
        Back.setBounds(200, 340, 100, 40);
        Back.setBackground(Color.RED);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocation(330, 150);
        setVisible(true);

        JOptionPane.showMessageDialog(null, "Warning: Avoid crediting money to the account linked with the ATM PIN you are using!");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==Back) {
            setVisible(false);
            new Main();
        } else if (ae.getSource()==Submit) {
            try{
                conn conn=new conn();
                char []password=pinField.getPassword();
                pin=new String(password);
                String query1="SELECT EXISTS ( SELECT 1 FROM ATM WHERE pin = "+pin+")AS pin_exists;";
                boolean rs =conn.s.execute(query1);
                amount=tfAmt.getText();
                if(rs){
                    JOptionPane.showMessageDialog(null, amount+" credited to "+acc_no+" successfully!");
                }
                new deductAmount(pin,amount);
                dataStoreInTable();
                setVisible(false);
                new creditPage();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void dataStoreInTable(){
        try{
            conn conn= new conn();
            String q1="SELECT b.account_number FROM bank b JOIN atm a ON b.account_number = a.acc_no WHERE a.pin = "+pin+";";
            ResultSet rs=conn.s.executeQuery(q1);
            String account=null;
            if(rs.next()){
                account=rs.getString("account_number");
            }
            //to fetch the current date and time to insert in transaction table
            LocalDateTime currentDateTime = LocalDateTime.now();
            String currentDate = currentDateTime.toLocalDate().format(DateTimeFormatter.ISO_DATE);
            String currentTime = currentDateTime.toLocalTime().format(DateTimeFormatter.ISO_TIME);
            String q2="INSERT INTO TRANSACTION VALUES('"+account+"','debit','"+amount+"','"+currentDate+"','"+currentTime+"');";
            conn.s.executeUpdate(q2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Credit();
    }
}

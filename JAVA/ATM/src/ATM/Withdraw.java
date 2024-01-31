package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Withdraw extends JFrame implements ActionListener {
    JTextField tfAmt;
    //to hide ATM pin
    JPasswordField pinField;
    JButton Submit, Back;

    static String pin, amount,acc_no;

    Withdraw(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Withdraw Money");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.BLACK);
        add(name);

        JLabel warning1 = new JLabel("(Withdrawal amount Should not cross INR 20,000)");
        warning1.setBounds(150, 70, 1200, 20);
        warning1.setFont(new Font("serif", Font.PLAIN, 20));
        warning1.setForeground(Color.blue);
        add(warning1);

        JLabel pin = new JLabel("Enter PIN");
        pin.setBounds(120, 140, 200, 50);
        pin.setFont(new Font("serif", Font.PLAIN, 20));
        add(pin);
        pinField = new JPasswordField(4);
        pinField.setBounds(440, 150, 200, 30);
        add(pinField);

        JLabel amt = new JLabel("Enter Amount");
        amt.setBounds(120, 190, 200, 50);
        amt.setFont(new Font("serif", Font.PLAIN, 20));
        add(amt);
        tfAmt = new JTextField();
        tfAmt.setBounds(440, 200, 200, 30);
        add(tfAmt);

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


        setSize(700, 450);
        setLocation(330, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                String q="SELECT ACC_NO FROM ATM WHERE PIN="+pin+";";
                ResultSet r=conn.s.executeQuery(q);
                if(r.next()){
                    acc_no=r.getString("ACC_NO");
                }
                if(rs){
                    JOptionPane.showMessageDialog(null, amount+" debited successfully!");
                }
                new deductAmount(pin,amount);
                dataStoreInTable();
                setVisible(false);
                new WithdrawalPage();
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
        new Withdraw();
    }
}
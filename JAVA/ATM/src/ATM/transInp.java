package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class transInp extends JFrame implements ActionListener {
    static String acc_no,pin;
    JTextField tfaccNo;
    JPasswordField pinField;
    JButton Submit, Back;
    transInp(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Check Transaction History");
        name.setBounds(180, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.RED);
        add(name);

        JLabel accNo = new JLabel("Account Number");
        accNo.setBounds(120, 140, 200, 50);
        accNo.setFont(new Font("serif", Font.PLAIN, 20));
        add(accNo);
        tfaccNo = new JTextField();
        tfaccNo.setBounds(340, 150, 200, 30);
        add(tfaccNo);

        JLabel pinLabel = new JLabel("ATM PIN");
        pinLabel.setBounds(120, 190, 200, 50);
        pinLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(pinLabel);
        pinField = new JPasswordField(4);
        pinField.setBounds(340, 200, 200, 30);
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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==Back) {
            setVisible(false);
            new Main();
        } else if (ae.getSource()==Submit) {
            try{
                conn conn=new conn();
                char []password=pinField.getPassword();
                pin=new String(password);
                acc_no=tfaccNo.getText();
                String query1="SELECT EXISTS ( SELECT 1 FROM ATM WHERE pin = "+pin+")AS pin_exists;";
                boolean rs =conn.s.execute(query1);
                String q2 = "SELECT account_number FROM bank b JOIN atm a ON b.account_number = a.acc_no WHERE a.pin = " + pin + ";";
                ResultSet r = conn.s.executeQuery(q2);

                if (r.next()) {
                    // Assuming acc_no is a String variable declared earlier
                    acc_no = r.getString("account_number");
                } else {
                    // Handle the case when no rows are returned
                    System.out.println("No account found for the given PIN.");
                }

                setVisible(false);
                new transaction();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new transInp();
    }
}

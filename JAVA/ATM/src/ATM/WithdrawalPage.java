package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class WithdrawalPage extends JFrame{

    WithdrawalPage(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Current Balance");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.RED);
        add(name);

        JLabel Customer_name = new JLabel("Customer Name");
        Customer_name.setBounds(50, 80, 100, 30);
        add(Customer_name);

        JLabel Customer_name_val=new JLabel();
        Customer_name_val.setBounds(200,80,100,30);
        add(Customer_name_val);

        JLabel Account_number = new JLabel("Account Number");
        Account_number.setBounds(50, 130, 100, 30);
        add(Account_number);

        JLabel Account_number_val=new JLabel();
        Account_number_val.setBounds(200,130,100,30);
        add(Account_number_val);

        JLabel current_bal=new JLabel("Current Balance");
        current_bal.setBounds(50,180,100,30);
        add(current_bal);

        JLabel current_bal_val=new JLabel();
        current_bal_val.setBounds(200,180,100,30);
        add(current_bal_val);

        try{
            conn c = new conn();
            String atm_pin=Withdraw.pin;
            String query = "select account_number, name, balance from bank b join atm a on b.account_number=a.acc_no where a.pin="+atm_pin+"";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                Account_number_val.setText(rs.getString("Account_number"));
                Customer_name_val.setText(rs.getString("name"));
                current_bal_val.setText(rs.getString("balance"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/withdraw.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 80, 200, 200);
        add(image);

        setSize(700, 350);
        setLocation(330, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a Timer that will switch to another page after 5 seconds
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame
                dispose();
                new Main();

                // Stop the timer after executing the action
                ((Timer) e.getSource()).stop();
            }
        });

        // Start the timer
        timer.start();

    }
    public static void main(String[] args) {
        new WithdrawalPage();
    }
}

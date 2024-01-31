package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePIN extends JFrame implements ActionListener {
    JTextField tfAcc_no;
    //to hide ATM pin
    JPasswordField pinField;
    JButton Submit, Back;

    static String pin, acc_no;

    ChangePIN(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Update ATM PIN");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.BLACK);
        add(name);

        JLabel account = new JLabel("Account Number");
        account.setBounds(120, 140, 200, 50);
        account.setFont(new Font("serif", Font.PLAIN, 20));
        add(account);
        tfAcc_no = new JTextField();
        tfAcc_no.setBounds(440, 150, 200, 30);
        add(tfAcc_no);

        JLabel pin = new JLabel("Current PIN");
        pin.setBounds(120, 190, 200, 50);
        pin.setFont(new Font("serif", Font.PLAIN, 20));
        add(pin);
        pinField = new JPasswordField(4);
        pinField.setBounds(440, 200, 200, 30);
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
                String query="SELECT EXISTS ( SELECT 1 FROM ATM WHERE pin = "+pin+")AS pin_exists;";
                boolean rs =conn.s.execute(query);
                acc_no=tfAcc_no.getText();
                if(rs){
                    setVisible(false);
                    new PINUpdatePage();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new ChangePIN();
    }
}
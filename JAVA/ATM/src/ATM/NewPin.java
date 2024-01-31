package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPin extends JFrame implements ActionListener {
    static String acc,pin;
    JButton Back, Submit;
    JPasswordField tfpin, tfnpin;
    NewPin(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Set ATM PIN");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.BLACK);
        add(name);

        JLabel lbpin=new JLabel("PIN Number");
        lbpin.setBounds(120, 150, 200, 40);
        lbpin.setFont(new Font("serif", Font.PLAIN, 20));
        lbpin.setForeground(Color.BLACK);
        add(lbpin);
        tfpin = new JPasswordField(4);
        tfpin.setBounds(340, 150, 200, 30);
        add(tfpin);

        JLabel lbnpin = new JLabel("Confirm PIN Number");
        lbnpin.setBounds(120, 190, 200, 50);
        lbnpin.setFont(new Font("serif", Font.PLAIN, 20));
        add(lbnpin);
        tfnpin = new JPasswordField(4);
        tfnpin.setBounds(340, 200, 200, 30);
        add(tfnpin);

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

        JOptionPane.showMessageDialog(null, "SET 4-Digit ATM PIN");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==Back) {
            setVisible(false);
            new NewATM();
        } else if (ae.getSource()==Submit) {
                acc=NewATM.acc_no;
                char []password=tfnpin.getPassword();
                pin=new String(password);
                new AddATM(acc,pin);
                setVisible(false);
                new Main();
        }
    }

    public static void main(String[] args) {
        new NewPin();
    }

}

package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PINUpdatePage extends JFrame implements ActionListener {
    JPasswordField NewPin, ReNewPin;
    JButton submit;
    static String NewATMPIN;
    PINUpdatePage(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Update ATM PIN");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.BLACK);
        add(name);

        JLabel newPin = new JLabel("New PIN");
        newPin.setBounds(50, 130, 100, 30);
        newPin.setFont(new Font("serif", Font.PLAIN, 20));
        newPin.setForeground(Color.BLACK);
        add(newPin);
        NewPin = new JPasswordField(4);
        NewPin.setBounds(230, 130, 150, 30);
        add(NewPin);

        JLabel reNew = new JLabel("Confirm New PIN");
        reNew.setBounds(50, 200, 150, 30);
        reNew.setFont(new Font("serif", Font.PLAIN, 20));
        add(reNew);
        ReNewPin = new JPasswordField(4);
        ReNewPin.setBounds(230, 200, 150, 30);
        add(ReNewPin);

        submit=new JButton("Submit");
        submit.setBounds(250, 300, 200, 40);
        submit.setBackground(Color.GREEN);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/change PIN.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 80, 200, 200);
        add(image);

        setSize(700, 400);
        setLocation(330, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            char []pin=ReNewPin.getPassword();
            NewATMPIN=new String(pin);
            new UpdatePIN(NewATMPIN);
            setVisible(false);
            new Main();
        }
    }

    public static void main(String[] args) {
        new PINUpdatePage();
    }
}

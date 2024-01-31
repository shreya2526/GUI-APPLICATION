package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    JButton credit, withdraw, newATM, changePIN, transaction;

    Main() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("WELCOME TO ATM");
        heading.setBounds(315, 30, 1200, 60);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.RED);
        add(heading);

        JLabel menu = new JLabel("MENU");
        menu.setBounds(710, 130, 200, 40);
        menu.setFont(new Font("serif", Font.PLAIN, 40));
        menu.setForeground(Color.BLACK);
        add(menu);

        credit=new JButton("Fund Transfer");
        credit.setBounds(650,200,250,40);
        credit.setBackground(Color.CYAN);
        credit.setForeground(Color.BLACK);
        credit.addActionListener(this);
        add(credit);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(650,260,250,40);
        withdraw.setBackground(Color.CYAN);
        withdraw.setForeground(Color.BLACK);
        withdraw.addActionListener(this);
        add(withdraw);

        newATM = new JButton("New ATM");
        newATM.setBounds(650,320,250,40);
        newATM.setBackground(Color.CYAN);
        newATM.setForeground(Color.BLACK);
        newATM.addActionListener(this);
        add(newATM);

        changePIN = new JButton("Change PIN");
        changePIN.setBounds(650,380,250,40);
        changePIN.setBackground(Color.CYAN);
        changePIN.setForeground(Color.BLACK);
        changePIN.addActionListener(this);
        add(changePIN);

        transaction = new JButton("Transaction History");
        transaction.setBounds(650,440,250,40);
        transaction.setBackground(Color.CYAN);
        transaction.setForeground(Color.BLACK);
        transaction.addActionListener(this);
        add(transaction);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/welcome.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 130, 300, 400);
        add(image);

        setTitle("ATM MACHINE");
        setSize(1170,650);
        setLocation(130,50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == credit) {
            setVisible(false);
            new Credit();
        } else if (ae.getSource() == withdraw) {
            setVisible(false);
            new Withdraw();
        } else if (ae.getSource() == newATM) {
            setVisible(false);
            new NewATM();
        } else if (ae.getSource() == changePIN) {
            setVisible(false);
            new ChangePIN();
        } else if (ae.getSource() == transaction) {
            setVisible(false);
            new transInp();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

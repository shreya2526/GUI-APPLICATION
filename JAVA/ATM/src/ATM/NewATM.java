package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewATM extends JFrame implements ActionListener {
    static String acc_no;
    JButton Back, Submit;
    JTextField tfacc, tfph;
    NewATM(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("New ATM Apply");
        name.setBounds(250, 20, 400, 40);
        name.setFont(new Font("serif", Font.PLAIN, 30));
        name.setForeground(Color.BLACK);
        add(name);

        JLabel lbacc=new JLabel("Account Number");
        lbacc.setBounds(120, 150, 200, 40);
        lbacc.setFont(new Font("serif", Font.PLAIN, 20));
        lbacc.setForeground(Color.BLACK);
        add(lbacc);
        tfacc = new JTextField();
        tfacc.setBounds(340, 150, 200, 30);
        add(tfacc);

        JLabel ph = new JLabel("Phone number");
        ph.setBounds(120, 190, 200, 50);
        ph.setFont(new Font("serif", Font.PLAIN, 20));
        add(ph);
        tfph = new JTextField();
        tfph.setBounds(340, 200, 200, 30);
        add(tfph);

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

        JOptionPane.showMessageDialog(null, "Enter valid Account and Phone number");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==Back) {
            setVisible(false);
            new Main();
        } else if (ae.getSource()==Submit) {
            try{
                conn conn=new conn();
                acc_no=tfacc.getText();
                String query="SELECT EXISTS ( SELECT 1 FROM BANK WHERE ACCOUNT_NUMBER = "+acc_no+")AS ACCOUNT_NUMBER_EXISTS;";
                boolean rs =conn.s.execute(query);
                if(rs){
                    setVisible(false);
                    new NewPin();
                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new NewATM();
    }

}

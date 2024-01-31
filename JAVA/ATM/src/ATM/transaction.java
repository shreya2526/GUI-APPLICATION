package ATM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class transaction extends JFrame implements ActionListener{
    static JTable table;
    JButton print;

    transaction(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        setSize(1100, 700);
        setLocation(100, 30);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel name=new JLabel("Beneficiary Name: ");
        name.setBounds(80,20,200,40);
        name.setFont(new Font("serif", Font.PLAIN, 20));
        name.setForeground(Color.BLACK);
        add(name);
        JLabel name1=new JLabel();
        name1.setBounds(300,30,150,20);
        name1.setForeground(Color.BLACK);
        add(name1);

        JLabel account_number=new JLabel("Account Number: ");
        account_number.setBounds(80,60,200,20);
        account_number.setFont(new Font("serif", Font.PLAIN, 20));
        account_number.setForeground(Color.BLACK);
        add(account_number);
        JLabel account_num=new JLabel();
        account_num.setBounds(300,60,150,20);
        account_num.setForeground(Color.BLACK);
        add(account_num);

        JLabel balance=new JLabel("Current Balance: ");
        balance.setBounds(80,90,200,30);
        balance.setFont(new Font("serif", Font.PLAIN, 20));
        balance.setForeground(Color.BLACK);
        add(balance);
        JLabel balance1=new JLabel();
        balance1.setBounds(300,90,150,20);
        balance1.setForeground(Color.BLACK);
        add(balance1);

        // Initialize the table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(80, 130, 900, 400);
        add(scrollPane);

        print = new JButton("Print");
        print.setBounds(920, 20, 80, 20);
        print.addActionListener(this);
        add(print);

        try{
            conn c = new conn();
            String atm_pin=transInp.pin;
            String query = "select account_number, name, balance from bank b join atm a on b.account_number=a.acc_no where a.pin="+atm_pin+"";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                account_num.setText(rs.getString("account_number"));
                name1.setText(rs.getString("name"));
                balance1.setText(rs.getString("balance"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        TransTable();

        // Create a Timer that will switch to another page after 15 seconds
        Timer timer = new Timer(25000, new ActionListener() {
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

    // Fetch transaction activity
    public static void TransTable(){
        try {
            conn con = new conn();
            String acc = transInp.acc_no;
            String transactionQuery = "SELECT t_type, amount, t_time, t_date FROM transaction_view WHERE account_number =" + acc + ";";
            ResultSet rs = con.s.executeQuery(transactionQuery);

            // Populate a table model with transaction data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Transaction Type");
            tableModel.addColumn("Amount");
            tableModel.addColumn("Transaction Time");
            tableModel.addColumn("Transaction Date");

            while (rs.next()) {
                String transactionType = rs.getString("t_type");
                String amount = rs.getString("amount");
                String transactionTime = rs.getString("t_time");
                String transactionDate = rs.getString("t_date");

                // Add a row to the table model
                tableModel.addRow(new Object[]{transactionType, amount, transactionTime, transactionDate});
            }

            // Set the table model to the JTable
            table.setModel(tableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new transaction();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == print) {
            try {
                //to print or make pdf of the table
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

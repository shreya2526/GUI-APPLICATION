package brm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class BookFrame {
    Connection con;
    PreparedStatement ps;
    JFrame frame=new JFrame("BRM Project");
    //for multiple pages to view in one screen like browser
    JTabbedPane tabbedPane=new JTabbedPane();
    //JPanel is a sub-container of JFrame
    JPanel insertPanel, viewPanel;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4,t5;
    JButton saveButton, updateButton, deleteButton;
    JTable table;
    //for scrollbar
    JScrollPane scrollPane;
    //display table model on gui
    DefaultTableModel tm;
    //to show table model in this format
    String []colNames={"Book ID","Title","Price","Author","Publisher"};

    public BookFrame(){
        getConnectionFromMySQL();
        initComponents();
    }
    void getConnectionFromMySQL(){
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","Shreya@2001");
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void initComponents(){
        // Components for insert Form
        l1=new JLabel();
        l1.setText("Book ID:");
        l2=new JLabel();
        l2.setText("Title:");
        l3=new JLabel();
        l3.setText("Price:");
        l4=new JLabel();
        l4.setText("Author:");
        l5=new JLabel();
        l5.setText("Publisher:");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        t5=new JTextField();
        saveButton=new JButton("Save");
        l1.setBounds(100,100,100,20);
        l2.setBounds(100,150,100,20);
        l3.setBounds(100,200,100,20);
        l4.setBounds(100,250,100,20);
        l5.setBounds(100,300,100,20);
        t1.setBounds(250,100,100,20);
        t2.setBounds(250,150,100,20);
        t3.setBounds(250,200,100,20);
        t4.setBounds(250,250,100,20);
        t5.setBounds(250,300,100,20);
        saveButton.setBounds(100,350,100,30);
        saveButton.addActionListener(new InsertBookRecord());

        insertPanel=new JPanel();
        insertPanel.setLayout(null);
        insertPanel.add(l1);
        insertPanel.add(l2);
        insertPanel.add(l3);
        insertPanel.add(l4);
        insertPanel.add(l5);
        insertPanel.add(t1);
        insertPanel.add(t2);
        insertPanel.add(t3);
        insertPanel.add(t4);
        insertPanel.add(t5);
        insertPanel.add(saveButton);
        //fectching data from main table in the fetchBookRecord(), we took ArrayList to get the growable feature of array according to the number of data
        ArrayList<Book> bookList =fetchBookRecords();
        //setting fetched data on JTable
        setDataOnTable(bookList);
        //update data of table, this button will be on view panel where the book table will visible
        updateButton=new JButton("Update Book");
        updateButton.addActionListener(new UpdateBookRecord());
        //delete data of table, this button will be on view panel where the book table will visible
        deleteButton=new JButton("Delete Book");
        deleteButton.addActionListener(new DeleteBookRecord());
        viewPanel=new JPanel();
        viewPanel.add(updateButton);
        viewPanel.add(deleteButton);
        scrollPane=new JScrollPane(table);
        viewPanel.add(scrollPane);

        tabbedPane.add(insertPanel);
        tabbedPane.add(viewPanel);
        //to display current inserted data in the table
        tabbedPane.addChangeListener(new TabChangeHandler());

        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);

    }
    void setDataOnTable(ArrayList<Book> bookList){
        //Object is superclass of every object hence we made object of Object class
        Object [][]obj=new Object[bookList.size()][5];
        for(int i=0;i<bookList.size();i++){
            obj[i][0]=bookList.get(i).getBookId();
            obj[i][1]=bookList.get(i).getTitle();
            obj[i][2]=bookList.get(i).getPrice();
            obj[i][3]=bookList.get(i).getAuthor();
            obj[i][4]=bookList.get(i).getPublisher();
        }
        table = new JTable();
        //setting table structure
        tm=new DefaultTableModel();
        tm.setColumnCount(5);
        tm.setRowCount(bookList.size());
        tm.setColumnIdentifiers(colNames);
        //placing data in table cells
        for(int i=0;i<bookList.size();i++){
            tm.setValueAt(obj[i][0],i,0);
            tm.setValueAt(obj[i][1],i,1);
            tm.setValueAt(obj[i][2],i,2);
            tm.setValueAt(obj[i][3],i,3);
            tm.setValueAt(obj[i][4],i,4);
        }
        table.setModel(tm);//arranging whole model in table
    }
    //this method will do changes in the table model. It does almost same task as setDataOnTable() but won't make any table this time
    void updateTable(ArrayList<Book> bookList){
        Object [][]obj=new Object[bookList.size()][5];
        for(int i=0;i<bookList.size();i++){
            obj[i][0]=bookList.get(i).getBookId();
            obj[i][1]=bookList.get(i).getTitle();
            obj[i][2]=bookList.get(i).getPrice();
            obj[i][3]=bookList.get(i).getAuthor();
            obj[i][4]=bookList.get(i).getPublisher();
        }
        tm.setRowCount(bookList.size());
        for(int i=0;i<bookList.size();i++){
            tm.setValueAt(obj[i][0],i,0);
            tm.setValueAt(obj[i][1],i,1);
            tm.setValueAt(obj[i][2],i,2);
            tm.setValueAt(obj[i][3],i,3);
            tm.setValueAt(obj[i][4],i,4);
        }
        table.setModel(tm);
    }
    ArrayList<Book> fetchBookRecords(){
        ArrayList <Book>bookList = new ArrayList<Book>();
        String q="select * from book";
        try{
            ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Book b=new Book();
                b.setBookId(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setPrice(rs.getDouble(3));
                b.setAuthor(rs.getString(4));
                b.setPublisher(rs.getString(5));
                bookList.add(b);
            }
        }
        catch(SQLException ex){
            System.out.println("Exception: "+ex.getMessage());
        }
        finally{
            return bookList;
        }
    }

    //save button event handling
    class InsertBookRecord implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Book b1=readFromData(); //storing data in Book obj b1
            String q="insert into Book (bookid,title,price,author,publisher) values(?,?,?,?,?)";
            try{
                ps =con.prepareStatement(q);
                ps.setInt(1,b1.getBookId());
                ps.setString(2,b1.getTitle());
                ps.setDouble(3,b1.getPrice());
                ps.setString(4,b1.getAuthor());
                ps.setString(5,b1.getPublisher());
                ps.execute();
                //emptying the text box so that it doesn't show same data on the text fields and user won't insert same record in the table for multiple times by mistake
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");

            }
            catch(SQLException ex){
                System.out.println("Exception: "+ex.getMessage());
            }
        }
        //reads data of insert form
        Book readFromData(){
            Book b1=new Book();
            b1.setBookId(Integer.parseInt(t1.getText()));
            b1.setTitle(t2.getText());
            b1.setPrice(Double.parseDouble(t3.getText()));
            b1.setAuthor(t4.getText());
            b1.setPublisher(t5.getText());
            return b1; //returns book type data
        }
    }
    class TabChangeHandler implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            int index=tabbedPane.getSelectedIndex();
            if(index==0){
                System.out.println("Insert");
            }
            if(index==1){
                ArrayList <Book>bookList=fetchBookRecords();
                updateTable(bookList);
            }
        }
    }
    class UpdateBookRecord implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Book>updatedBookList=readTableData();
            String q="update Book set title=?,price=?,author=?,publisher=? where bookid=?";
            try{
                ps=con.prepareStatement(q);
                for(int i=0;i<updatedBookList.size();i++){
                    ps.setString(1,updatedBookList.get(i).getTitle());
                    ps.setDouble(2,updatedBookList.get(i).getPrice());
                    ps.setString(3,updatedBookList.get(i).getAuthor());
                    ps.setString(4,updatedBookList.get(i).getPublisher());
                    ps.setInt(5,updatedBookList.get(i).getBookId());
                    ps.executeUpdate();
                }
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            finally{
                ArrayList<Book> bookList=fetchBookRecords();
                updateTable(bookList);
            }

        }
        ArrayList<Book>readTableData(){
            ArrayList<Book>updatedBookList=new ArrayList<Book>();
            for(int i=0;i<table.getRowCount();i++){
                Book b=new Book();
                b.setBookId(Integer.parseInt(table.getValueAt(i,0).toString()));
                b.setTitle(table.getValueAt(i,1).toString());
                b.setPrice(Double.parseDouble(table.getValueAt(i,2).toString()));
                b.setAuthor(table.getValueAt(i,3).toString());
                b.setPublisher(table.getValueAt(i,4).toString());
                updatedBookList.add(b);
            }
            return updatedBookList;
        }
    }
    class DeleteBookRecord implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int rowNo=table.getSelectedRow();
            if(rowNo!=-1){
                int id=(int)table.getValueAt(rowNo,0);
                String q="delete from book where bookid=?";
                try{
                    ps=con.prepareStatement(q);
                    ps.setInt(1,id);
                    ps.execute();

                }
                catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
                finally{
                    ArrayList<Book> bookList=fetchBookRecords();
                    updateTable(bookList);
                }

            }

        }
    }
}

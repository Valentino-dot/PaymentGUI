import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PaymentGUI extends JDialog{
    private JPanel MainPannal;
    private JPanel TopPannal;
    private JPanel BottomPannal;
    private JPanel Payment_Info;
    private JTextField tfPaymentCode;
    private JTextField tfPayDate;
    private JTextField tfPaymentMethod;
    private JTextField tfStudentID;
    private JTextField tfStuffID;
    private JTextField tfPaidAmount;
    private JButton insertButton;
    private JTable showTable;
    private PreparedStatement ps;

    private JDBC db = new JDBC();


//    private void createTable(){
//        Object[][] data = {
//            try {
//                ps = con.prepareStatement
//            }
//        };
//        showTable.setModel(new DefaultTableModel(
//            data,
//        new String[]{"Payment Code", "Pay Date", "Payment Method", "Student ID", "Stuff ID", "Paid Amount"}));
//    }

//    public PaymentGUI(JFrame parent){
//        super(parent);
//        setTitle("Payment Interface");
//        setContentPane(MainPannal);
//        setMaximumSize(new Dimension(400, 200));
//        setModal(true);
//        setLocationRelativeTo(parent);
//
//        insertButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                InsertUser();
//            }
//        });
//
//        setVisible(true);
//    }
    void tableload(){
        final String db_url = "jdbc:mysql://localhost:3306/payment";
        try{
            Connection con = DriverManager.getConnection(db_url);

            String sql="SELECT code, data, method, stuID, staID, amount from payment";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            showTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }  catch (Exception e){
            e.printStackTrace();
        }
    }
    public PaymentGUI() {
 //       createTable();
        tableload();
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertUser();
            }
        });

//        setVisible(true);

    }


    private void InsertUser() {
        int paymentCode = Integer.valueOf(tfPaymentCode.getText());
        String payDate = tfPayDate.getText();
        String paymentMethod = tfPaymentMethod.getText();
        String studentID = tfStudentID.getText();
        String staffID = tfStuffID.getText();
        float paidAmount = Float.valueOf(tfPaidAmount.getText());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        java.sql.Date sqlDate = null;
        try {
            // Parse the date to check if it's valid
            java.util.Date utilDate = dateFormat.parse(payDate);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            // Handle invalid date format
            System.out.println("Invalid date format: " + payDate);
            return;
        }

        db.AddPayment(paymentCode, studentID, staffID, sqlDate, paymentMethod, paidAmount);
    }



    public static void main (String[] args){
//        PaymentGUI paymentGUI = new PaymentGUI(null);
        JFrame frame = new JFrame("Payment Interface");
        frame.setContentPane(new PaymentGUI().MainPannal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

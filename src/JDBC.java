import javax.swing.*;
import java.sql.*;

public class JDBC  {
    private PreparedStatement ps;
    private ResultSet r;
    public Connection connection(){

        Connection con = null;

        try
        {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/payment","root","");
//            JOptionPane.showMessageDialog(null,"connected with "+con.toString());

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"not connect to server and message is"+e.getMessage());
        }

        return con;

    }
    public void FetchPayment(){
        try{
            ps = connection().prepareStatement("SELECT * FROM tbpayment");
            r = ps.executeQuery();
            while(r.next()){
               ListPayment.list_payment.add(new ListPayment(
                       r.getInt("code"),
                       r.getString("date"),
                       r.getString("method"),
                       r.getDate("stuID"),
                       r.getString("staID"),
                       r.getFloat("amount")
                       ));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //----------
    // Add
    public void AddPayment(int code, String stuID, String staID , Date date, String method, float amount){
        try{
            ps = connection().prepareStatement("INSERT INTO tbpayment(code,date,method,stuID,staID,amount) VALUES(?,?,?,?,?,?)");
            ps.setInt(1, code);
            ps.setDate(2, date);
            ps.setString(3, method);
            ps.setString(4, stuID);
            ps.setString(5, staID);
            ps.setFloat(6, amount);
            int index = ps.executeUpdate();
            if(index > 0){
                JOptionPane.showMessageDialog(null, "Payment Add Success");
            }else{
                JOptionPane.showMessageDialog(null, "Payment Add Error!");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}



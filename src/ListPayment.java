import java.util.ArrayList;
import java.util.Date;

public class ListPayment {
    private int code;
    private String  method,stuID,staID;
    private  Date date;
    private float amount;

    public ListPayment(int code, String stuID, String staID , Date date, String method, float amount) {
        this.code = code;
        this.stuID = stuID;
        this.staID = staID;
        this.date = date;
        this.method = method;
        this.amount = amount;
    }




    public Date getDate() {
        return date;
    }

    public int getCode() {
        return code;
    }

    public String getStuID() {
        return stuID;
    }

    public String getStaID() {
        return staID;
    }

    public String getMethod() {
        return method;
    }

    public float getAmount() {
        return amount;
    }

    public static ArrayList<ListPayment> getList_payment() {
        return list_payment;
    }



        static ArrayList<ListPayment> list_payment = new ArrayList<>();

}

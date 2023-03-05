import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestingDbConnection {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banktask","root","1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bankUsers");

            while (rs.next()){
                System.out.println(rs.getString(2));
            }

        }catch (Exception e){
            System.out.println(e);

            // test OK, correct data is taken from the correct table

        }
    }
}

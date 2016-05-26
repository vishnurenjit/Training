
package sum;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Sum {

    public static String DB_URL = "jdbc:mysql://localhost:3306/sum";
    public static String DB_USERNAME = "root";
    public static String DB_PASSWORD = "mysql";
    
   
   
    public static void main(String[] args) throws SQLException {
       Connection connection = null ;
       Statement stmt = null;
       
       try {
           int a = 0;
            int b = 0;
            int result = 0;
           Scanner inp = new Scanner(System.in);
           System.out.println("Enter 2 numbers :");
           a = inp.nextInt();
           b = inp.nextInt();
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
           stmt = connection.createStatement();
           String query = "SELECT * FROM sum";
           ResultSet rs = stmt.executeQuery(query);
           while(rs.next()){
             int first = rs.getInt("a");
             int second = rs.getInt("b");
             if(a == first && b == second){
                 int c = rs.getInt("c");
                 System.out.println("Sum of a & b :"+c);
             }else{
                 result = a + b;
                 System.out.println("Sum of a & b :"+ result);
                 String sql = "INSERT INTO sum(a,b,c)VALUES(a,b,result) ";
                    int retu = stmt.executeUpdate(sql);
                    if(retu > 0){
                        System.out.println("success");
                    }
                 
             }
           }
           rs.close();
           stmt.close();
           connection.close();
       }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Sum.class.getName()).log(Level.SEVERE, null, ex);
        }    }
}

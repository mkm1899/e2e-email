package e2e;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Database {
    private Connection databaseConnection;

    public Database(){
        establishConnectionToDatabase();
    }

    private void establishConnectionToDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            System.out.println("WRITE");
            String u = "user";
            String p = "ASD732rk@nfba";
            String url = "jdbc:mysql://localhost:6379/e2e";
            databaseConnection = DriverManager.getConnection(url, u, p);
            System.out.println("NO ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("NO ERROR");
    }

    public void doSomthing(){
        System.out.println("28000");
    }
    /*
    public void AddEmail(Email email){

    }
    */

    public static void main(String args[]){
        Database database = new Database();

    }
}
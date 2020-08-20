package e2e;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.*;

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
            System.exit(1);
        }

        try{
            System.out.println("WRITE");
            //String u = "root";//"user"; //"root"
            //String p = "root";//"ASD732rk@nfba"; //"root"
            String u = "user"; //"root"
            String p = "ASD732rk@nfba"; //"root"
            //String url = "jdbc:mysql://localhost:3306/e2e";
            String url = "jdbc:mysql://localhost:3306/e2e?serverTimezone=UTC";
            databaseConnection = DriverManager.getConnection(url, u, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try{
            databaseConnection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    public void AddEmail(Email email){

    }
    */

    public static void main(String args[]){
        Database database = new Database();

    }
}
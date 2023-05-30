package database;

import java.sql.*;

public class DbAccess {
    private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final String DBMS = "jdbc:mysql";
    private final String SERVER = "localhost";
    private final String DATABASE = "MapDB";
    private final String PORT= "3306";
    private final String USER_ID = "MapUser";
    private final String PASSWORD = "map";
    private Connection conn;

    public void initConnection() throws DatabaseConnectionException{
        //chiedo al class loader di caricare il driver mysql
        try{
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        }catch (ClassNotFoundException e){
            System.out.println("Error, class not found: " + e.getMessage());
        }catch (InstantiationException e){
            System.out.println("Error, instantiation failed: " + e.getMessage());
        }catch (IllegalAccessException e){
            System.out.println("Error, illegal access: " + e.getMessage());
        }

        //inizializza la connessione col database
        String connectionURL = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
        try{
            conn = DriverManager.getConnection(connectionURL, USER_ID, PASSWORD);
            System.out.println("Connection to the database successful.");
        }catch(SQLException e){
            throw new DatabaseConnectionException(e.getMessage());
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public void closeConnection(){
        try{
            conn.close();
        }catch(SQLException e){
            System.out.println("Error occurred while closing the connection:" + e.getMessage());
        }
    }
}

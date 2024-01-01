package database;

import java.sql.*;

/**
 * Classe che gestisce l'accesso al database MySQL.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class DbAccess {
    /** Nome della classe del driver JDBC MySQL. */
    private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    /** URL del database MySQL. */
    private final String DBMS = "jdbc:mysql";
    /** Indirizzo del server MySQL. */
    private final String SERVER = "localhost";
    /** Nome del database MySQL. */
    private final String DATABASE = "MapDB";
    /** Porta del server MySQL. */
    private final String PORT= "3306";
    /** ID utente per la connessione al database MySQL. */
    private final String USER_ID = "MapUser";
    /** Password per la connessione al database MySQL. */
    private final String PASSWORD = "map";
    /** Oggetto di connessione al database. */
    private Connection conn;

    /**
     * Inizializza la connessione al database.
     *
     * @throws DatabaseConnectionException Se si verifica un errore durante l'inizializzazione della connessione.
     */
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

    /**
     * Restituisce l'oggetto di connessione al database.
     *
     * @return L'oggetto di connessione al database.
     */
    public Connection getConnection(){
        return conn;
    }

    /**
     * Chiude la connessione al database.
     */
    public void closeConnection(){
        try{
            conn.close();
        }catch(SQLException e){
            System.out.println("Error occurred while closing the connection:" + e.getMessage());
        }
    }
}

package database;

/**
 * Eccezione che rappresenta un errore durante la connessione al database.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class DatabaseConnectionException extends Exception{
    /**
     * Costruttore della classe {@code DatabaseConnectionException}.
     *
     * @param message Il messaggio descrittivo dell'eccezione.
     */
    DatabaseConnectionException(String message){
        super(message);
    }
}

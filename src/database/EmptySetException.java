package database;

/**
 * Eccezione che rappresenta un insieme vuoto.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class EmptySetException extends Exception {
    /**
     * Costruttore della classe `EmptySetException`.
     *
     * @param message Il messaggio descrittivo dell'eccezione.
     */
    public EmptySetException(String message){
        super(message);
    }
}

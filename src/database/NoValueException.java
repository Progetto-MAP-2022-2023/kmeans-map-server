package database;

/**
 * Eccezione che rappresenta l'assenza di un valore, ad esempio in caso di risultato vuoto o valore nullo.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class NoValueException extends Exception {
    /**
     * Costruttore della classe {@code NoValueException}.
     *
     * @param message Il messaggio descrittivo dell'eccezione.
     */
    public NoValueException(String message){
        super(message);
    }
}

package data;

/**
 * Eccezione che indica che il numero indicato è fuori dal range consentito.
 * Può essere sollevata quando il numero di cluster specificato è inferiore a 1 o maggiore del numero di esempi nei dati.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class OutOfRangeSampleSize extends Exception {
    /**
     * Costruisce un'istanza di {@code OutOfRangeSampleSize} con il messaggio di errore specificato
     * e la causa originale dell'eccezione.
     *
     * @param message Il messaggio di errore descrittivo.
     * @param e       La causa originale dell'eccezione.
     */
    public OutOfRangeSampleSize(String message, Throwable e){
        super(message, e);
    }
}

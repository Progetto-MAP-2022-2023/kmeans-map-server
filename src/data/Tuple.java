package data;

import java.io.Serializable;

/**
 * Classe che rappresenta una tupla di oggetti di tipo {@link Item}.
 * Implementa l'interfaccia {@link Serializable} per la serializzazione.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class Tuple implements Serializable {
    /** Array di oggetti di tipo {@link Item}. */
    private Item[] tuple;
    /**
     * Costruttore della classe `Tuple`.
     *
     * @param size La dimensione della tupla.
     */
    public Tuple(int size){
        tuple = new Item[size];
    }
    /**
     * Restituisce la lunghezza della tupla.
     *
     * @return La lunghezza della tupla.
     */
    public int getLength(){
        return tuple.length;
    }

    /**
     * Restituisce l'oggetto di tipo {@link Item} in posizione specifica della tupla.
     *
     * @param i La posizione dell'oggetto.
     * @return L'oggetto di tipo {@link Item} in posizione specifica.
     */
    public Item get(int i){
        return tuple[i];
    }

    /**
     * Aggiunge un oggetto di tipo {@link Item} in una specifica posizione della tupla.
     *
     * @param c L'oggetto di tipo {@link Item} da aggiungere.
     * @param i La posizione in cui aggiungere l'oggetto.
     */
    public void add(Item c,int i){
        tuple[i] = c;
    }

    /**
     * Calcola la distanza tra questa tupla e un'altra tupla.
     *
     * @param obj L'altra tupla con cui calcolare la distanza.
     * @return La distanza tra le due tuple.
     */
    public double getDistance(Tuple obj) {
        int length;
        double distance = 0;

        for (int i = 0; i < this.getLength(); i++) {
            distance = distance + this.tuple[i].distance(obj.tuple[i]);
        }

        return distance;
    }

    /**
     * Calcola la distanza media tra questa tupla e un insieme di tuple definite da indici.
     *
     * @param data L'oggetto di tipo {@link Data} contenente le tuple.
     * @param clusteredData Gli indici delle tuple nel set clusterizzato.
     * @return La distanza media tra questa tupla e l'insieme di tuple clusterizzato.
     */
    public double avgDistance(Data data, int[] clusteredData) {
        double p = 0.0, sumD = 0.0;
        for(int i = 0; i < clusteredData.length; i++) {
            double d = getDistance(data.getItemSet(clusteredData[i]));
            sumD += d;
        }
        p = sumD / clusteredData.length;
        return p;
    }
}

package database;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un esempio o una transazione.
 * Implementa l'interfaccia Comparable per la comparazione con altri oggetti Example.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class Example implements Comparable<Example> {
    /** Lista di oggetti che compongono l'esempio. */
    private List<Object> example = new ArrayList<Object>();
    /**
     * Aggiunge un oggetto all'esempio.
     *
     * @param o L'oggetto da aggiungere.
     */
    public void add(Object o) {
        example.add(o);
    }
    /**
     * Ottiene l'oggetto in posizione specifica dell'esempio.
     *
     * @param i La posizione dell'oggetto.
     * @return L'oggetto in posizione specifica.
     */
    public Object get(int i) {
        return example.get(i);
    }

    /**
     * Confronta questo esempio con un altro per determinare l'ordine.
     *
     * @param ex L'altro esempio da confrontare.
     * @return Un valore negativo se questo esempio è inferiore, un valore positivo se è superiore, 0 se sono uguali.
     */
    public int compareTo(Example ex) {

        int i = 0;
        for (Object o : ex.example) {
            if (!o.equals(this.example.get(i)))
                return ((Comparable) o).compareTo(example.get(i));
            i++;
        }
        return 0;
    }

    /**
     * Restituisce una rappresentazione stringa di questo esempio.
     *
     * @return Una stringa che rappresenta l'esempio.
     */
    public String toString() {
        String str = "";
        for (Object o : example)
            str += o.toString() + " ";
        return str;
    }
}

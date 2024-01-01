package data;

import java.io.Serializable;
import java.util.*;

/**
 * Classe astratta che rappresenta un elemento (Item) associato a un attributo.
 * Implementa l'interfaccia {@link Serializable} per la serializzazione.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public abstract class Item implements Serializable {
    /** Oggetto di tipo {@link Attribute} associato all'elemento. */
    private Attribute attribute;
    /** Valore dell'elemento. */
    private Object value;

    /**
     * Costruttore della classe `Item`.
     *
     * @param attribute Oggetto di tipo {@link Attribute} associato all'elemento.
     * @param value Valore dell'elemento di tipo {@link Object}.
     */
    Item(Attribute attribute, Object value){
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce l'attributo associato a questo elemento.
     *
     * @return Oggetto di tipo {@link Attribute} associato a questo elemento.
     */
    Attribute getAttribute(){
        return attribute;
    }

    /**
     * Restituisce il valore dell'elemento.
     *
     * @return Il valore dell'elemento di tipo {@link Object}.
     */
    Object getValue(){
        return value;
    }

    /**
     * Restituisce una rappresentazione in forma di stringa del valore dell'elemento.
     *
     * @return Il valore dell'elemento convertito in stringa.
     */
    @Override
    public String toString(){
        return value.toString();
    }

    /**
     * Calcola la distanza tra due elementi.
     *
     * @param a L'elemento con cui calcolare la distanza.
     * @return La distanza tra "a" e questo oggetto (un valore "double").
     */
    abstract double distance(Object a);

    /**
     * Confronta il valore tra due elementi.
     *
     * @param obj L'elemento con cui confrontare.
     * @return true se il valore di "obj" è lo stesso di questo oggetto, false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Modifica il valore dell'elemento utilizzando i dati dal dataset e gli elementi clusterizzati.
     *
     * @param data Dataset da cui ottenere i dati.
     * @param clusteredData Elementi appartenenti a un cluster.
     */
    public void update(Data data, Set<Integer> clusteredData){
        value = data.computePrototype(clusteredData, attribute);
    }
}

package data;

import java.io.Serializable;

/**
 * Classe astratta che rappresenta un attributo generico.
 * Implementa l'interfaccia {@link Serializable}.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
abstract class Attribute implements Serializable {
    /** Nome dell'attributo. */
    private String name;
    /** Indice dell'attributo. */
    private int index;

    /**
     * Costruttore per la classe Attribute.
     *
     * @param name  Nome dell'attributo.
     * @param index Indice dell'attributo.
     */
    Attribute(String name, int index){
        this.name = name;
        this.index = index;
    }

    /**
     * Ottiene il nome dell'attributo.
     *
     * @return Il nome dell'attributo.
     */
    String getName(){
        return this.name;
    }

    /**
     * Ottiene l'indice dell'attributo.
     *
     * @return L'indice dell'attributo.
     */
    int getIndex(){
        return this.index;
    }

    /**
     * Restituisce una rappresentazione in stringa dell'attributo (il suo nome).
     *
     * @return Una stringa che rappresenta l'attributo.
     */
    public String toString(){
        return this.name;
    }

}

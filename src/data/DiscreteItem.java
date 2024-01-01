package data;

/**
 * Rappresenta un elemento discreto associato a un attributo discreto.
 * Estende dalla classe {@link Item}
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
class DiscreteItem extends Item {

    /**
     * Costruttore della classe `DiscreteItem`.
     *
     * @param attribute Attributo discreto associato all'elemento discreto.
     * @param value Valore che l'elemento discreto assume.
     */
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    /**
     * Calcola la distanza tra due elementi discreti.
     *
     * @param a L'elemento con cui calcolare la distanza.
     * @return 0 se il valore di "a" è uguale al valore di questo elemento, 1 altrimenti.
     */
    double distance(Object a){
        return this.equals(a) ? 0 : 1;
    }

    /**
     * Confronta il valore tra due elementi discreti.
     *
     * @param obj L'elemento con cui confrontare.
     * @return true se il valore di "obj" è lo stesso di questo elemento, false altrimenti.
     */
    @Override
    public boolean equals(Object obj){
        return (((String)this.getValue()).equals(((Item)obj).getValue()));
    }

}

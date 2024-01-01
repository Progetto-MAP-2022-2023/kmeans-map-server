package data;

import java.lang.Math;

/**
 * Rappresenta un elemento con un attributo continuo.
 * Estende la classe {@link Item}.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
class ContinuousItem extends Item{
    /**
     * Costruttore per ContinuousItem.
     *
     * @param attribute L'attributo continuo dell'elemento.
     * @param value     Il valore dell'attributo continuo.
     */
    ContinuousItem(Attribute attribute, Double value){
        super(attribute, value);
    }

    /**
     * Calcola la distanza tra due elementi continui.
     * La distanza si basa sulla differenza assoluta tra i valori scalati degli attributi continui.
     *
     * @param a L'altro elemento continuo da cui calcolare la distanza.
     * @return La distanza tra i due elementi continui.
     */
    double distance(Object a){
        double thisScaledValue = ((ContinuousAttribute)this.getAttribute()).getScaledValue((Double)this.getValue());
        double aScaledValue = ((ContinuousAttribute)((ContinuousItem)a).getAttribute()).getScaledValue((Double)((ContinuousItem)a).getValue());
        return Math.abs(thisScaledValue - aScaledValue);
    }
}

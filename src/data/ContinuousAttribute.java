package data;

/**
 * Rappresenta un attributo continuo.
 * Estende la classe {@link Attribute}.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
class ContinuousAttribute extends Attribute {
    /** Il valore minimo dell'attributo {@code ContinuousAttribute}. */
    private double min;
    /** Il valore massimo dell'attributo {@code ContinuousAttribute}. */
    private double max;

    /**
     * Costruttore per ContinuousAttribute.
     *
     * @param name  Il nome dell'attributo continuo.
     * @param index L'indice dell'attributo continuo.
     * @param min   Il valore minimo dell'attributo {@code ContinuousAttribute}.
     * @param max   Il valore massimo dell'attributo {@code ContinuousAttribute}.
     */
    ContinuousAttribute(String name, int index, double min, double max){
        super(name, index);
        this.min = min;
        this.max = max;
    }

    /**
     * Ottiene il valore normalizzato (scalato) dell'attributo continuo.
     *
     * @param v Il valore da scalare.
     * @return Il valore normalizzato (scalato) dell'attributo continuo.
     */
    double getScaledValue(double v){
        double vnormalized;

        vnormalized = (v-min)/(max-min);

        return vnormalized;
    }

}

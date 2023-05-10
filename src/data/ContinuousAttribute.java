package data;

/**
 * The ContinuousAttribute class extends Attribute class and models data of type continuousattribute (numeric).
 * This class includes methods for the normalization of the continuousattribute's domain in the range [0,1] to make them
 * comparable with attributes having different domains
 */
class ContinuousAttribute extends Attribute {

    /**
     * A double that represents the minor extreme of the interval (domain)
     */
    private double min;

    /**
     * A double that represents the major extreme of the interval (domain)
     */
    private double max;

    /**
     * Constructor method that invokes teh superclass' constructor. It initializes member values added by extension
     * @param name attribute's name
     * @param index numerical identifier of the attribute in the dataset
     * @param min the minor extreme of the interval
     * @param max the major extreme of the interval
     */
    ContinuousAttribute(String name, int index, double min, double max){
        super(name, index);
        this.min = min;
        this.max = max;
    }

    /**
     *Method that calculate normalized value of the attribute in input with codomain the interval [0,1]
     * @param v attribute's value to normalize
     * @return vnormalized normalized value
     */
    double getScaledValue(double v){
        double vnormalized;

        vnormalized = (v-min)/(max-min);

        return vnormalized;
    }

}

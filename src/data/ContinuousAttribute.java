package data;

class ContinuousAttribute extends Attribute {
    private double min;
    private double max;
    ContinuousAttribute(String name, int index, double min, double max){
        super(name, index);
        this.min = min;
        this.max = max;
    }

    double getScaledValue(double v){
        double vnormalized;

        vnormalized = (v-min)/(max-min);

        return vnormalized;
    }

}

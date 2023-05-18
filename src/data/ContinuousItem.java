package data;

import java.lang.Math;

class ContinuousItem extends Item{
    ContinuousItem(Attribute attribute, Double value){
        super(attribute, value);
    }

    double distance(Object a){
        double thisScaledValue = ((ContinuousAttribute)this.getAttribute()).getScaledValue((Double)this.getValue());
        double aScaledValue = ((ContinuousAttribute)((ContinuousItem)a).getAttribute()).getScaledValue((Double)((ContinuousItem)a).getValue());
        return Math.abs(thisScaledValue - aScaledValue);
    }
}

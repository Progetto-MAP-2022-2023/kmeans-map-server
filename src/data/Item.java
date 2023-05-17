package data;

import java.util.*;

/**
 * Class that models an item; it contains an attribute and a value
 *
 * @author Antonio Catanzaro (GitHub: KoteiMadoshi)
 * @author Daniele Grandolfo
 * @author Rosanna Fracchiola
 */
public abstract class Item {
    private Attribute attribute;
    private Object value;

    /**
     * Constructor; inizialize the attribute and the value
     * @param attribute object of type "data.Attribute"
     * @param value object of type "Object"
     */
    Item(Attribute attribute, Object value){
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * @return the attribute
     */
    Attribute getAttribute(){
        return attribute;
    }

    /**
     * @return the value
     */
    Object getValue(){
        return value;
    }

    /**
     * Methods the return the value in the form of a string
     * @return "value" casted to String
     */
    @Override
    public String toString(){
        return (String)value;
    }

    /**
     * Methods that calculate the distance between two items
     * @param a the item with which to calculate the distance
     * @return the distance between "a" and this object (a "double" value)
     */
    abstract double distance(Object a);

    /**
     * Methods that compare the value between two items
     * @param obj the item to compare with
     * @return true if the value of "obj" is the same of this object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Methods that modify "value"
     * @param data dataset to get the data from
     * @param clusteredData elements belonging to a cluster
     */
    public void update(Data data, Set<Integer> clusteredData){
        value = data.computePrototype(clusteredData, attribute);
    }

}

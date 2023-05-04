/**
 * Class that extends "Item" and work on discrete item types
 * @see Item
 * @author Antonio Catanzaro (GitHub: KoteiMadoshi)
 * @author Daniele Grandolfo
 * @author Rosanna Fracchiola
 */
public class DiscreteItem extends Item{

    /**
     * Constructor, call the constructor of "Item"
     * @param attribute the attribute of the discrete item
     * @param value the value that the discrete item assumes
     */
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    /**
     * Methods that calculate the distance between two items
     * @param a the item with which to calculate the distance
     * @return 0 if the value of "a" is equal to the value of this item, 1 otherwise
     */
    double distance(Object a){
        return this.equals(a) ? 0 : 1;
    }

    /**
     * Methods that compare the value between two items
     * @param obj the item to compare with
     * @return true if the value of "obj" is the same of this object, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        return (((String)this.getValue()).equals(((Item)obj).getValue()));
    }

}

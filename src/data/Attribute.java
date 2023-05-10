package data;

/**
 * The  Attribute class models data of type attribute made up of a name and an index
 */

abstract class Attribute {

    /**
     *A string for attribute name's
     */

    private String name;

    /**
     * An integer for the index, that shows attribute's position in the dataset
     */
    private int index;

    /**
     * Constructor method that initializes member values, name and index
     * @param name attribute's name
     * @param index numerical identifier of the attribute in the dataset
     */

    Attribute(String name, int index){
        this.name = name;
        this.index = index;
    }

    /**
     * Method that returns the name of an attribute in the dataset
     * @return attribute's name
     */
    String getName(){
        return name;
    }

    /**
     * Method that returns the index of an attribute in the dataset
     * @return index's attribute, its numerical identifier
     */
    int getIndex(){
        return index;
    }

    /**
     * Method that override superclass' method, returning the string that represents attribute's state
     * @return name, the attribute's name
     */
    public String toString(){
        return name;
    }

}

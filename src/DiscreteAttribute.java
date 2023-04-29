class DiscreteAttribute extends Attribute {

    private String[] values;

    DiscreteAttribute(String name, int index, String values[]){
        super(name, index);

        this.values = new String[values.length];
        System.arraycopy(values, 0, this.values, 0, this.values.length);

    }

    int getNumberOfDistinctValues(){
        return values.length;
    }

    String getValue(int i){
        return values[i];
    }

}

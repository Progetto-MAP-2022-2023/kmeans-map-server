package data;

import data.Attribute;
import data.Data;
import utility.ArraySet;

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
    int frequency(Data data, ArraySet idList, String v){
        int[] position;
        int occurrences = 0;
        String attributeValue;
        Attribute attribute;

        // prendo indici di riga da controllare
        position=idList.toArray();

        // se il valore è presente lo conta
        for(int i = 0; i < position.length; i++){
            attributeValue = (String)data.getAttributeValue(position[i], super.getIndex());
            if(v.equals(attributeValue)){
                occurrences +=1;
            }
        }

        return occurrences;
    }
}

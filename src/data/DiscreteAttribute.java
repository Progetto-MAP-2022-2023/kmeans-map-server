package data;

import utility.ArraySet;
import java.util.*;

class DiscreteAttribute extends Attribute implements Iterable<String>{

    private TreeSet<String> values;

    DiscreteAttribute(String name, int index, String[] values){
        super(name, index);
        this.values = new TreeSet<String>(Arrays.asList(values));
    }

    int getNumberOfDistinctValues(){
        return values.size();
    }

    public Iterator<String> iterator(){
        return this.values.iterator();
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



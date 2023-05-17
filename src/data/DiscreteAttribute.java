package data;

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

    int frequency(Data data, Set<Integer> idList, String v){
        int occurrences = 0;
        String attributeValue;

        // se il valore è presente lo conta
        for(Integer iterIdList : idList){
            attributeValue = (String)data.getAttributeValue(iterIdList, super.getIndex());
            if(v.equals(attributeValue)){
                occurrences +=1;
            }
        }

        return occurrences;
    }

}



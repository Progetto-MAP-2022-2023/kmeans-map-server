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
        int errorValue = -1;
        int index = errorValue;
        String attributeValue;
        Attribute attribute;

        // prendo indici di riga da controllare
        position=idList.toArray();

        // controlla in quale indice può essere presente il valore discreto v
        for(int i = 0; i < data.getNumberOfAttributes(); i++){
            attribute = data.getAttribute(i);
            DiscreteAttribute discreteAux = (DiscreteAttribute)attribute;
            for(int j=0; j<discreteAux.getNumberOfDistinctValues(); j++){
                if(v == discreteAux.getValue(j)){
                    index = i;
                }
            }
        }
        //eccezione v non trovato in nessun attributo

        // se il valore discreto non è presente ritorna 0 come occorrenza
        if(index == errorValue){
            return 0;
        }

        // se il valore è presente lo conta
        for(int i = 0; i < position.length; i++){
            attributeValue = (String)data.getAttributeValue(position[i], index);
            if(v == attributeValue){
                occurrences +=1;
            }
        }

        return occurrences;
    }
}

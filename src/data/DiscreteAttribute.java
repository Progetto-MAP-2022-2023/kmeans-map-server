package data;

import java.util.*;

/**
 * Rappresenta un attributo discreto che può assumere un insieme finito di valori distinti.
 * Estende la classe astratta {@link Attribute} e implementa l'interfaccia {@link Iterable} per supportare l'iterazione sui suoi valori.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
class DiscreteAttribute extends Attribute implements Iterable<String>{
    /**
     * Rappresenta l'insieme ordinato dei valori distinti che l'attributo discreto può assumere.
     */
    private TreeSet<String> values;

    /**
     * Costruttore della classe `DiscreteAttribute`.
     *
     * @param name  Nome dell'attributo discreto.
     * @param index Indice dell'attributo discreto.
     * @param values Array di stringhe rappresentanti i valori distinti dell'attributo discreto.
     */
    DiscreteAttribute(String name, int index, String[] values){
        super(name, index);
        this.values = new TreeSet<String>(Arrays.asList(values));
    }

    /**
     * Restituisce il numero di valori distinti dell'attributo discreto.
     *
     * @return Numero di valori distinti dell'attributo discreto.
     */
    int getNumberOfDistinctValues(){
        return values.size();
    }

    /**
     * Restituisce un iteratore per attraversare i valori dell'attributo discreto.
     *
     * @return Iteratore sui valori dell'attributo discreto.
     */
    public Iterator<String> iterator(){
        return this.values.iterator();
    }

    /**
     * Calcola la frequenza di un valore specifico nell'insieme di dati dato.
     *
     * @param data   Dataset da cui ottenere i dati.
     * @param idList Insieme di identificatori rappresentanti le tuple da considerare.
     * @param v      Valore di cui calcolare la frequenza.
     * @return Numero di occorrenze del valore specificato nell'insieme di dati dato.
     */
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



public class DiscreteItem extends Item{

    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    double distance(Object a){
        return this.equals(a) ? 0 : 1;
    }

    //override dell'equals su DiscreteItem: effettua l'equals sul getValue dell'item castato a stringa confrontandolo
    //con il valore presente nell'item di obj, in modo da fare l'equals sulle stringhe
    @Override
    public boolean equals(Object obj){
        return (((String)this.getValue()).equals(((Item)obj).getValue()));
    }

}

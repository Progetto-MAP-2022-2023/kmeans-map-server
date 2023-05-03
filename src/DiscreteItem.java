public class DiscreteItem extends Item{

    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    double distance(Object a){
        return (this.getValue().equals(a)) ? 0 : 1;
    }

}

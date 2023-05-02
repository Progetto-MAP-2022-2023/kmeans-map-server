public class DiscreteItem extends Item{

    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    double distance(Object a){
        if(this.getValue().equals(a)){
            return 0;
        } else {
            return 1;
        }
    }

}

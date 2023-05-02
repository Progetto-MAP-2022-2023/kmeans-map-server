public class Tuple {
    private Item[] tuple;

    Tuple(int size){
        tuple = new Item[size];
    }

    int getLength(){
        return tuple.length;
    }

    Item get(int i){
        return tuple[i];
    }

    void add(Item c,int i){
        tuple[i] = c;
    }

    double getDistance(Tuple obj) {
        int length;
        double distance = 0;

        if (tuple.length < obj.getLength()) {
            length = tuple.length;
        } else {
            length = obj.getLength();
        }
        for (int i = 0; i < length; i++) {
            distance = distance + this.tuple[i].distance(obj.tuple[i]);
        }

        return distance;
    }

    double avgDistance(Data data, int[] clusteredData) {
        double p = 0.0, sumD = 0.0;
        for(int i = 0; i < clusteredData.length; i++) {
            double d = getDistance(data.getItemSet(clusteredData[i]));
            sumD += d;
        }
        p = sumD / clusteredData.length;
        return p;
    }

}

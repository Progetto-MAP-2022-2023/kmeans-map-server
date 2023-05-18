package mining;

import data.Data;
import data.Tuple;
import data.OutOfRangeSampleSize;

import java.io.Serializable;


public class ClusterSet implements Serializable {
    private Cluster[] C;
    private int i = 0;

    ClusterSet(int k) throws OutOfRangeSampleSize{
        if(k <= 1){
            throw new OutOfRangeSampleSize("Number of cluster must be greater than 1.", new UnsupportedOperationException());
        }
        C = new Cluster[k];
    }

    private void add(Cluster c){
        C[i] = c;
        i += 1;
    }

    Cluster get(int i){
        return C[i];
    }

    void initializeCentroids(Data data) throws OutOfRangeSampleSize{

        int[] centroidIndexes = data.sampling(C.length);
        for(int i = 0; i<centroidIndexes.length; i++) {
            Tuple centroidI = data.getItemSet(centroidIndexes[i]);
            this.add(new Cluster(centroidI));
        }

    }

    Cluster nearestCluster(Tuple tuple){
        double minDistance;
        double tempDistance;
        int clusterIndex;
        int i = 0;

        minDistance = tuple.getDistance(C[i].getCentroid());
        clusterIndex = i;
        for(i = 1; i < C.length; i++){
            tempDistance = tuple.getDistance(C[i].getCentroid());
            if(tempDistance < minDistance){
                minDistance = tempDistance;
                clusterIndex = i;
            }
        }
        return C[clusterIndex];
    }

    Cluster currentCluster(int id){

        for(int i = 0; i < C.length; i++) {
            if (C[i].contain(id)) {
                return C[i];
            }
        }
        return null;
    }

    void updateCentroids(Data data){

        for(int i = 0; i < C.length; i++){
            C[i].computeCentroid(data);
        }
    }

    @Override
    public String toString(){
        String s = "";

        for(int i = 0; i < C.length; i++){
            s += i + " " + C[i].toString();
        }

        return s;
    }

    public String toString(Data data){
        String str = "";

        for(int i=0; i<C.length; i++){
            if (C[i] != null){
                str += i + ":" + C[i].toString(data) + "\n";
            }
        }

        return str;
    }

}

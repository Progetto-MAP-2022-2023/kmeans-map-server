public class ClusterSet {
    private Cluster[] C;
    private int i = 0;

    ClusterSet(int k){
        C = new Cluster[k];
    }

    void add(Cluster c){
        C[i] = c;
        i+=1;
    }

    Cluster get(int i){
        return C[i];
    }

    void initializeCentroids(Data data){
        int centroidIndexes[]=data.sampling(C.length);
        for(int i = 0;i<centroidIndexes.length; i++)
        {
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

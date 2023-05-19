package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import java.io.*;

public class KMeansMiner {
    private ClusterSet C;

    public KMeansMiner(int k) throws OutOfRangeSampleSize{
        this.C = new ClusterSet(k);
    }

    public KMeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));

        this.C = (ClusterSet)objectInputStream.readObject();
        objectInputStream.close();
    }

    public ClusterSet getC(){
        return this.C;
    }

    public int kmeans(Data data) throws OutOfRangeSampleSize {
        int numberOfIterations=0;
        //STEP 1
        C.initializeCentroids(data);
        boolean changedCluster=false;
        do{
            numberOfIterations++;
            //STEP 2
            changedCluster=false;
            for(int i = 0; i<data.getNumberOfExamples(); i++){
                Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));
                Cluster oldCluster=C.currentCluster(i);
                boolean currentChange=nearestCluster.addData(i);
                if(currentChange)
                    changedCluster=true;
                //rimuovo la tupla dal vecchio cluster
                if(currentChange && oldCluster!=null)
                    //il nodo va rimosso dal suo vecchio cluster
                    oldCluster.removeTuple(i);
            }
            //STEP 3
            C.updateCentroids(data);
        }
        while(changedCluster);
        return numberOfIterations;
    }

    public void salva(String fileName) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

        objectOutputStream.writeObject(this.C);
        objectOutputStream.close();
    }
}

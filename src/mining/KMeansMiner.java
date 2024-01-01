package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import java.io.*;

/**
 * Classe che implementa l'algoritmo K-Means per il clustering di dati.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class KMeansMiner {
    /**
     * Insieme di cluster ottenuti da un'operazione di clustering.
     * Contiene informazioni sui centroidi e sugli elementi associati a ciascun cluster.
     */
    private ClusterSet C;

    /**
     * Costruisce un nuovo oggetto {@link KMeansMiner} inizializzando un insieme di cluster {@code C}
     * con il numero specificato di cluster {@code k}.
     *
     * @param k Il numero di cluster desiderato.
     * @throws OutOfRangeSampleSize Se il numero di cluster specificato è inferiore a 1.
     */
    public KMeansMiner(int k) throws OutOfRangeSampleSize{
        this.C = new ClusterSet(k);
    }

    /**
     * Costruisce un nuovo oggetto {@link KMeansMiner} caricando i dati da un file specificato.
     * Legge un oggetto di tipo {@link  ClusterSet} dal file, che rappresenta un insieme di cluster
     * ottenuti da un'operazione di clustering precedente.
     *
     * @param fileName Il nome del file da cui caricare i dati.
     * @throws FileNotFoundException Se il file specificato non è trovato.
     * @throws IOException           Se si verifica un errore durante l'operazione di I/O durante la lettura del file.
     * @throws ClassNotFoundException Se la classe richiesta per la deserializzazione non è trovata.
     */
    public KMeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));

        this.C = (ClusterSet)objectInputStream.readObject();
        objectInputStream.close();
    }

    /**
     * Restituisce l'insieme di cluster {@code C} associato all'oggetto {@link KMeansMiner}.
     *
     * @return L'insieme di cluster associato all'oggetto {@link KMeansMiner}.
     */
    public ClusterSet getC(){
        return this.C;
    }

    /**
     * Esegue l'algoritmo K-Means sulla serie di dati fornita, assegnando ciascun esempio al cluster più vicino
     * e aggiornando iterativamente i centroidi dei cluster fino a quando non avviene alcun cambio di assegnazione.
     *
     * @param data La serie di dati su cui eseguire l'algoritmo K-Means.
     * @return Il numero di iterazioni necessarie per convergere durante l'esecuzione di K-Means.
     * @throws OutOfRangeSampleSize Se il numero di cluster è inferiore a 1 o maggiore del numero di esempi nei dati.
     */
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

    /**
     * Salva l'insieme di cluster associato all'oggetto {@link KMeansMiner} su un file specificato.
     *
     * @param fileName Il percorso del file in cui salvare l'insieme di cluster.
     * @throws FileNotFoundException Se il file specificato non è trovato.
     * @throws IOException           Se si verifica un errore durante l'operazione di I/O durante la scrittura del file.
     */
    public void salva(String fileName) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

        objectOutputStream.writeObject(this.C);
        objectOutputStream.close();
    }
}

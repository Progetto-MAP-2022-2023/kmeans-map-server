package mining;

import data.Data;
import data.Tuple;
import data.OutOfRangeSampleSize;

import java.io.Serializable;

/**
 * La classe {@code ClusterSet} rappresenta un insieme di cluster. È serializzabile,
 * il che consente di salvare e ripristinare l'oggetto tramite operazioni di I/O.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class ClusterSet implements Serializable {
    /**
     * Un array di oggetti {@link Cluster} che costituiscono l'insieme di cluster.
     */
    private Cluster[] C;
    /**
     * Un contatore utilizzato per tenere traccia del numero di cluster presenti nell'insieme.
     */
    private int i = 0;

    /**
     * Costruisce un nuovo oggetto {@code ClusterSet} con un numero specificato di cluster {@code k}.
     *
     * @param k Il numero di cluster da inizializzare.
     * @throws OutOfRangeSampleSize Se il numero di cluster specificato è inferiore o uguale a 1.
     */
    ClusterSet(int k) throws OutOfRangeSampleSize{
        if(k <= 1){
            throw new OutOfRangeSampleSize("Number of cluster must be greater than 1.", new UnsupportedOperationException());
        }
        C = new Cluster[k];
    }

    /**
     * Aggiunge il cluster specificato all'insieme di cluster.
     *
     * @param c Il cluster da aggiungere.
     */
    private void add(Cluster c){
        C[i] = c;
        i += 1;
    }

    /**
     * Restituisce il cluster situato nella posizione specificata nell'insieme di cluster.
     *
     * @param i L'indice del cluster da restituire.
     * @return Il cluster situato nella posizione specificata.
     */
    Cluster get(int i){
        return C[i];
    }

    /**
     * Inizializza i centroidi dei cluster selezionando casualmente un sottoinsieme di esempi dai dati forniti.
     * Il numero di centroidi selezionati corrisponde alla dimensione dell'insieme di cluster.
     *
     * @param data La serie di dati da cui selezionare i centroidi.
     * @throws OutOfRangeSampleSize Se la dimensione dell'insieme di cluster è inferiore a 1 o maggiore del numero di esempi nei dati.
     */
    void initializeCentroids(Data data) throws OutOfRangeSampleSize{
        int[] centroidIndexes = data.sampling(C.length);
        for(int i = 0; i<centroidIndexes.length; i++) {
            Tuple centroidI = data.getItemSet(centroidIndexes[i]);
            this.add(new Cluster(centroidI));
        }
    }

    /**
     * Restituisce il cluster più vicino al dato punto rappresentato da un oggetto {@link Tuple}.
     *
     * @param tuple Il punto di riferimento per trovare il cluster più vicino.
     * @return Il cluster più vicino al punto specificato.
     */
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

    /**
     * Restituisce il cluster corrente a cui appartiene un dato elemento identificato da un ID.
     *
     * @param id L'ID dell'elemento per cui cercare il cluster corrente.
     * @return Il cluster corrente a cui appartiene l'elemento, o {@code null} se non è presente in alcun cluster.
     */
    Cluster currentCluster(int id){

        for(int i = 0; i < C.length; i++) {
            if (C[i].contain(id)) {
                return C[i];
            }
        }
        return null;
    }

    /**
     * Aggiorna i centroidi di tutti i cluster nell'insieme utilizzando i dati forniti.
     *
     * @param data La serie di dati utilizzata per calcolare i nuovi centroidi dei cluster.
     */
    void updateCentroids(Data data){

        for(int i = 0; i < C.length; i++){
            C[i].computeCentroid(data);
        }
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'insieme di cluster.
     *
     * @return Una stringa che rappresenta l'insieme di cluster, con ogni cluster numerato e seguito dalla sua rappresentazione.
     */
    @Override
    public String toString(){
        String s = "";

        for(int i = 0; i < C.length; i++){
            s += i + " " + C[i].toString();
        }

        return s;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'insieme di cluster, utilizzando i dati forniti.
     *
     * @param data La serie di dati utilizzata per ottenere la rappresentazione dei cluster.
     * @return Una stringa che rappresenta l'insieme di cluster, con ogni cluster numerato e seguito dalla sua rappresentazione basata sui dati forniti.
     */
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

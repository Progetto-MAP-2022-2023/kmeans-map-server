package mining;

import data.Data;
import data.Tuple;
import java.util.Arrays;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe {@code Cluster} rappresenta un cluster di dati con un centroide associato.
 * È serializzabile per consentire operazioni di I/O.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
class Cluster implements Serializable {
	/**
	 * Il centroide del cluster, rappresentato da un oggetto {@code Tuple}.
	 */
	private Tuple centroid;
	/**
	 * Un insieme di ID che identificano gli elementi attualmente assegnati a questo cluster.
	 */
	private Set<Integer> clusteredData;

	/**
	 * Costruisce un nuovo oggetto {@code Cluster} con il centroide specificato.
	 *
	 * @param centroid Il centroide del cluster.
	 */
	Cluster(Tuple centroid){
		this.centroid = centroid;
		clusteredData = new HashSet<Integer>();
	}

	/**
	 * Restituisce il centroide del cluster.
	 *
	 * @return Il centroide del cluster.
	 */
	Tuple getCentroid(){
		return centroid;
	}

	/**
	 * Calcola il nuovo centroide del cluster utilizzando i dati forniti.
	 *
	 * @param data La serie di dati utilizzata per calcolare il nuovo centroide.
	 */
	void computeCentroid(Data data){
		for(int i = 0; i<centroid.getLength(); i++){
			centroid.get(i).update(data,clusteredData);
		}
	}

	/**
	 * Aggiunge un elemento identificato da un ID al cluster.
	 *
	 * @param id L'ID dell'elemento da aggiungere al cluster.
	 * @return {@code true} se l'elemento cambia cluster, altrimenti {@code false}.
	 */
	boolean addData(int id){
		return clusteredData.add(id);
		
	}

	/**
	 * Verifica se un elemento identificato da un ID è presente nel cluster.
	 *
	 * @param id L'ID dell'elemento da cercare nel cluster.
	 * @return {@code true} se l'elemento è presente nel cluster, altrimenti {@code false}.
	 */
	boolean contain(int id){
		return clusteredData.contains(id);
	}


	/**
	 * Rimuove un elemento identificato da un ID dal cluster.
	 *
	 * @param id L'ID dell'elemento da rimuovere dal cluster.
	 */
	void removeTuple(int id){
		clusteredData.remove(id);
	}

	/**
	 * Restituisce una rappresentazione in formato stringa del cluster.
	 *
	 * @return Una stringa che rappresenta il cluster con il suo centroide.
	 */
	@Override
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+= centroid.get(i) + " ";
		str+=")";
		return str;

	}

	/**
	 * Restituisce una rappresentazione dettagliata in formato stringa del cluster, utilizzando i dati forniti.
	 *
	 * @param data La serie di dati utilizzata per ottenere la rappresentazione dettagliata del cluster.
	 * @return Una stringa dettagliata che rappresenta il cluster, inclusi gli esempi e la distanza media.
	 */
	String toString(Data data){

		String str = "Centroid=(";
		for(int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i) + " ";
		str += ")\nExamples:\n";

		int[] intArray = Arrays.stream(clusteredData.toArray()).mapToInt( x-> (int) x).toArray();

		for(int i = 0 ; i < intArray.length; i++){
			str += "[" ;
			for(int j = 0; j < data.getNumberOfAttributes(); j++)
				str += data.getAttributeValue(intArray[i], j) + " ";
			str += "] dist=" + getCentroid().getDistance(data.getItemSet(intArray[i])) + "\n";
			
		}
		str += "AvgDistance=" + getCentroid().avgDistance(data, intArray) + "\n";

		return str;
	}
}

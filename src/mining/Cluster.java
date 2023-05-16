package mining;

import data.Data;
import data.Tuple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Cluster {
	private Tuple centroid;

	private Set<Integer> clusteredData;
	
	/*mining.Cluster(){
		
	}*/

	Cluster(Tuple centroid){
		this.centroid = centroid;
		clusteredData = new HashSet<Integer>();
	}
		
	Tuple getCentroid(){
		return centroid;
	}
	
	void computeCentroid(Data data){
		for(int i = 0; i<centroid.getLength(); i++){
			centroid.get(i).update(data,clusteredData);
			
		}
		
	}
	//return true if the tuple is changing cluster
	boolean addData(int id){
		return clusteredData.add(id);
		
	}
	
	//verifica se una transazione � clusterizzata nell'array corrente
	boolean contain(int id){
		return clusteredData.contains(id);
	}
	

	//remove the tuplethat has changed the cluster
	void removeTuple(int id){
		clusteredData.remove(id);
	}

	@Override
	public String toString(){

		String str="Centroid=(";
		for(Integer iterClusteredData : clusteredData){
			str += iterClusteredData;
		}
		str += ")";

		return str;
	}
	

	
	String toString(Data data){

		String str = "Centroid=(";
		for(int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i) + " ";
		str += ")\nExamples:\n";

		//Object[] array = clusteredData.toArray();

		/*
		Integer[] array = new Integer[clusteredData.size()];
		System.arraycopy(clusteredData.toArray(), 0, array, 0, clusteredData.size());
		*/

		int[] array = new int[clusteredData.size()];
		int indexArray = 0;
		for(Integer tempInt : clusteredData){
			array[indexArray] = tempInt;
			indexArray += 1;
		}

		for(int i = 0 ; i < array.length; i++){
			str += "[" ;
			for(int j = 0; j < data.getNumberOfAttributes(); j++)
				str += data.getAttributeValue(array[i], j) + " ";
			str += "] dist=" + getCentroid().getDistance(data.getItemSet(array[i])) + "\n";
			
		}
		str += "AvgDistance=" + getCentroid().avgDistance(data, array) + "\n";

		return str;
	}

}

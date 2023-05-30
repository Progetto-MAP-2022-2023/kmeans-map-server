package data;

import database.*;
import java.sql.SQLException;
import java.util.*;

/**
 * The Data class models a dataset, made up of a set of tuples that value various attributes, described within the class.
 *
 * @author Antonio Catanzaro (GitHub: KoteiMadoshi)
 * @author Daniele Grandolfo
 * @author Rosanna Fracchiola
 */
public class Data {

	/**
	 * List of object of class <code>Example</code>, contains the various values assumed by the tuples for each attribute present in the dataset used.
	 */
	private List<Example> data;
	/**
	 * Number of tuples present.
	 */
	private int numberOfExamples;
	/**
	 * List describing the attributes of the dataset.
	 */
	private List<Attribute> attributeSet;

	/**
	 * Default constructor; initialize:
	 * <ul>
	 *     <li>
	 *         the list <a href="#data" class="member-name-link"><code>data</code></a> with a certain number of values;
	 *     </li>
	 *     <li>
	 *         <a href="#numberOfExamples" class="member-name-link"><code>numberOfExamples</code></a> with the number of tuples;
	 *     </li>
	 *     <li>
	 *         <a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a> with the number of attributes, their name and the possible values that each attribute can assume;
	 *     </li>
	 * </ul>
	 */
	public Data(){

		DbAccess dbAccess = new DbAccess();
		try{
			dbAccess.initConnection();
		}catch(DatabaseConnectionException e){
			System.out.println("Error on connection to db: " + e.getMessage());
		}

		TableData table = new TableData(dbAccess);
		String tableName = "playtennis";
		try{
			data = table.getDistinctTransazioni(tableName);
		}catch(SQLException | EmptySetException e){
			System.out.println("Error: " + e.getMessage());
		}

		//Inizializza il numero di tuple presenti
		numberOfExamples = data.size();

		try{
			TableSchema schema = new TableSchema(dbAccess, tableName);
			this.attributeSet = new LinkedList<Attribute>();

			for(int i = 0; i < schema.getNumberOfAttributes(); i++){
				if(!schema.getColumn(i).isNumber()){
					Object[] tableResult = table.getDistinctColumnValues(tableName, schema.getColumn(i)).toArray();
					int sizeOfDistinctTuple = tableResult.length;
					String[] distinctValues = new String[sizeOfDistinctTuple];
					System.arraycopy(tableResult, 0, distinctValues, 0, sizeOfDistinctTuple);
					this.attributeSet.add(new DiscreteAttribute(schema.getColumn(i).getColumnName(), i, distinctValues));
				}else{
					try{
						QUERY_TYPE aggregateMin = QUERY_TYPE.MIN;
						double min = (Double)table.getAggregateColumnValue(tableName, schema.getColumn(i), aggregateMin);
						QUERY_TYPE aggregateMax = QUERY_TYPE.MAX;
						double max = (Double)table.getAggregateColumnValue(tableName, schema.getColumn(i), aggregateMax);
						this.attributeSet.add(new ContinuousAttribute(schema.getColumn(i).getColumnName(), i, min, max));
					}catch(SQLException | NoValueException e){
						System.out.println("Error: " + e.getMessage());
					}
				}
			}
		}catch(SQLException e){
			System.out.println("Error during creation of table schema: " + e.getMessage());
		}

		dbAccess.closeConnection();
	}

	/**
	 * Method that returns the number of tuples in the dataset.
	 * @return the value of <a href="#numberOfExamples" class="member-name-link"><code>numberOfExamples</code></a>.
	 */
	public int getNumberOfExamples(){
		return numberOfExamples;
	}

	/**
	 * Method that returns the number of attributes that compose the dataset.
	 * @return the length of <a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a>.
	 */
	public int getNumberOfAttributes(){
		return attributeSet.size();
	}

	/**
	 * Method that returns the value of an attribute of a specific tuple.
	 * @param exampleIndex value of the row where the tuple to get the value from is located.
	 * @param attributeIndex index of the attribute you want to query.
	 * @return an <code>Object</code> containing the value of the tuple in row <code>exampleIndex</code> and column <code>attributeIndex</code>
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data.get(exampleIndex).get(attributeIndex);
	}

	/**
	 * Method that returns an object of class <code>Attribute</code> that describes an attribute with name, index and possible values that it can assume.
	 * @param index index representing the location where the attribute was saved in <a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a>
	 * @return an object of class <code>Attribute</code>.
	 */
	Attribute getAttribute(int index){
		return this.attributeSet.get(index);
	}

	/**
	 * Method that returns a string consisting of:
	 * <ul>
	 *     <li>the first row with the names of the attributes;</li>
	 *     <li>all the tuples present in the dataset, in columns and with the values of each attribute separated by a comma.</li>
	 * </ul>
	 * @return a string describing the dataset.
	 */
	public String toString(){
		String stringOutput = "";
		int counter = 0;

		for(Attribute attr : attributeSet){
			stringOutput += attr.getName() + ", ";
		}
		stringOutput += "\n";

		for(Example e : data){
			stringOutput += counter+1 + ": ";
			for(int j = 0; j < attributeSet.size(); j++){
				stringOutput += e.get(j) +",";
			}
			stringOutput += "\n";
			counter += 1;
		}

		return stringOutput;

	}

	/**
	 * Method that creates and returns an object of class <code>Tuple</code> that models as a sequence of Attribute-value pairs the row in <a href="#data" class="member-name-link"><code>data</code></a> in position i.
	 * @param index the value of the row to get the values from.
	 * @return an object of class <code>Tuple</code>
	 */
	public Tuple getItemSet(int index){
		Tuple tuple = new Tuple(attributeSet.size());

		for(Attribute iterAttr : attributeSet) {
			if(iterAttr instanceof DiscreteAttribute){
				tuple.add(new DiscreteItem((DiscreteAttribute)iterAttr, (String)data.get(index).get(iterAttr.getIndex())), iterAttr.getIndex());
			} else if(iterAttr instanceof ContinuousAttribute){
				tuple.add(new ContinuousItem((ContinuousAttribute)iterAttr, (Double)data.get(index).get(iterAttr.getIndex())), iterAttr.getIndex());
			}

		}

		return tuple;
	}

	/**
	 * Method that returns an array of different positions, representing the rows in <a href="#data" class="member-name-link"><code>data</code></a> used as centroids for the first step of the k-means.
	 * <br>First of all, the method checks if the number of centroids to be taken as initial seeds for the clusters is less than or equal to the number of distinct tuples.
	 * <br>If this condition is not met, the method throws the "<a href="OutOfRangeSampleSize.html"><code>OutOfRangeSampleSize</code></a>" exception. If this condition is satisfied, the algorithm proceeds.
	 * <br>For k times, a value ranging from 0 to <a href="#numberOfExamples" class="member-name-link"><code>numberOfExamples</code></a> - 1 is randomly chosen, and before inserting it into the array that will be returned at the end of the execution,
	 * <br>it is checked whether the chosen index has already been inserted.
	 * If it has, another index is chosen.
	 *
	 * @param k number of positions to return. It must be less than or equal to the number of distinct tuples.
	 * @return an array of k integer values, different from each other.
	 * @throws OutOfRangeSampleSize
	 */
	public int[] sampling(int k) throws OutOfRangeSampleSize{
		if(k > this.numberOfExamples){
			throw new OutOfRangeSampleSize("Number of cluster is greater than " + this.numberOfExamples, new UnsupportedOperationException());
		}
		int[] centroidIndexes = new int[k];
		//choose k random different centroids in data.
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for(int i = 0 ; i < k; i++){
			boolean found = false;
			int c;
			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroid already stored in CentroidIndexes
				for(int j = 0; j < i; j++)
					if(compare(centroidIndexes[j],c)){
						found = true;
						break;
					}
			}
			while(found);
			centroidIndexes[i] = c;
		}
		return centroidIndexes;
	}

	/**
	 * Method that checks if two examples in <a href="#data" class="member-name-link"><code>data</code></a> are equal.
	 * @param i Index of the first row to compare.
	 * @param j Index of the second row to check.
	 * @return True if the two rows have the same values for each attribute, false otherwise.
	 */
	private boolean compare(int i,int j){
		//Se le righe passate sono le stesse ritorna vero (tupla da controllare con se stessa)
		if(i == j || data.get(i).compareTo(data.get(j)) == 0){
			return true;
		}
		return false;
	}

	/**
	 * Method that returns the result of the private method <a href="#computePrototype(utility.ArraySet,data.DiscreteAttribute)" class="member-name-link">computePrototype</a>(ArraySet idList, DiscreteAttribute attribute).
	 * @param idList Set of row indices on which to calculate the centroid.
	 * @param attribute Attribute on which to calculate the prototype (centroid).
	 * @return an object of the Object class, representing the centroid value with respect to the attribute.
	 */
	Object computePrototype(Set<Integer> idList, Attribute attribute){
		if(attribute instanceof DiscreteAttribute){
			return computePrototype(idList, (DiscreteAttribute)attribute);
		}else {
			return computePrototype(idList, (ContinuousAttribute)attribute);
		}
	}

	/**
	 * Method that determines the most frequently occurring value for the attribute in the subset of data identified by idList.
	 * To do this, it uses the <a href="DiscreteAttribute.html#frequency(data.Data,utility.ArraySet,java.lang.String)" class="member-name-link">frequency</a>
	 * method of the <a href="DiscreteAttribute.html"><code>DiscreteAttribute</code></a>
	 * class, calculating the highest occurrence for each value of the specified attribute.
	 * @param idList Set of row indices of <a href="#data" class="member-name-link"><code>data</code></a> belonging to a cluster.
	 * @param attribute Discrete attribute according to which to calculate the prototype (centroid).
	 * @return an object of the String class, representing the centroid with respect to attribute.
	 */
	private String computePrototype(Set<Integer> idList, DiscreteAttribute attribute){
		int maxOccurrence = -1;
		int tempOccurrence;
		String stringToReturn = "";

		for(String tempString : attribute){
			tempOccurrence = attribute.frequency(this, idList, tempString);
			if(tempOccurrence > maxOccurrence){
				maxOccurrence = tempOccurrence;
				stringToReturn = tempString;
			}
		}

		return stringToReturn;
	}

	private Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute){
		double avgValue = 0.0;

		for(Integer iterIdList : idList){
			avgValue += (Double)(data.get(iterIdList).get(attribute.getIndex()));
		}
		avgValue /= idList.size();

		return avgValue;
	}
}

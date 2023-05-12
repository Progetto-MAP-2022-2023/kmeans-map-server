package data;

import utility.ArraySet;
import java.util.*;

/**
 * The Data class models a dataset, made up of a set of tuples that value various attributes, described within the class.
 *
 * @author Antonio Catanzaro (GitHub: KoteiMadoshi)
 * @author Daniele Grandolfo
 * @author Rosanna Fracchiola
 */
public class Data {

	class Example implements Comparable<Example> {

		private List<Object> example =  new ArrayList<Object>();

		void add(Object o){
			try {
				example.add(o);
			} catch (UnsupportedOperationException | ClassCastException | NullPointerException |
					 IllegalArgumentException e){
				System.out.println("Error: " + e.getMessage());
			}
		}

		public Object get(int i){
			return example.get(i);
		}

		public int compareTo(Example ex){
			Iterator<Object> thisIterator = this.example.iterator();
			Iterator<Object> exIterator = ex.example.iterator();
			String thisString;
			String exString;
			do{
				thisString = (String)thisIterator.next();
				exString = (String)exIterator.next();
				if(!thisString.equals(exString)){
					if(thisString.compareTo(exString) > 0){
						return 1;
					}else{
						return -1;
					}
				}
			}while(thisIterator.hasNext());
			return 0;
		}

		@Override
		public String toString(){
			String stringToReturn = "";
			for(Object obj: this.example){
				stringToReturn += (String)obj;
			}
			return stringToReturn;
		}
	}

	/**
	 * Two-Dimensional Array of objects of class <code>Object</code>, contains the various values assumed by the tuples for each attribute present in the dataset used.
	 */
	private List<Example> data;
	/**
	 * Number of tuples present.
	 */
	private int numberOfExamples;
	/**
	 * Array describing the attributes of the dataset.
	 */
	private List<Attribute> attributeSet;
	/**
	 * Number of distinct tuples within the dataset.
	 */
	private int distinctTuples;

	/**
	 * Default constructor; initialize:
	 * <ul>
	 *     <li>the Two-Dimensional Array <a href="#data" class="member-name-link"><code>data</code></a> with a certain number of values;</li>
	 *     <li><a href="#numberOfExamples" class="member-name-link"><code>numberOfExamples</code></a> with the number of tuples;</li>
	 *     <li><a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a> with the number of attributes, their name and the possible values that each attribute can assume;</li>
	 *     <li><a href="#distinctTuples" class="member-name-link"><code>distinctTuples</code></a> through the <a href="#countDistinctTuples()" class="member-name-link"><code>countDistinctTuples</code></a> method.</li>
	 * </ul>
	 */
	public Data(){


		//Inizializza la matrice data con i vari valori del dataset
		TreeSet<Example> tempSet = new TreeSet<Example>();

		Example ex0 = new Example();
		Example ex1 = new Example();
		Example ex2 = new Example();
		Example ex3 = new Example();
		Example ex4 = new Example();
		Example ex5 = new Example();
		Example ex6 = new Example();
		Example ex7 = new Example();
		Example ex8 = new Example();
		Example ex9 = new Example();
		Example ex10 = new Example();
		Example ex11 = new Example();
		Example ex12 = new Example();
		Example ex13 = new Example();

		ex0.add(new String ("sunny"));
		ex1.add(new String ("sunny"));
		ex2.add(new String ("overcast"));
		ex3.add(new String ("rain"));
		ex4.add(new String ("rain"));
		ex5.add(new String ("rain"));
		ex6.add(new String ("overcast"));
		ex7.add(new String ("sunny"));
		ex8.add(new String ("sunny"));
		ex9.add(new String ("rain"));
		ex10.add(new String ("sunny"));
		ex11.add(new String ("overcast"));
		ex12.add(new String ("overcast"));
		ex13.add(new String ("rain"));

		ex0.add(new String ("hot"));
		ex1.add(new String ("hot"));
		ex2.add(new String ("hot"));
		ex3.add(new String ("mild"));
		ex4.add(new String ("cool"));
		ex5.add(new String ("cool"));
		ex6.add(new String ("cool"));
		ex7.add(new String ("mild"));
		ex8.add(new String ("cool"));
		ex9.add(new String ("mild"));
		ex10.add(new String ("mild"));
		ex11.add(new String ("mild"));
		ex12.add(new String ("hot"));
		ex13.add(new String ("mild"));

		ex0.add(new String ("high"));
		ex1.add(new String ("high"));
		ex2.add(new String ("high"));
		ex3.add(new String ("high"));
		ex4.add(new String ("normal"));
		ex5.add(new String ("normal"));
		ex6.add(new String ("normal"));
		ex7.add(new String ("high"));
		ex8.add(new String ("normal"));
		ex9.add(new String ("normal"));
		ex10.add(new String ("normal"));
		ex11.add(new String ("high"));
		ex12.add(new String ("normal"));
		ex13.add(new String ("high"));

		ex0.add(new String ("weak"));
		ex1.add(new String ("strong"));
		ex2.add(new String ("weak"));
		ex3.add(new String ("weak"));
		ex4.add(new String ("weak"));
		ex5.add(new String ("strong"));
		ex6.add(new String ("strong"));
		ex7.add(new String ("weak"));
		ex8.add(new String ("weak"));
		ex9.add(new String ("weak"));
		ex10.add(new String ("strong"));
		ex11.add(new String ("strong"));
		ex12.add(new String ("weak"));
		ex13.add(new String ("strong"));

		ex0.add(new String ("no"));
		ex1.add(new String ("no"));
		ex2.add(new String ("yes"));
		ex3.add(new String ("yes"));
		ex4.add(new String ("yes"));
		ex5.add(new String ("no"));
		ex6.add(new String ("yes"));
		ex7.add(new String ("no"));
		ex8.add(new String ("yes"));
		ex9.add(new String ("yes"));
		ex10.add(new String ("yes"));
		ex11.add(new String ("yes"));
		ex12.add(new String ("yes"));
		ex13.add(new String ("no"));

		tempSet.add(ex0);
		tempSet.add(ex1);
		tempSet.add(ex2);
		tempSet.add(ex3);
		tempSet.add(ex4);
		tempSet.add(ex5);
		tempSet.add(ex6);
		tempSet.add(ex7);
		tempSet.add(ex8);
		tempSet.add(ex9);
		tempSet.add(ex10);
		tempSet.add(ex11);
		tempSet.add(ex12);
		tempSet.add(ex13);

		data = new ArrayList<Example>(tempSet);

		//Inizializza il numero di tuple presenti
		numberOfExamples = data.size();

		//Inizializzazione del set di attributi
		attributeSet = new LinkedList<Attribute>();

		//Outlook
		String outLookValues[]=new String[3];
		outLookValues[0]="overcast";
		outLookValues[1]="rain";
		outLookValues[2]="sunny";
		attributeSet.add(new DiscreteAttribute("Outlook",0, outLookValues));

		//Temperature
		String temperatureValues[] = new String[3];
		temperatureValues[0]="hot";
		temperatureValues[1]="mild";
		temperatureValues[2]="cool";
		attributeSet.add(new DiscreteAttribute("Temperature", 1, temperatureValues));

		//Humidity
		String humidityValues[] = new String[2];
		humidityValues[0]="high";
		humidityValues[1]="normal";
		attributeSet.add(new DiscreteAttribute("Humidity", 2, humidityValues));

		//Wind
		String windValues[] = new String[2];
		windValues[0]="weak";
		windValues[1]="strong";
		attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));

		//PlayTennis
		String playTennisValues[] = new String[2];
		playTennisValues[0]="yes";
		playTennisValues[1]="no";
		attributeSet.add(new DiscreteAttribute("PlayTennis", 4, playTennisValues));
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

		for(Attribute attr : attributeSet){
			stringOutput += attr.getName() + ", ";
		}
		stringOutput += "\n";

		for(int i = 0; i < numberOfExamples; i++){
			stringOutput += i+1 + ": ";
			for(int j = 0; j < attributeSet.size(); j++){
				stringOutput += data.get(i).get(j) +",";
			}
			stringOutput += "\n";
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
		for(Attribute interAttr:attributeSet) {
			tuple.add(new DiscreteItem((DiscreteAttribute)interAttr, (String)data.get(index).get(interAttr.getIndex())), interAttr.getIndex());
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
		int[] centroidIndexes=new int[k];
		//choose k random different centroids in data.
		Random rand=new Random();
		rand.setSeed(System.currentTimeMillis());
		for(int i=0; i<k; i++){
			boolean found = false;
			int c;
			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroid already stored in CentroidIndexes
				for(int j=0;j<i;j++)
					if(compare(centroidIndexes[j],c)){
						found=true;
						break;
					}
			}
			while(found);
			centroidIndexes[i]=c;
		}
		return centroidIndexes;
	}

	/**
	 * Method that checks if two tuples in <a href="#data" class="member-name-link"><code>data</code></a> are equal.
	 * To do this, it checks if the values in row i and row j are equal for each attribute.
	 * @param i Index of the first row to compare.
	 * @param j Index of the second row to check.
	 * @return True if the two rows have the same values for each attribute, false otherwise.
	 */
	private boolean compare(int i,int j){
		//Se le righe passate sono le stesse ritorna vero (tupla da controllare con se stessa)
		if(i == j){
			return true;
		}
		//Controlla se ogni colonna è uguale per entrambe le righe
		for(int k = 0; k < this.getNumberOfAttributes(); k++){
			//Se trova una disuguaglianza ritorna falso
			if(!((String)data.get(i).get(k)).equals((String)data.get(j).get(k))){
				return false;
			}
		}
		return true;
	}

	/**
	 * Method that returns the result of the private method <a href="#computePrototype(utility.ArraySet,data.DiscreteAttribute)" class="member-name-link">computePrototype</a>(ArraySet idList, DiscreteAttribute attribute).
	 * @param idList Set of row indices on which to calculate the centroid.
	 * @param attribute Attribute on which to calculate the prototype (centroid).
	 * @return an object of the Object class, representing the centroid value with respect to the attribute.
	 */
	Object computePrototype(ArraySet idList, Attribute attribute){
		return (Object)computePrototype(idList, (DiscreteAttribute)attribute);
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
	private String computePrototype(ArraySet idList, DiscreteAttribute attribute){
		int maxOccurrence;
		int tempOccurrence;
		String tempString;
		String stringToReturn;

		Iterator<String> i = attribute.iterator();
		tempString = i.next();
		maxOccurrence = attribute.frequency(this, idList, tempString);
		stringToReturn = tempString;
		while(i.hasNext()){
			tempString = i.next();
			tempOccurrence = attribute.frequency(this, idList, tempString);
			if(tempOccurrence > maxOccurrence){
				stringToReturn = tempString;
			}
		}
		return stringToReturn;
	}

}

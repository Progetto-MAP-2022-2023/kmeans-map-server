package data;

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
	 * The inner class Example models a transaction.
	 */
	class Example implements Comparable<Example> {

		/**
		 * List of attribute values belonging to each example.
		 */
		private List<Object> example =  new ArrayList<>();

		/**
		 * Method that adds a value to the element.
		 * @param o Value to add to the example.
		 */
		void add(Object o){
			try {
				example.add(o);
			} catch (UnsupportedOperationException | ClassCastException | NullPointerException |
					 IllegalArgumentException e){
				System.out.println("Error: " + e.getMessage());
			}
		}

		/**
		 * Method that returns the value of an attribute in the example.
		 * @param i Index of the attribute to query.
		 * @return The value of the attribute at index i.
		 */
		public Object get(int i){
			return example.get(i);
		}

		/**
		 * Method that compares two elements of type Example. To do so, all values for each attribute of both Example's object are pairwise compared.
		 * If they are different, the method returns two values based on the result of the compareTo operation performed on the values.
		 * @param ex the object to be compared.
		 * @return <ul>
		 *     <li>
		 *         0 if the examples are equals;
		 *     </li>
		 *     <li>
		 *         1 if two values of the examples are different and the result of their compareTo operation is greater than 0.
		 *     </li>
		 *     <li>
		 *         -1 if two values of the examples are different and the result of their compareTo operation is lower than 0.
		 *     </li>
		 * </ul>
		 */
		public int compareTo(Example ex){
			Iterator<Object> exIterator = ex.example.iterator();
			String thisString;
			String exString;
			Double thisDouble;
			Double exDouble;

			for(Object thisIterator : this.example){
				if(thisIterator instanceof String){
					thisString = (String)thisIterator;
					exString = (String)exIterator.next();
					if(!thisString.equals(exString)){
						return  (thisString.compareTo(exString) > 0) ?  1 : -1;
					}
				} else if(thisIterator instanceof Double){
					thisDouble = (Double)thisIterator;
					exDouble = (Double)exIterator.next();
					if(!thisDouble.equals(exDouble)){
						return  (thisDouble.compareTo(exDouble) > 0) ?  1 : -1;
					}
				}
			}
			return 0;

		}

		/**
		 * Method that returns a string containing the values for each attribute of the example.
		 * @return a string that describes the example and its values.
		 */
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

		ex0.add(Double.valueOf("37.5"));
		ex1.add(Double.valueOf("38.7"));
		ex2.add(Double.valueOf("37.5"));
		ex3.add(Double.valueOf("20.5"));
		ex4.add(Double.valueOf("20.7"));
		ex5.add(Double.valueOf("21.2"));
		ex6.add(Double.valueOf("20.5"));
		ex7.add(Double.valueOf("21.2"));
		ex8.add(Double.valueOf("21.2"));
		ex9.add(Double.valueOf("19.8"));
		ex10.add(Double.valueOf("3.5"));
		ex11.add(Double.valueOf("3.6"));
		ex12.add(Double.valueOf("3.5"));
		ex13.add(Double.valueOf("3.2"));

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
		attributeSet.add(new ContinuousAttribute("Temperature",1, 3.2, 38.7));

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

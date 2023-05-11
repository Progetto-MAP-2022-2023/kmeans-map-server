package data;

import utility.ArraySet;
import java.util.Random;

/**
 * The Data class models a dataset, made up of a set of tuples that value various attributes, described within the class.
 *
 * @author Antonio Catanzaro (GitHub: KoteiMadoshi)
 * @author Daniele Grandolfo
 * @author Rosanna Fracchiola
 */
public class Data {
	/**
	 * Two-Dimensional Array of objects of class <code>Object</code>, contains the various values assumed by the tuples for each attribute present in the dataset used.
	 */
	private Object data[][];
	/**
	 * Number of tuples present.
	 */
	private int numberOfExamples;
	/**
	 * Array describing the attributes of the dataset.
	 */
	private Attribute attributeSet[];
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
		data = new Object [14][5];

		data[0][0]=new String ("sunny");
		data[1][0]=new String ("sunny");
		data[2][0]=new String ("overcast");
		data[3][0]=new String ("rain");
		data[4][0]=new String ("rain");
		data[5][0]=new String ("rain");
		data[6][0]=new String ("overcast");
		data[7][0]=new String ("sunny");
		data[8][0]=new String ("sunny");
		data[9][0]=new String ("rain");
		data[10][0]=new String ("sunny");
		data[11][0]=new String ("overcast");
		data[12][0]=new String ("overcast");
		data[13][0]=new String ("rain");

		data[0][1]=new String ("hot");
		data[1][1]=new String ("hot");
		data[2][1]=new String ("hot");
		data[3][1]=new String ("mild");
		data[4][1]=new String ("cool");
		data[5][1]=new String ("cool");
		data[6][1]=new String ("cool");
		data[7][1]=new String ("mild");
		data[8][1]=new String ("cool");
		data[9][1]=new String ("mild");
		data[10][1]=new String ("mild");
		data[11][1]=new String ("mild");
		data[12][1]=new String ("hot");
		data[13][1]=new String ("mild");

		data[0][2]=new String ("high");
		data[1][2]=new String ("high");
		data[2][2]=new String ("high");
		data[3][2]=new String ("high");
		data[4][2]=new String ("normal");
		data[5][2]=new String ("normal");
		data[6][2]=new String ("normal");
		data[7][2]=new String ("high");
		data[8][2]=new String ("normal");
		data[9][2]=new String ("normal");
		data[10][2]=new String ("normal");
		data[11][2]=new String ("high");
		data[12][2]=new String ("normal");
		data[13][2]=new String ("high");

		data[0][3]=new String ("weak");
		data[1][3]=new String ("strong");
		data[2][3]=new String ("weak");
		data[3][3]=new String ("weak");
		data[4][3]=new String ("weak");
		data[5][3]=new String ("strong");
		data[6][3]=new String ("strong");
		data[7][3]=new String ("weak");
		data[8][3]=new String ("weak");
		data[9][3]=new String ("weak");
		data[10][3]=new String ("strong");
		data[11][3]=new String ("strong");
		data[12][3]=new String ("weak");
		data[13][3]=new String ("strong");

		data[0][4]=new String ("no");
		data[1][4]=new String ("no");
		data[2][4]=new String ("yes");
		data[3][4]=new String ("yes");
		data[4][4]=new String ("yes");
		data[5][4]=new String ("no");
		data[6][4]=new String ("yes");
		data[7][4]=new String ("no");
		data[8][4]=new String ("yes");
		data[9][4]=new String ("yes");
		data[10][4]=new String ("yes");
		data[11][4]=new String ("yes");
		data[12][4]=new String ("yes");
		data[13][4]=new String ("no");

		//Inizializza il numero di tuple presenti
		numberOfExamples = 14;

		//Inizializzazione del set di attributi
		attributeSet = new Attribute[5];

		//Outlook
		String outLookValues[]=new String[3];
		outLookValues[0]="overcast";
		outLookValues[1]="rain";
		outLookValues[2]="sunny";
		attributeSet[0] = new DiscreteAttribute("Outlook",0, outLookValues);

		//Temperature
		String temperatureValues[] = new String[3];
		temperatureValues[0]="hot";
		temperatureValues[1]="mild";
		temperatureValues[2]="cool";
		attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);

		//Humidity
		String humidityValues[] = new String[2];
		humidityValues[0]="high";
		humidityValues[1]="normal";
		attributeSet[2] = new DiscreteAttribute("Humidity", 2, humidityValues);

		//Wind
		String windValues[] = new String[2];
		windValues[0]="weak";
		windValues[1]="strong";
		attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);

		//PlayTennis
		String playTennisValues[] = new String[2];
		playTennisValues[0]="yes";
		playTennisValues[1]="no";
		attributeSet[4] = new DiscreteAttribute("PlayTennis", 4, playTennisValues);

		//Inizializza il numero delle tuple non ripetute
		distinctTuples = this.countDistinctTuples();
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
		return attributeSet.length;
	}

	/**
	 * Method that returns the schema of the dataset, through an array that describes the attributes in the form of name, index and values that each of them can assume.
	 * @return <a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a>.
	 */
	Attribute[] getAttributeSchema(){
		return attributeSet;
	}

	/**
	 * Method that returns the value of an attribute of a specific tuple.
	 * @param exampleIndex value of the row where the tuple to get the value from is located.
	 * @param attributeIndex index of the attribute you want to query.
	 * @return an <code>Object</code> containing the value of the tuple in row <code>exampleIndex</code> and column <code>attributeIndex</code>
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data[exampleIndex][attributeIndex];
	}

	/**
	 * Method that returns an object of class <code>Attribute</code> that describes an attribute with name, index and possible values that it can assume.
	 * @param index index representing the location where the attribute was saved in <a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a>
	 * @return an object of class <code>Attribute</code>.
	 */
	Attribute getAttribute(int index){
		return attributeSet[index];
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

		for(int i = 0; i < attributeSet.length; i++){
			stringOutput += attributeSet[i].getName() + ", ";
		}
		stringOutput += "\n";

		for(int i = 0; i < numberOfExamples; i++){
			stringOutput += i+1 + ": ";
			for(int j = 0; j < attributeSet.length; j++){
				stringOutput += data[i][j] +",";
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
		Tuple tuple = new Tuple(attributeSet.length);
		for(int i = 0; i < attributeSet.length; i++) {
			tuple.add(new DiscreteItem((DiscreteAttribute)attributeSet[i], (String)data[index][i]), i);
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
		if(k > this.distinctTuples){
			throw new OutOfRangeSampleSize("Number of cluster is greater than " + this.distinctTuples, new UnsupportedOperationException());
		}
		int[] centroidIndexes=new int[k];
		//choose k random different centroids in data.
		Random rand=new Random();
		rand.setSeed(System.currentTimeMillis());
		for(int i=0; i<k; i++){
			boolean found = false;
			int c;
			do
			{
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
			if(!((String)data[i][k]).equals((String)data[j][k])){
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
		int indexOfMaxOccurrency;
		int i = 0;

		maxOccurrence = attribute.frequency(this, idList, attribute.getValue(i));
		indexOfMaxOccurrency = i;
		for(i = 1; i < attribute.getNumberOfDistinctValues(); i++){
			tempOccurrence = attribute.frequency(this, idList, attribute.getValue(i));
			if(maxOccurrence < tempOccurrence){
				maxOccurrence = tempOccurrence;
				indexOfMaxOccurrency = i;
			}
		}
		return attribute.getValue(indexOfMaxOccurrency);
	}

	/**
	 * Method that returns the number of distinct tuples.
	 * <br>This method is private as it is only used to initialize the <a href="#distinctTuples" class="member-name-link"><code>distinctTuples</code></a> attribute.
	 * <br>To do this, an <code>ArraySet</code> of indices is created, which will be used to avoid checking positions already marked as duplicate tuples.
	 * The algorithm proceeds with the following operations:
	 * <ul>
	 *     <li>
	 *      	First, a tuple is checked. If it has not been previously inserted in the <code>ArraySet</code>,
	 *      	it means it is not a duplicate tuple, and the number of distinct tuples is incremented.
	 *     </li>
	 *     <li>
	 *     		Next, all subsequent tuples to the currently checked one are compared.
	 *     		If they are equal, they are marked in the <code>ArraySet</code> at the reference index.
	 *     </li>
	 *     <li>
	 *			After that, the operation is repeated for each tuple in the <a href="#data" class="member-name-link"><code>data</code></a>
	 *			except the last one to avoid an <code>ArrayIndexOutOfBoundsException</code> in the inner loop.
	 *     </li>
	 *     <li>
	 *         Finally, it is checked whether the index of the last row has not been previously added in the <code>ArraySet</code>,
	 *         indicating that the tuple is not signed as duplicated before, and therefore the number of distinct tuples is incremented.
	 *     </li>
	 * </ul>
	 * @return an int value indicating the number of distinct tuples contained in the <a href="#data" class="member-name-link"><code>data</code></a>.
	 */
	private int countDistinctTuples(){
		int numberOfDistinctTuples = 0;
		ArraySet countedElements = new ArraySet();

		for(int i = 0; i < this.numberOfExamples - 1; i++){
			if(!countedElements.get(i)){
				numberOfDistinctTuples += 1;
				countedElements.add(i);
				for(int j = i+1; j < this.numberOfExamples; j++){
					if(!countedElements.get(j)){
						if(this.compare(i, j)){
							countedElements.add(j);
						}
					}
				}
			}
		}
		if(!countedElements.get(this.numberOfExamples - 1)){
			numberOfDistinctTuples += 1;
		}

		return numberOfDistinctTuples;
	}


}

package data;

import database.*;
import java.sql.SQLException;
import java.util.*;

/**
 * Rappresenta un insieme di dati composto da tuple, ognuna rappresentata da un oggetto della classe {@link Example}.
 * Contiene i vari valori assunti dalle tuple per ciascun attributo presente nel dataset utilizzato.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class Data {

	/**
	 * Lista di oggetti di classe {@link Example}, contiene i vari valori assunti dalle tuple per ciascun attributo presente nel dataset utilizzato.
	 */
	private List<Example> data;
	/**
	 * Numero di tuple presenti nel dataset.
	 */
	private int numberOfExamples;
	/**
	 * Lista che descrive gli attributi del dataset, rappresentati da oggetti di classe {@link Attribute}.
	 */
	private List<Attribute> attributeSet;

	/**
	 * Costruttore della clase {@code Data}.
	 * Inizializza:
	 * <ul>
	 *     <li>
	 *         La lista di <a href="#data" class="member-name-link"><code>data</code></a> con un determinato numero di valori;
	 *     </li>
	 *     <li>
	 *         <a href="#numberOfExamples" class="member-name-link"><code>numberOfExamples</code></a> con il numero delle Tuple;
	 *     </li>
	 *     <li>
	 *         <a href="#attributeSet" class="member-name-link"><code>attributeSet</code></a> con il numero di attributi, il loro nome ed i possibili valori che ogni attributo può assumere;
	 *     </li>
	 * </ul>
	 */
	public Data(String tableName){

		DbAccess dbAccess = new DbAccess();
		try{
			dbAccess.initConnection();
		}catch(DatabaseConnectionException e){
			System.out.println("Error on connection to db: " + e.getMessage());
		}

		TableData table = new TableData(dbAccess);
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
	 * Restituisce il numero di tuple presenti nel dataset.
	 *
	 * @return il valore di {@link #numberOfExamples}.
	 */
	public int getNumberOfExamples(){
		return numberOfExamples;
	}

	/**
	 * Restituisce il numero di attributi che compongono il dataset.
	 *
	 * @return la lunghezza di {@link #attributeSet}.
	 */
	public int getNumberOfAttributes(){
		return attributeSet.size();
	}

	/**
	 * Restituisce il valore di un attributo di una specifica tupla.
	 *
	 * @param exampleIndex indice della riga in cui si trova la tupla da cui ottenere il valore.
	 * @param attributeIndex indice dell'attributo da cui si desidera fare la query.
	 * @return un oggetto {@code Object} contenente il valore della tupla nella riga {@code exampleIndex} e nella colonna {@code attributeIndex}.
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data.get(exampleIndex).get(attributeIndex);
	}

	/**
	 * Restituisce un oggetto di classe {@link Attribute} che descrive un attributo con nome, indice e valori possibili che può assumere.
	 *
	 * @param index indice che rappresenta la posizione in cui l'attributo è stato salvato in {@link #attributeSet}.
	 * @return un oggetto di classe {@link Attribute}.
	 */
	Attribute getAttribute(int index){
		return this.attributeSet.get(index);
	}

	/**
	 * Restituisce una stringa composta da:
	 * <ul>
	 *     <li>la prima riga con i nomi degli attributi;</li>
	 *     <li>tutte le tuple presenti nel dataset, in colonne e con i valori di ogni attributo separati da una virgola.</li>
	 * </ul>
	 *
	 * @return una stringa che descrive il dataset.
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
	 * Crea e restituisce un oggetto di classe {@link Tuple} che modella come sequenza di coppie Attributo-valore la riga in {@link #data} in posizione i.
	 *
	 * @param index il valore della riga da cui ottenere i valori.
	 * @return un oggetto di classe {@link Tuple}.
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
	 * Restituisce un array di diverse posizioni, rappresentanti le righe in {@link #data} utilizzate come centroidi per il primo passo del k-means.
	 * <br>Prima di tutto, il metodo verifica se il numero di centroidi da prendere come semi iniziali per i cluster è minore o uguale al numero di tuple distinte.
	 * <br>Se questa condizione non è soddisfatta, il metodo solleva l'eccezione "<a href="OutOfRangeSampleSize.html"><code>OutOfRangeSampleSize</code></a>". Se questa condizione è soddisfatta, l'algoritmo procede.
	 * <br>Per k volte, viene scelto casualmente un valore compreso tra 0 e {@link #numberOfExamples} - 1, e prima di inserirlo nell'array che verrà restituito alla fine dell'esecuzione,
	 * <br>viene verificato se l'indice scelto è già stato inserito.
	 * Se lo è, viene scelto un altro indice.
	 *
	 * @param k numero di posizioni da restituire. Deve essere minore o uguale al numero di tuple distinte.
	 * @return un array di k valori interi, diversi tra loro.
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
	 * Verifica se due esempi in {@link #data} sono uguali.
	 *
	 * @param i Indice della prima riga da confrontare.
	 * @param j Indice della seconda riga da controllare.
	 * @return True se le due righe hanno gli stessi valori per ogni attributo, false altrimenti.
	 */
	private boolean compare(int i,int j){
		//Se le righe passate sono le stesse ritorna vero (tupla da controllare con se stessa)
		if(i == j || data.get(i).compareTo(data.get(j)) == 0){
			return true;
		}
		return false;
	}

	/**
	 * Restituisce il risultato del metodo privato {@link #computePrototype(Set, Attribute)}.
	 *
	 * @param idList Set di indici di righe su cui calcolare il centroide.
	 * @param attribute Attributo su cui calcolare il prototipo (centroide).
	 * @return un oggetto della classe Object, rappresentante il valore del centroide rispetto all'attributo.
	 */
	Object computePrototype(Set<Integer> idList, Attribute attribute){
		if(attribute instanceof DiscreteAttribute){
			return computePrototype(idList, (DiscreteAttribute)attribute);
		}else {
			return computePrototype(idList, (ContinuousAttribute)attribute);
		}
	}

	/**
	 * Determina il valore che compare più frequentemente per l'attributo nel sottoinsieme di dati identificato da idList.
	 * Per fare ciò, utilizza il metodo {@link DiscreteAttribute#frequency(Data, Set, String)} della classe {@link DiscreteAttribute},
	 * calcolando l'occorrenza più alta per ciascun valore dell'attributo specificato.
	 *
	 * @param idList Set di indici di righe di {@link #data} appartenenti a un cluster.
	 * @param attribute Attributo discreto secondo cui calcolare il prototipo (centroide).
	 * @return un oggetto della classe String, rappresentante il centroide rispetto all'attributo.
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

	/**
	 * Calcola il prototipo (centroide) rispetto all'attributo continuo.
	 *
	 * @param idList Set di indici di righe di {@link #data} appartenenti a un cluster.
	 * @param attribute Attributo continuo secondo cui calcolare il prototipo (centroide).
	 * @return un oggetto della classe Double, rappresentante il valore medio rispetto all'attributo.
	 */
	private Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute){
		double avgValue = 0.0;

		for(Integer iterIdList : idList){
			avgValue += (Double)(data.get(iterIdList).get(attribute.getIndex()));
		}
		avgValue /= idList.size();

		return avgValue;
	}
}

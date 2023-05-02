import java.util.Random;

class Data {
// Le visibilità di classi , attributi e metodi devono essere decise dagli studenti	
	private Object data[][];
	private int numberOfExamples;
	private Attribute attributeSet[];
	
	
	Data(){
		
		data = new Object [14][5];

		data[0][0]=new String ("sunny");
		data[1][0]=new String ("sunny");
		data[2][0]=new String ("sunny");
		data[3][0]=new String ("rain");
		data[4][0]=new String ("rain");
		data[5][0]=new String ("rain");
		data[6][0]=new String ("rain");
		data[7][0]=new String ("rain");
		data[8][0]=new String ("rain");
		data[9][0]=new String ("rain");
		data[10][0]=new String ("overcast");
		data[11][0]=new String ("overcast");
		data[12][0]=new String ("overcast");
		data[13][0]=new String ("overcast");
		
		data[0][1]=new String ("hot");
		data[1][1]=new String ("hot");
		data[2][1]=new String ("hot");
		data[3][1]=new String ("mild");
		data[4][1]=new String ("mild");
		data[5][1]=new String ("mild");
		data[6][1]=new String ("mild");
		data[7][1]=new String ("mild");
		data[8][1]=new String ("mild");
		data[9][1]=new String ("mild");
		data[10][1]=new String ("cold");
		data[11][1]=new String ("cold");
		data[12][1]=new String ("cold");
		data[13][1]=new String ("cold");
		
		data[0][2]=new String ("high");
		data[1][2]=new String ("high");
		data[2][2]=new String ("high");
		data[3][2]=new String ("normal");
		data[4][2]=new String ("normal");
		data[5][2]=new String ("normal");
		data[6][2]=new String ("normal");
		data[7][2]=new String ("normal");
		data[8][2]=new String ("normal");
		data[9][2]=new String ("normal");
		data[10][2]=new String ("high");
		data[11][2]=new String ("high");
		data[12][2]=new String ("high");
		data[13][2]=new String ("high");
		
		
		data[0][3]=new String ("weak");
		data[1][3]=new String ("weak");
		data[2][3]=new String ("weak");
		data[3][3]=new String ("strong");
		data[4][3]=new String ("strong");
		data[5][3]=new String ("strong");
		data[6][3]=new String ("strong");
		data[7][3]=new String ("strong");
		data[8][3]=new String ("strong");
		data[9][3]=new String ("strong");
		data[10][3]=new String ("strong");
		data[11][3]=new String ("strong");
		data[12][3]=new String ("strong");
		data[13][3]=new String ("strong");
		

		data[0][4]=new String ("no");
		data[1][4]=new String ("no");
		data[2][4]=new String ("no");
		data[3][4]=new String ("yes");
		data[4][4]=new String ("yes");
		data[5][4]=new String ("yes");
		data[6][4]=new String ("yes");
		data[7][4]=new String ("yes");
		data[8][4]=new String ("yes");
		data[9][4]=new String ("yes");
		data[10][4]=new String ("yes");
		data[11][4]=new String ("yes");
		data[12][4]=new String ("yes");
		data[13][4]=new String ("yes");
		
		numberOfExamples = 14;
		 
		
		//explanatory Set
		
		attributeSet = new Attribute[5];

		// TO DO : avvalorare ciascune elemento di attributeSet con un oggetto della classe DiscreteAttribute che modella il corrispondente attributo (e.g. outlook, temperature,etc)
		// nel seguito si fornisce l'esempio per outlook
		
		String outLookValues[]=new String[3];
		outLookValues[0]="overcast";
		outLookValues[1]="rain";
		outLookValues[2]="sunny";
		attributeSet[0] = new DiscreteAttribute("Outlook",0, outLookValues);


		//temperature

		String temperatureValues[] = new String[3];
		temperatureValues[0]="Hot";
		temperatureValues[1]="Mild";
		temperatureValues[2]="Cool";
		attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);


		//Humidity

		String humidityValues[] = new String[2];
		humidityValues[0]="High";
		humidityValues[1]="Normal";
		attributeSet[2] = new DiscreteAttribute("Humidity", 2, humidityValues);

		//Wind

		String windValues[] = new String[2];
		windValues[0]="Weak";
		windValues[1]="Strong";
		attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);

		//PlayTennis

		String playTennisValues[] = new String[2];
		playTennisValues[0]="Yes";
		playTennisValues[1]="No";
		attributeSet[4] = new DiscreteAttribute("PlayTennis", 4, playTennisValues);
		
	}
	
	int getNumberOfExamples(){
		return numberOfExamples;
	}
	
	int getNumberOfAttributes(){
		return attributeSet.length;
	}

	Attribute[] getAttributeSchema(){
		return attributeSet;
	}
	
	
	Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data[exampleIndex][attributeIndex];
	}
	
	Attribute getAttribute(int index){
		return attributeSet[index];
	}
	
	
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

	Tuple getItemSet(int index){
		Tuple tuple=new Tuple(explanatorySet.length);
		for(int i=0;i<explanatorySet.length;i++)
			tuple.add(new DiscreteItem(explanatorySet[i], (String)data[index][i]),i);
		return tuple;
	}

	int[] sampling(int k){
		int centroidIndexes[]=new int[k];
		//choose k random different centroids in data.
		Random rand=new Random();
		rand.setSeed(System.currentTimeMillis());
		for(int i=0;i<k;i++){
			boolean found=false;
			int c;
			do
			{
				found=false;
				c=rand.nextInt(getNumberOfExamples());
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

	private boolean compare(int i,int j){
		for(int k = 0; k < this.getNumberOfAttributes(); k++){
			// controllare l'ugualianza senza cast
			if((String)data[i][k] != (String)data[j][k]){
				return false;
			}
		}
		return true;
	}

}

import data.Data;
import data.OutOfRangeSampleSize;
import mining.KMeansMiner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;


import static keyboardinput.Keyboard.*;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Data data = new Data();
		System.out.println(data);
		int k;
		String choice;
		String path = "savings/";
		String fileName;

		do {
			do {
				System.out.print("Do you want to create new clusters and save them (1) or load a previous one (2)? ");
				k = readInt();
			}while(k != 1 && k != 2);
			if(k == 1){
				try {
					System.out.print("Enter a valid number of clusters (more than 1): ");
					k = readInt();
					KMeansMiner kmeans = new KMeansMiner(k);
					int numIter = kmeans.kmeans(data);
					try{
						System.out.print("Please enter the name of the file to save (IMPORTANT: without extension): ");
						fileName = path + readString() + ".dat";
						kmeans.salva(fileName);
					}catch (FileNotFoundException fileError){
						System.out.println("Error file: " + fileError.getMessage());
					}catch (IOException ioError){
						System.out.println("Error i/o: " + ioError.getMessage());
					}
					System.out.println("Number of iterations:" + numIter);
					System.out.println(kmeans.getC().toString(data));
				} catch(OutOfRangeSampleSize e){
					System.out.println(e.getMessage());
				}
			}else {
				try{
					File pathFile = new File(path);
					String[] fileInPath = pathFile.list();
					for(int i = 0; i < fileInPath.length; i++){
						System.out.println("File n." + (i+1) + ": " + fileInPath[i].replace(".dat", ""));
					}
					System.out.print("Please enter the name of the file to load (IMPORTANT: without extension): ");
					fileName = path + readString() + ".dat";
					KMeansMiner kmeans = new KMeansMiner(fileName);
					System.out.println(kmeans.getC().toString(data));
				}catch (FileNotFoundException | ClassNotFoundException e){
					System.out.println("Error file: " + e.getMessage());
				}catch (IOException e){
					System.out.println("Error i/0: " + e.getMessage());
				}
			}
			do {
				System.out.print("Do you want to continue running? : ");
				System.out.print("For YES type Y, for NO type N : ");
				choice = readWord();
			} while (!(choice.equalsIgnoreCase("Y")) && !(choice.equalsIgnoreCase("N")));
		}while(choice.equalsIgnoreCase("Y"));
	}
}



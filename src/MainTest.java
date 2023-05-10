import data.Data;
import data.OutOfRangeSampleSize;
import mining.ClusterSet;
import mining.KMeansMiner;

import java.util.Scanner;

import static keyboardinput.Keyboard.*;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Data data = new Data();
		System.out.println(data);
		int k = 0;
		
		String choice;
		do {
			try {
				System.out.print("Enter a valid number of clusters (more than 1): ");
				k = readInt();
				KMeansMiner kmeans = new KMeansMiner(k);
				int numIter = kmeans.kmeans(data);
				System.out.println("Number of iterations:" + numIter);
				System.out.println(kmeans.getC().toString(data));
			} catch(OutOfRangeSampleSize e){
				System.out.println(e.getMessage());
			}

			do {
				System.out.print("Do you want to continue running? : ");
				System.out.print("For YES type Y, for NO type N : ");
				choice = readWord();
			} while (!(choice.toUpperCase().equals("Y")) && !(choice.toUpperCase().equals("N")));
		}while(choice.toUpperCase().equals("Y"));


	}
}


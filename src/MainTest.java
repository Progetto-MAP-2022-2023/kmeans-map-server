import data.Data;
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

		char choice;
		do {
			int k = 0;
			do {
				System.out.print("Enter the number of clusters : ");
				k = readInt();
			} while (k <= 0);

			KMeansMiner kmeans = new KMeansMiner(k);
			int numIter = kmeans.kmeans(data);
			System.out.println("Number of iterations:" + numIter);
			System.out.println(kmeans.getC().toString(data));


			do {
				System.out.print("Do you want to continue running? : ");
				System.out.print("For YES type Y, for NO type N : ");
				choice = readChar();
			} while ((choice == 'Y') || (choice == 'N'));
		}while(choice =='Y');




	}
}

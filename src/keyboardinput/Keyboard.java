//********************************************************************
//  Keyboard.java       Author: Lewis and Loftus
//
//  Facilitates keyboard input by abstracting details about input
//  parsing, conversions, and exception handling.
//********************************************************************

package keyboardinput;

import java.io.*;
import java.util.*;

/**
 * La classe {@code Keyboard} fornisce metodi per l'input da tastiera e la gestione degli errori.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class Keyboard {
	// ************* Error Handling Section **************************

	/**
	 * Indica se stampare gli errori durante l'input da tastiera.
	 */
	private static boolean printErrors = true;
	/**
	 * Contatore globale degli errori verificatisi durante l'input da tastiera.
	 */
	private static int errorCount = 0;

	/**
	 * Restituisce il numero corrente di errori verificatisi durante l'input da tastiera.
	 *
	 * @return Il conteggio corrente degli errori.
	 */
	public static int getErrorCount() {
		return errorCount;
	}

	/**
	 * Resetta il conteggio degli errori a un valore specificato.
	 *
	 * @param count Il nuovo valore con cui resettare il conteggio degli errori.
	 */
	public static void resetErrorCount(int count) {
		errorCount = 0;
	}

	/**
	 * Restituisce il valore corrente dell'opzione per stampare gli errori durante l'input da tastiera.
	 *
	 * @return {@code true} se gli errori vengono stampati, {@code false} altrimenti.
	 */
	public static boolean getPrintErrors() {
		return printErrors;
	}

	/**
	 * Imposta l'opzione per stampare o non stampare gli errori durante l'input da tastiera.
	 *
	 * @param flag {@code true} se si desidera stampare gli errori, {@code false} altrimenti.
	 */
	public static void setPrintErrors(boolean flag) {
		printErrors = flag;
	}

	/**
	 * Gestisce un errore durante l'input da tastiera, incrementando il conteggio degli errori e stampando un messaggio di errore (se abilitato).
	 *
	 * @param str Il messaggio di errore da stampare.
	 */
	private static void error(String str) {
		errorCount++;
		if (printErrors)
			System.out.println(str);
	}

	// ************* Tokenized Input Stream Section ******************

	/**
	 * Token corrente per l'input da tastiera.
	 */
	private static String current_token = null;
	/**
	 * Oggetto {@link StringTokenizer} utilizzato per analizzare l'input.
	 */
	private static StringTokenizer reader;
	/**
	 * Oggetto {@link BufferedReader} utilizzato per leggere l'input da tastiera.
	 */
	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	/**
	 * Restituisce il token successivo dell'input da tastiera,
	 * supponendo che possa trovarsi su righe di ingresso successive.
	 *
	 * @return Il token successivo dell'input.
	 */
	private static String getNextToken() {
		return getNextToken(true);
	}

	/**
	 * Restituisce il token successivo dell'input da tastiera, che potrebbe essere già stato letto.
	 *
	 * @param skip {@code true} se si desidera ignorare gli spazi bianchi durante la lettura, {@code false} altrimenti.
	 * @return Il token successivo dell'input.
	 */
	private static String getNextToken(boolean skip) {
		String token;

		if (current_token == null)
			token = getNextInputToken(skip);
		else {
			token = current_token;
			current_token = null;
		}

		return token;
	}

	/**
	 * Ottiene il prossimo token dall'input da tastiera, gestendo spazi bianchi in base alla richiesta.
	 *
	 * @param skip {@code true} se si desidera ignorare gli spazi bianchi durante la lettura, {@code false} altrimenti.
	 * @return Il prossimo token dall'input.
	 */
	private static String getNextInputToken(boolean skip) {
		final String delimiters = " \t\n\r\f";
		String token = null;

		try {
			if (reader == null)
				reader = new StringTokenizer(in.readLine(), delimiters, true);

			while (token == null || ((delimiters.indexOf(token) >= 0) && skip)) {
				while (!reader.hasMoreTokens())
					reader = new StringTokenizer(in.readLine(), delimiters,
							true);

				token = reader.nextToken();
			}
		} catch (Exception exception) {
			token = null;
		}

		return token;
	}

	/**
	 * Verifica se si è raggiunto la fine della riga durante la lettura dell'input da tastiera.
	 *
	 * @return {@code true} se si è raggiunto la fine della riga, {@code false} altrimenti.
	 */
	public static boolean endOfLine() {
		return !reader.hasMoreTokens();
	}

	// ************* Reading Section *********************************

	/**
	 * Legge una stringa dall'input da tastiera.
	 *
	 * @return La stringa letta dall'input o {@code null} in caso di errore.
	 */
	public static String readString() {
		String str;

		try {
			str = getNextToken(false);
			while (!endOfLine()) {
				str = str + getNextToken(false);
			}
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			str = null;
		}
		return str;
	}

	/**
	 * Legge una parola (token) dall'input da tastiera.
	 *
	 * @return La parola letta dall'input o {@code null} in caso di errore.
	 */
	public static String readWord() {
		String token;
		try {
			token = getNextToken();
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			token = null;
		}
		return token;
	}

	/**
	 * Legge un valore booleano dall'input da tastiera.
	 *
	 * @return Il valore booleano letto dall'input o {@code false} in caso di errore.
	 */
	public static boolean readBoolean() {
		String token = getNextToken();
		boolean bool;
		try {
			if (token.toLowerCase().equals("true"))
				bool = true;
			else if (token.toLowerCase().equals("false"))
				bool = false;
			else {
				error("Error reading boolean data, false value returned.");
				bool = false;
			}
		} catch (Exception exception) {
			error("Error reading boolean data, false value returned.");
			bool = false;
		}
		return bool;
	}

	/**
	 * Legge un carattere dall'input da tastiera.
	 *
	 * @return Il carattere letto dall'input o il valore MIN_VALUE del tipo char in caso di errore.
	 */
	public static char readChar() {
		String token = getNextToken(false);
		char value;
		try {
			if (token.length() > 1) {
				current_token = token.substring(1, token.length());
			} else
				current_token = null;
			value = token.charAt(0);
		} catch (Exception exception) {
			error("Error reading char data, MIN_VALUE value returned.");
			value = Character.MIN_VALUE;
		}

		return value;
	}

	/**
	 * Legge un valore intero dall'input da tastiera.
	 *
	 * @return Il valore intero letto dall'input o il valore MIN_VALUE del tipo int in caso di errore.
	 */
	public static int readInt() {
		String token = getNextToken();
		int value;
		try {
			value = Integer.parseInt(token);
		} catch (Exception exception) {
			error("Error reading int data, MIN_VALUE value returned.");
			value = Integer.MIN_VALUE;
		}
		return value;
	}

	/**
	 * Legge un valore long dall'input da tastiera.
	 *
	 * @return Il valore long letto dall'input o il valore MIN_VALUE del tipo long in caso di errore.
	 */
	public static long readLong() {
		String token = getNextToken();
		long value;
		try {
			value = Long.parseLong(token);
		} catch (Exception exception) {
			error("Error reading long data, MIN_VALUE value returned.");
			value = Long.MIN_VALUE;
		}
		return value;
	}

	/**
	 * Legge un valore float dall'input da tastiera.
	 *
	 * @return Il valore float letto dall'input o il valore NaN (Not a Number) in caso di errore.
	 */
	public static float readFloat() {
		String token = getNextToken();
		float value;
		try {
			value = (new Float(token)).floatValue();
		} catch (Exception exception) {
			error("Error reading float data, NaN value returned.");
			value = Float.NaN;
		}
		return value;
	}

	/**
	 * Legge un valore double dall'input da tastiera.
	 *
	 * @return Il valore double letto dall'input o il valore NaN (Not a Number) in caso di errore.
	 */
	public static double readDouble() {
		String token = getNextToken();
		double value;
		try {
			value = (new Double(token)).doubleValue();
		} catch (Exception exception) {
			error("Error reading double data, NaN value returned.");
			value = Double.NaN;
		}
		return value;
	}
}

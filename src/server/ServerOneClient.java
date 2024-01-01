package server;

import java.net.Socket;
import java.io.*;

import data.OutOfRangeSampleSize;
import mining.KMeansMiner;

import data.Data;

/**
 * La classe {@code ServerOneClient} rappresenta un gestore di connessione per un singolo client
 * all'interno di un server. Contiene i flussi di input e output, nonché un'istanza di {@link KMeansMiner}
 * per eseguire operazioni di mining dei dati.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class ServerOneClient extends Thread{
    /**
     * Il socket utilizzato per la comunicazione con il client.
     */
    private Socket socket;
    /**
     * Stream di input per la ricez ione di oggetti dal client.
     */
    private ObjectInputStream in;
    /**
     * Stream di output per l'invio di oggetti al client.
     */
    private ObjectOutputStream out;
    /**
     * Un'istanza di {@code KMeansMiner} per eseguire operazioni di mining dei dati.
     */
    private KMeansMiner kmeans;

    /**
     * Costruisce un nuovo oggetto {@code ServerOneClient} con il socket specificato.
     * Inizializza gli stream di input e output per la comunicazione con il client e avvia
     * il thread corrente.
     *
     * @param s Il socket utilizzato per la comunicazione con il client.
     * @throws IOException Se si verifica un errore durante l'apertura degli stream di input e output.
     */
    public ServerOneClient(Socket s) throws IOException{
        try {
            this.socket = s;
            this.in = new ObjectInputStream(this.socket.getInputStream());
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.start();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Il metodo {@code run()} gestisce le richieste del client ed esegue le operazioni corrispondenti.
     * Il comportamento è basato sulle scelte inviate dal client per determinare l'azione da compiere.
     * Supporta operazioni come caricare dati da una tabella, eseguire il clustering con K-Means, salvare i risultati su file,
     * e caricare risultati da un file precedentemente salvato.
     * Il metodo utilizza gli stream di input e output per comunicare con il client.
     * In caso di eccezioni, invia messaggi appropriati al client o li gestisce internamente, e chiude infine la connessione.
     */
    public void run(){
        String fileName = "";
        String path = "savings/";
        String table = "";
        Data data = null;
        Integer nCluster = null;
        KMeansMiner kmeans= null;

        try {
            while(true) {
                int choice = (Integer)in.readObject();
                switch (choice) {
                    case 0:
                        try {
                            table = (String)in.readObject();
                            data = new Data(table);
                            out.writeObject("OK");
                        } catch (Exception e) {
                            if (e.getMessage().contains("this.data")) {
                                out.writeObject("Table not found.");
                            } else {
                                out.writeObject("Error occurred while the server was connecting to the database. Please try again later.");
                            }
                        }
                        break;
                    case 1:
                        try {
                            nCluster = (Integer)in.readObject();
                            kmeans = new KMeansMiner(nCluster);
                            int numIter = kmeans.kmeans(data);
                            out.writeObject("OK");
                            out.writeObject("Number of iterations:" + numIter + "\n" + kmeans.getC().toString(data));
                        } catch (OutOfRangeSampleSize e) {
                            out.writeObject(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            fileName = path + table + nCluster + "k.dat";
                            System.out.println(fileName);
                            kmeans.salva(fileName);
                            out.writeObject("OK");
                        } catch (IOException e) {
                            out.writeObject("Errore col file.");
                        }
                        break;
                    case 3:
                        try {
                            table = ((String)in.readObject()).replace(" ", "");
                            data = new Data(table);
                            nCluster = (Integer)in.readObject();
                            fileName = path + table + nCluster + "k.dat";
                            System.out.println(fileName);
                            kmeans = new KMeansMiner(fileName);
                            out.writeObject("OK");
                            out.writeObject(kmeans.getC().toString(data));
                        } catch (FileNotFoundException e) {
                            out.writeObject("File not found.");
                        } catch (Exception e) {
                            if (e.getMessage().contains("this.data")) {
                                out.writeObject("Table not found.");
                            } else{
                                out.writeObject("Error occurred while the server was connecting to the database. Please try again later.");
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (EOFException e) {
            System.out.println("Connection reset");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

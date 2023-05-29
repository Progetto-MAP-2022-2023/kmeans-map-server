package Server;

import java.net.Socket;
import java.io.*;

import data.OutOfRangeSampleSize;
import mining.KMeansMiner;

import data.Data;

public class ServerOneClient extends Thread{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private KMeansMiner kmeans;

    public ServerOneClient(Socket s) throws IOException{
        try{
            this.socket = s;
            this.in = new ObjectInputStream(this.socket.getInputStream());
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.start();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void run(){
        String fileName = "";
        String path = "savings/";
        String table = "";
        Data data = null;
        Integer nCluster = null;
        KMeansMiner kmeans= null;

        try {
            while(true) {
                int choice = (Integer) in.readObject();
                switch (choice) {
                    case 0:
                        try {
                            table = (String) in.readObject();
                            data = new Data(table);
                            out.writeObject("OK");
                        } catch (Exception e) {
                            out.writeObject("Table not found.");
                        }
                        break;
                    case 1:
                        try {
                            nCluster = (Integer) in.readObject();
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
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }finally {
            try{
                socket.close();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

}

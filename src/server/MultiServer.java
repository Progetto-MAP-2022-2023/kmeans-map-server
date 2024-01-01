package server;

import java.io.IOException;
import java.net.*;

/**
 * Classe che rappresenta un server multi-client.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class MultiServer {
    /**
     * Porta di default per il server
     */
    private int PORT = 8080;

    /**
     * Costruttore per la creazione e l'avvio del server
     *
     * @param port Porta sulla quale avviare il server
     */
    public MultiServer(int port){
        PORT = port;
        this.run();
    }

    /**
     * Il metodo {@code run()} avvia un server che accetta connessioni da client.
     * Utilizza un oggetto {@link ServerSocket} per ascoltare su una porta specificata
     * e crea un nuovo oggetto {@link ServerOneClient} per gestire ogni connessione accettata.
     */
    void run(){
        ServerSocket s = null;
        try{
            s = new ServerSocket(PORT);
            try{
                while(true) {
                    Socket socket = s.accept();
                    try{
                        new ServerOneClient(socket);
                    }catch(IOException e){
                        socket.close();
                    }
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                s.close();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Il metodo {@code main()} è il punto di ingresso dell'applicazione.
     * Crea un oggetto {@link MultiServer} che avvia un server multithread sulla porta specificata.
     *
     * @param args Un array di stringhe che rappresenta gli argomenti della riga di comando.
     */
    public static void main(String[] args){
        MultiServer multiServer = new MultiServer(8080);
    }
}

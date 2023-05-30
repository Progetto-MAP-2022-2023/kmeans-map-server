package server;

import java.io.IOException;
import java.net.*;

public class MultiServer {
    private int PORT = 8080;

    public MultiServer(int port){
        PORT = port;
        this.run();
    }

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
    public static void main(String[] args){
        MultiServer multiServer = new MultiServer(8080);
    }
}

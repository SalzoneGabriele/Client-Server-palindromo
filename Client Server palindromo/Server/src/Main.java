import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2500);
            System.out.println("Server partito, in attesa...");
            while (true) {
                new Thread(new ServerThread(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
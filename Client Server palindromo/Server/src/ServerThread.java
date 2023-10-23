import com.sun.nio.sctp.AbstractNotificationHandler;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public static boolean isPalindrome(String str)
    {
        if (str == null) {
            return false;
        }

        for (int i = 0, j = str.length() - 1; i < j; i++, j--)
        {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connessione ricevuta");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            while (true) {
                String p=null;
                String s=null;
                String PrefissoEStringa = bufferedReader.readLine();
                System.out.println("messaggio ricevuto: "+PrefissoEStringa);
                if(PrefissoEStringa.equals("FINE")){
                    p = "FINE";
                    s=null;
                }else {
                    String[] separatore = PrefissoEStringa.split(" ");
                    p=separatore[0];
                    s=separatore[1];
                }

                System.out.println("blocco");
                if(p=="FINE"){
                    System.out.println("Ricevuto messaggio: " + s);
                    System.out.println("Rispondo al client " + socket.getLocalAddress());
                    printWriter.println("Risposta Server: " + "Comunicazione Finita");
                    printWriter.flush();
                }else{

                }

                System.out.println(p);
                if(p!="FINE"){

                    if(isPalindrome(s)==true){
                        System.out.println("Ricevuto messaggio: " + s);
                        System.out.println("Rispondo al client " + socket.getLocalAddress());
                        printWriter.println("Risposta Server: " + s+" è palindromo");
                        printWriter.flush();
                    }else{
                        System.out.println("Ricevuto messaggio: " + s);
                        System.out.println("Rispondo al client " + socket.getLocalAddress());
                        printWriter.println("Risposta Server: " + s+" non è palindromo");
                        printWriter.flush();
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber= 2500;
        Scanner tastiera = new Scanner(System.in);
        try {
            Socket clientSocket = new Socket(hostName, portNumber);
            System.out.println("server contattato");
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputUtente = null;
            boolean FINE=false;
            while (FINE==false) {
                Scanner sc =new Scanner(System.in);

                boolean loopbreaker=false;
                while (loopbreaker==false) {
                    System.out.println("1-inviare un messaggio\n2-Finire la comunicazione");
                    int scelta=sc.nextInt();
                    switch (scelta) {
                        case 1:
                            System.out.println("scrivere il messaggio da inviare");
                            String parola=sc.next();
                            System.out.println("parola inserita");
                            inputUtente = "PALINDROMO " + parola;
                            loopbreaker = true;
                            break;
                        case 2:
                            inputUtente = "FINE";
                            loopbreaker = true;
                            break;
                        default:
                            loopbreaker = false;
                            break;
                    }
                }

                if(inputUtente==null){
                    inputUtente="FINE";
                }

                writer.println(inputUtente);

                writer.flush();

                System.out.println("risultato: " + reader.readLine());

                if(inputUtente=="FINE"){
                    FINE=true;
                }

                loopbreaker=false;

            }
            //clientSocket.close();
        } catch (Exception e) {
            System.out.println("eccezione " + e.getMessage());
        }
    }
}
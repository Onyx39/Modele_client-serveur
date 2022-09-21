import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Serveur {

    static ServerSocket socketserver;
    static Socket socketduserveur;

    public static void main(String[] zero) {

        /*ServerSocket socketserver;
        Socket socketduserveur;*/
        BufferedReader in;
        PrintWriter out;

    try { 

        socketserver = new ServerSocket(1600);
        System.out.println("Serveur actif (port " + socketserver.getLocalPort() + ')');
        socketduserveur = socketserver.accept();
        in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
        String message_recu = in.readLine();
        if (!message_recu.equals("Hello")) {
            out = new PrintWriter((socketduserveur.getOutputStream()));
            out.println("STOP");
            out.flush();
            System.out.println("Vous n'avez pas serré la main, fin de communication");
            endOfConnection();
            System.exit(0);
        }

        out = new PrintWriter((socketduserveur.getOutputStream()));
        out.println("Hello and welcome, connection accepted");
        System.out.println("Un client s'est connecté\n");
        out.flush();

        while (!message_recu.equals("STOP") || message_recu == null) {
            in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
            message_recu = in.readLine();
            System.out.println(message_recu);

        }


        out = new PrintWriter((socketduserveur.getOutputStream()));
        out.println("STOP");
        out.flush();
        
        endOfConnection();


    }catch (IOException e) {
        e.printStackTrace();
    }

    }

    static void endOfConnection () throws IOException {
        System.out.println("\nEND OF CONNECTION \n");
        socketserver.close();
        socketduserveur.close();
    }
}
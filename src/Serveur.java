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

    public static void main(String[] zero) {

        ServerSocket socketserver;
        Socket socketduserveur;
        BufferedReader in;
        PrintWriter out;

    try { 

        socketserver = new ServerSocket(1600);
        System.out.println("Le serveur est à l'écoute du port " + socketserver.getLocalPort());
        socketduserveur = socketserver.accept();
        in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
        String message_recu = in.readLine();
        //System.out.println("message_recu : " + message_recu);
        if (message_recu.equals("DATE?")) {
            System.out.println("Un zéro s'est connecté !");
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println(LocalDate.now().toString());
            out.flush();
        }

        if (message_recu.equals("HOUR?")) {
            System.out.println("Un zéro s'est connecté !");
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println(LocalTime.now().toString());
            out.flush();
        }

        if (message_recu.equals("MOON?")) {
            System.out.println("Un zéro s'est connecté !");
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("I DON'T KNOW");
            out.flush();
        }
        
        socketserver.close();
        socketduserveur.close();


    }catch (IOException e) {
        e.printStackTrace();
    }

    }
}
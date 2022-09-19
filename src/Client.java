import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] zero) {

        Socket socket;
        BufferedReader in;
        PrintWriter out;

    try {

        socket = new Socket(InetAddress.getLocalHost(), 1600);
        System.out.println("Demande de connexion");

        out = new PrintWriter(socket.getOutputStream());
        out.println("MOON?");
        out.flush();

        in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
        String message_distant = in.readLine();
        System.out.println("Message reçu : " + message_distant);

        socket.close();
    
    }catch (UnknownHostException e) {

        e.printStackTrace();
    }catch (IOException e) {

        e.printStackTrace();
    }
    }
}
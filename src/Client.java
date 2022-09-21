import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.*; 

public class Client {

    static Socket socket;

    public static void main(String[] zero) throws InterruptedException {

        BufferedReader in;
        PrintWriter out;

    try {

        socket = new Socket(InetAddress.getLocalHost(), 1600);
        //System.out.println("Demande de connexion");

        out = new PrintWriter(socket.getOutputStream());
        out.println("Hello");
        out.flush();

        in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
        String message_distant = in.readLine();
        if (message_distant.equals("Hello and welcome, connection accepted")) {
            System.out.println("\nCONFIRMATION DE CONNECTION\n");
        }
        else {endOfConnection(); System.exit(0);}

        for (int i=0; i<10; i++) {

            out = new PrintWriter(socket.getOutputStream());
            Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
            //System.out.print("Enter a string: ");  
            String new_message = sc.nextLine(); 
            //sc.close(); 
            out.println(new_message);
            out.flush();
            if (new_message.equals("STOP")) {endOfConnection(); System.exit(0);}
    
            TimeUnit.SECONDS.sleep(1);
        }

        out.println("STOP");
        out.flush();

        endOfConnection();
    
    }catch (UnknownHostException e) {

        e.printStackTrace();
    }catch (IOException e) {

        e.printStackTrace();
    }
    }

    static void endOfConnection () throws IOException {
        System.out.println("\nEND OF CONNECTION \n");
        socket.close();
    }
}
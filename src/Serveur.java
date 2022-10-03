import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import javax.swing.*;

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
        //in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
        //String message_recu = in.readLine();
        //System.out.println(message_recu);
        /*
        if (!message_recu.equals("Hello")) {
            out = new PrintWriter((socketduserveur.getOutputStream()));
            out.println("STOP");
            out.flush();
            System.out.println("Vous n'avez pas serré la main, fin de communication");
            endOfConnection();
            System.exit(0);
        }*/
        out = new PrintWriter((socketduserveur.getOutputStream()));
        out.println("Hello stranger, please enter the code to connect");
        out.flush();
        in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
        String message_recu = in.readLine();
        in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
        message_recu = in.readLine();
        if (message_recu.equals("v")) {
           
        out = new PrintWriter((socketduserveur.getOutputStream()));
        out.println("Welcome, please enter your pseudo");
        //System.out.println("Un client s'est connecté\n");
        out.flush();
        in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
        message_recu = in.readLine();
        String client_pseudo = message_recu;
        System.out.println(message_recu + " s'est connecté");
        out.println("Nice to meet you " + message_recu + ", I am listening");
        out.flush();


        while (!message_recu.equals("STOP") || message_recu == null) {
            in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
            message_recu = in.readLine();
            //System.out.println(message_recu);
            if (message_recu.equals("en attente")) {break;}
            if (message_recu.equals("DATE?")) {
                out = new PrintWriter(socketduserveur.getOutputStream());
                out.println(LocalDate.now().toString());
                out.flush();
            }
    
            else { if (message_recu.equals("HOUR?")) {
                out = new PrintWriter(socketduserveur.getOutputStream());
                out.println(LocalTime.now().toString().substring(0, 8));
                out.flush();
            }
    
            else { if (message_recu.equals("BEGIN CONVERSATION")) {
                final JFrame f =  new JFrame();
                f.setLayout(null); 
                f.setSize(500,800);  
                f.setTitle("Fenêtre de tchat avec le client " + client_pseudo);
                final JLabel begin_conversation = new JLabel("Begin of the discussion");
                begin_conversation.setBounds(200, 0, 250, 25); 
                Integer height = 30;
                f.add(begin_conversation);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
                out = new PrintWriter(socketduserveur.getOutputStream());
                out.println("As you wish, what do you want to tell me ? \nAs a reminder, please, do not use accents \nThank you in advance");
                out.flush();
                in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
                message_recu = in.readLine();
                while (!message_recu.equals("END CONVERSATION") || message_recu == null) {
                    height += 15;
                    final JLabel client_message = new JLabel(message_recu);
                    client_message.setBounds(0, height, 250, 25); 
                    
                    f.add(client_message);
                    f.revalidate();
                    SwingUtilities.updateComponentTreeUI(f);
                    client_message.setVisible(true);
                    out = new PrintWriter(socketduserveur.getOutputStream());
                    Scanner sc = new Scanner(System.in);   
                    String new_message = sc.nextLine(); 
                    out.println(new_message);
                    out.flush();
                    JLabel reponse_serveur = new JLabel(new_message);
                    height += 15;
                    reponse_serveur.setBounds(250, height, 250, 25); 
                    f.add(reponse_serveur);
                    reponse_serveur.setVisible(true);
                    f.revalidate();
                    SwingUtilities.updateComponentTreeUI(f);
                    in = new BufferedReader(new InputStreamReader((socketduserveur.getInputStream())));
                    message_recu = in.readLine();

                }
                //sc.close();
                f.removeAll();
                out = new PrintWriter(socketduserveur.getOutputStream());
                out.println("CONVERSATION CLOSED\nWhat can I do for you now ?");
                out.flush();
                message_recu = "en attente";
                //break;
                
            }   
            else { if(!message_recu.equals("STOP")) {
                out = new PrintWriter(socketduserveur.getOutputStream());
                out.println("I DON'T UNDERSTAND");
                out.flush();
            }}}}}}

            else {
                out = new PrintWriter((socketduserveur.getOutputStream()));
                out.println("Incorrect code, I can not communicate with you. \nHave a good day anyway \nKisses");
                out.flush();
                System.out.println("Someone tried to connect but did not know the code");
                endOfConnection();
                System.exit(0);
            

            }


        out = new PrintWriter((socketduserveur.getOutputStream()));
        out.println("STOP\nSee you the next time");
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
        System.exit(0);
    }
}
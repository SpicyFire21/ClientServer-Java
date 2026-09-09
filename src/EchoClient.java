import java.io.*;
import java.net.*;
 
class EchoClient  {
 
    public static void main(String []args) {

        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader br = null; // pour lire du texte sur la socket
	PrintStream ps = null; // pour écrire du texte sur la socket
	String line = null;
	Socket sock = null;
	int port = -1;
 
	if (args.length != 2) {
        System.out.println("usage: EchoClient ip_server port");
	    System.exit(1);
	}
 
	try {
	    port = Integer.parseInt(args[1]); // récupération du port sous forme int
	    sock = new Socket(args[0],port); // création socket client et connexion au serveur donné en args[0]
	}
	catch(IOException e) {
	    System.out.println("problème de connexion au serveur : "+e.getMessage());
	    System.exit(1);
	}
 
	try {
        br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // création flux lecture lignes de texte
        ps = new PrintStream(sock.getOutputStream()); // création flux écriture lignes de texte

        while (true) {

            System.out.println("Écrivez votre message :");
            String message = clavier.readLine();

            if (message == null || message.isEmpty()) {
                break;
            }

            ps.println(message);

            String reponse = br.readLine();
            System.out.println("Le serveur répond : " + reponse);
        }
        sock.close();
	    br.close();
	    ps.close();
	}
	catch(IOException e) {
	    System.out.println(e.getMessage());
	}
    }
}

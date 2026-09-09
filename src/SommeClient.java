import java.io.*;
import java.net.*;

class SommeClient {

    public static void main(String[] args) {

        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

        if (args.length != 2) {
            System.out.println("usage: SommeClient ip port");
            System.exit(1);
        }

        try {
            Socket sock = new Socket(args[0], Integer.parseInt(args[1]));

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));

            PrintStream ps = new PrintStream(sock.getOutputStream());

            while (true) {

                System.out.println("Entrez des entiers séparés par des virgules :");
                String ligne = clavier.readLine();

                if (ligne == null || ligne.isEmpty())
                    break;

                String[] morceaux = ligne.split(",");

                boolean valide = true;

                for (String m : morceaux) {
                    try {
                        Integer.parseInt(m.trim());
                    } catch (NumberFormatException e) {
                        valide = false;
                        break;
                    }
                }

                if (!valide) {
                    System.out.println("Requête malformée");
                    continue;
                }

                ps.println(morceaux.length);

                for (String m : morceaux)
                    ps.println(m.trim());

                System.out.println("Somme = " + br.readLine());
            }

            sock.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
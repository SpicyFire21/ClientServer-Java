import java.io.*;
import java.net.*;

class SommeServer {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("usage: SommeServer port");
            System.exit(1);
        }

        try {

            ServerSocket conn = new ServerSocket(Integer.parseInt(args[0]));

            while (true) {

                Socket sock = conn.accept();

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(sock.getInputStream()));

                PrintStream ps = new PrintStream(sock.getOutputStream());

                String ligne;

                while ((ligne = br.readLine()) != null) {

                    if (ligne.isEmpty())
                        break;

                    int nb = Integer.parseInt(ligne);
                    int somme = 0;

                    for (int i = 0; i < nb; i++) {
                        somme += Integer.parseInt(br.readLine());
                    }

                    ps.println(somme);
                }

                sock.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
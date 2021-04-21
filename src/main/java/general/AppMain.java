package general;

import java.util.Scanner;

/**
 * Classe principale
 */
public final class AppMain
{

    private AppMain()
    {
        //non chiamato
    }

    /**
     * Comandi accettati in input
     */
    enum Comando
    {
        help, play, quit, nullo
    }

    /**
     * Metodo principale
     *
     * @param args input iniziale
     */
    public static void main(final String[] args)
    {

        Scanner leggi = new Scanner(System.in);
        Comando comando = null;
        System.out.println("\n   BENVENUTO AL GIOCO DEGLI SCACCHI     (Per l'elenco dei comandi digita help) ");

        while (comando != AppMain.Comando.quit || comando != AppMain.Comando.play)
        {

            System.out.print("\n   >In attesa di un comando: ");
            comando = traduciComando(leggi.next());
            System.out.println();

            switch (comando)
            {

                case play:
                    Partita p = new Partita();
                    p.play();
                    break;

                case help:
                    System.out.println("   >play    [per iniziare una nuova partita]");
                    System.out.println("   >quit    [per chiudere l'applicazione]");
                    break;

                case nullo:
                    System.out.println("   Comando non valido, reinserire.");
                    break;

                case quit:
                    // Caso comando di chiusura
                    System.out.println("   SEI SICURO DI VOLER USCIRE? S/N");
                    System.out.print("   >");
                    String scelta = leggi.next();

                    if (scelta.equals("S"))
                    {
                        System.out.println("\n   Hai abbandonato la partita.");
                        System.exit(0);
                    } else if (!scelta.equals("N"))
                    {
                        System.out.println("\n   Comando non valido, reinserire.");
                    }
                    break;

                default:
                    break;
            }

        }
        leggi.close();
    }

    /**
     * Legge il comando inserito dall'utente
     *
     * @param scelta input dell'utente
     * @return command comando da eseguire
     */
    public static Comando traduciComando(final String scelta)
    {

        Comando command = null;

        if (scelta.equals("play"))
        {
            command = Comando.play;
        } else if (scelta.equals("help"))
        {
            command = Comando.help;
        } else if (scelta.equals("quit"))
        {
            command = Comando.quit;
        } else
        {
            command = Comando.nullo;
        }

        return command;
    }

}

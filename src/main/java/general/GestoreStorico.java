package general;

import pezzi.Pezzo;

import java.util.ArrayList;

/**
 * Entity class
 * Gestione dello storico delle mosse o delle catture
 */
public final class GestoreStorico {

    private static ArrayList<String> mosse = new ArrayList<String>();
    private static ArrayList<String> cattureBianco = new ArrayList<String>();
    private static ArrayList<String> cattureNero = new ArrayList<String>();

    /**
     * Costruttore
     */
    private GestoreStorico() {
        // non chiamato
    }

    /**
     * Stampa lo storico delle mosse
     */
    public static void stampaStoricoMosse() {

        int j = 0;
        System.out.println("\n   >Storico mosse:");

        if (mosse.size() != 0) {
            for (int i = 0; i < mosse.size(); i++) {
                if (i % 2 == 0) {
                    j++;
                    System.out.print("\n   " + (j) + ". " + mosse.get(i)); // stampa di tutto il contenuto
                } else {
                    System.out.print(", " + mosse.get(i));
                }
            }
            System.out.println();

        } else {
            System.out.println("\n   >Nessuna mossa presente!");
        }

    }

    /**
     * Stampa lo storico delle catture
     */
    public static void stampaStoricoCatture() {

        System.out.println("\n   >Storico catture giocatore BIANCO:\n");
        for (int i = 0; i < cattureBianco.size(); i++) { // stampa di tutto il contenuto
            System.out.println("   " + (i + 1) + ". " + cattureBianco.get(i));
        }

        if (cattureBianco.size() == 0) {
            System.out.println("   Nessuna cattura effettuata dal giocatore!");
        }

        System.out.println("\n   >Storico catture giocatore NERO:\n");
        for (int i = 0; i < cattureNero.size(); i++) { // stampa di tutto il contenuto
            System.out.println("   " + (i + 1) + ". " + cattureNero.get(i));
        }

        if (cattureNero.size() == 0) {
            System.out.println("   Nessuna cattura effettuata dal giocatore!");
        }

    }

    /**
     * Aggiunge la mossa allo storico delle mosse
     *
     * @param comando da aggiungere allo storico
     */
    public static void aggiungiMossa(final String comando) {

        // aggiunta all'array delle mosse
        mosse.add(comando);

    }

    /**
     * Aggiunge la cattura allo storico delle catture
     *
     * @param giocatore il giocatore che ha effettuato la cattura
     * @param pezzo     il pezzo che � stato catturato
     */
    public static void aggiungiCattura(final String giocatore, final Pezzo pezzo) {

        if (giocatore.equals("bianco")) { // aggiunta all'array delle catture
            cattureBianco.add(pezzo.getUniCode());
        } else {
            cattureNero.add(pezzo.getUniCode());
        }

    }

    /**
     * Resetta lo storico delle mosse e delle catture
     */
    public static void resettaLista() {

        mosse.clear();
        cattureBianco.clear();
        cattureNero.clear();

    }

}

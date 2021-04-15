package general;

import java.util.Scanner;

import pezzi.Re;

/**
 * Boundary class
 * Gestione della partita in corso tramite interazione con l'utente
 */
public class Partita {
    private static final int DIM = 4;
    private static final int RIGINB = 7;
    private static final int COLINB = 4;
    private static final int RIGINN = 0;
    private static final int COLINN = 4;
    private static final int TRE = 3;
    private Scacchiera scacchiera = new Scacchiera();
    private static String giocatore = "bianco";
    private Partita p;
    private static Re rePrecBianco = null, rePrecNero = null;
    private static int[] re = new int[DIM];

    /**
     * Comandi accettati in input
     */
    public enum Command {
	play, help, board, moves, captures, spostamento, cattura, quit, nullo
    }

    /**
     * Costruttore
     */
    public Partita() {
	scacchiera.show();
	System.out.println("\n   Partita iniziata!");
	re[0] = RIGINB;
	re[1] = COLINB;
	re[2] = RIGINN;
	re[TRE] = COLINN;
    }

    /**
     * Restituisce il re bianco del turno precedente
     * @return rePrecBianco il re bianco al turno precedente
     */
    public static Re getRePrecBianco() {
	return rePrecBianco;
    }

    /**
     * Modifica il re bianco del turno precedente
     * @param rePrecBiancoRic il re bianco che si vuole salvare
     */
    public static void setRePrecBianco(final Re rePrecBiancoRic) {
	Partita.rePrecBianco = rePrecBiancoRic;
    }

    /**
     * Restituisce il re nero del turno precedente
     * @return rePrecNero il re nero al turno precedente
     */
    public static Re getRePrecNero() {
	return rePrecNero;
    }

    /**
     * Modifica il re nero del turno precedente
     * @param rePrecNeroRic il re nero che si vuole salvare
     */
    public static void setRePrecNero(final Re rePrecNeroRic) {
	Partita.rePrecNero = rePrecNeroRic;
    }

    /**
     * Restituisce il vettore contenente le posizioni dei due re
     * @return re array contenente le attuali coordinate dei due re
     */
    public static int[] getRe() {
	return re;
    }

    /**
     * Modifica il vettore contenente le posizioni dei due re
     * @param reRic il nuovo array con le nuove coordinate dei due re
     */
    public static void setRe(final int[] reRic) {
	Partita.re = reRic;
    }

    /**
     * Gestisce il gioco
     */
    public void play() {

	try (Scanner leggi = new Scanner(System.in)) {
	    String comando;
	    Command command = null;

	    while (!scaccoMatto(scacchiera)) {

		System.out.println("\n   >Turno del giocatore " + giocatore.toUpperCase()
			+ "   (Per l'elenco dei comandi digita help)");
		System.out.print("   >");
		comando = leggi.nextLine();
		command = Traduttore.traduciComando(comando);

		switch (command) {

		case play:
		    // Caso di riavvio partita
		    System.out.println("\n   SEI SICURO DI VOLER RIAVVIARE? S/N");
		    System.out.print("   >");
		    String scelta = leggi.next();

		    if (scelta.equals("S")) {
			p = null;
			p = new Partita();
			GestoreStorico.resettaLista();
			giocatore = "bianco";
			p.play();
		    } else if (scelta.equals("N")) {
			play();
		    }
		    break;

		case board:
		    // Caso comando mostra scacchiera
		    scacchiera.show();
		    break;

		case help:
		    // Caso comando aiuto
		    System.out.println();
		    System.out.println(
			    "   >play                             "
			    + "[per chiudere la partita attuale e iniziarne una nuova]");
		    System.out.println(
			    "   >board                            [per mostrare la posizione sulla scacchiera]");
		    System.out.println("   >moves                            [per mostrare lo storico delle mosse]");
		    System.out.println(
			    "   >captures                         [per mostrare le catture del BIANCO o del NERO]");
		    System.out.println("   >([ADTCR])?([a-h])([1-8])         [comando per lo spostamento di un pezzo]");
		    System.out.println("   >([ADTCR | a-h])(x)([a-h])([1-8]) [comando per la cattura semplice]");
		    System.out.println(
	 		    "   >([a-h])(x)([a-h])([1-8]) (e.p.)? "
	 		    + "[comando per la cattura en passant, notazione 'e.p.' facoltativa]");
		    System.out.println("   >(0-0 | O-O)                      [comando per arroccare corto] ");
		    System.out.println("   >(0-0-0 | O-O-O)                  [comando per arroccare lungo] ");

		    System.out.println("   >quit                             [per chiudere l'applicazione]");
		    System.out.println();
		    break;

		case quit:
		    // Caso comando di chiusura
		    System.out.println("\n   SEI SICURO DI VOLER USCIRE? S/N");
		    System.out.print("   >");
		    String scelta1 = leggi.next();

		    if (scelta1.equals("S")) {
			System.out.println("\n   " + giocatore.toUpperCase() + " ha abbandonato la partita.");
			System.exit(0);
		    } else if (scelta1.equals("N")) {
			play();
		    }
		    break;

		case moves:
		    // Caso storico mosse
		    GestoreStorico.stampaStoricoMosse();
		    break;

		case captures:
		    // Caso storico catture
		    GestoreStorico.stampaStoricoCatture();
		    break;

		case spostamento:
		    // Caso movimento di un pezzo
		    new Spostamento(comando, giocatore, scacchiera);
		    break;

		case cattura:
		    // Caso cattura di un pezzo
		    new Cattura(comando, giocatore, scacchiera);
		    break;

		case nullo:
		    // Caso comando inesistente
		    System.out.println("   Comando non valido");
		    break;

		default:
		    break;
		}

	    }
	    System.out.println("   SCACCO MATTO!");
	    System.out.println("   Il " + giocatore.toUpperCase() + " vince la partita!");
	    System.out.println("\n   >Ritorno al menu' principale...");
	    AppMain.main(null);
	}

    }

    /**
     * Verifica che ci sia la situazione di scacco matto
     * @param s scacchiera
     * @return esito se uno dei due re � in posizione di scacco matto
     */
    public static boolean scaccoMatto(final Scacchiera s) {

	boolean esito = false;

	if (giocatore.equals("bianco")) {

	    if (Spostamento.isSottoScacco(re[2], re[TRE], "nero", s)) {  //se il re avversario � sotto scacco
		rePrecNero = (Re) s.getScacchiera()[re[2]][re[TRE]];  //viene segnato
		esito = true;    //e viene segnato che ci si trova in posizione di scacco
	    } else {
		rePrecNero = null;   //se il re avversario non � in posizione di scacco
		esito = false;
	    }
	} else {

	    if (Spostamento.isSottoScacco(re[0], re[1], "bianco", s)) {
		rePrecBianco = (Re) s.getScacchiera()[re[0]][re[1]];
		esito = true;
	    } else {
		rePrecBianco = null;
		esito = false;
	    }
	}
	return esito;

    }

    /**
     * Passa il turno all'avversario
     * @return giocatore il nuovo giocatore in turno
     */
    public static String getPlayer() {

	if (giocatore.equals("bianco")) {
	    giocatore = "nero";
	} else {
	    giocatore = "bianco";
	}
	return giocatore;
    }

}

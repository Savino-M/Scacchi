package general;

import pezzi.*;

/**
 * Control class
 * Gestione della cattura di un pezzo in base al comando ricevuto
 */
public class Cattura {

    static final int TRE = 3;
    static final int QUATTRO = 4;
    static final int CINQUE = 5;
    static final int SETTE = 7;
    private String comando;
    private String giocatore;
    private Scacchiera scacchiera;

    /**
     * Costruttore
     *
     * @param comandoRicevuto    il comando inserito dall'utente
     * @param giocatoreRicevuto  il giocatore che ha richiesto il comando
     * @param scacchieraRicevuta la scacchiera
     */
    public Cattura(final String comandoRicevuto, final String giocatoreRicevuto, final Scacchiera scacchieraRicevuta) {
        this.comando = comandoRicevuto;
        this.giocatore = giocatoreRicevuto;
        this.scacchiera = scacchieraRicevuta;
        interpreta();
    }

    /**
     * Esegue la cattura da parte del pezzo richiesto
     */
    public void interpreta() {

        switch (comando.charAt(0)) {
            case 'A':
                catturaAlfiere();
                break;
            case 'D':
                catturaDonna();
                break;
            case 'T':
                catturaTorre();
                break;
            case 'C':
                catturaCavallo();
                break;
            case 'R':
                catturaRe();
                break;
            default:
                catturaPedone();
                break;
        }
    }

    /**
     * Gestisce la cattura del re
     */
    public void catturaRe() {

        int colMangiatore;
        int rigaTarget = Traduttore.traduciRiga(comando.charAt(TRE));
        int colTarget = Traduttore.traduciColonna(comando.charAt(2));
        int rigaMangiatore;
        String m = null;

        // se esiste un pezzo nelle coordinate date e appartiene all'avversario
        boolean validCommand = scacchiera.getScacchiera()[rigaTarget][colTarget] != null
                && scacchiera.getScacchiera()[rigaTarget][colTarget].getColor() != giocatore.toUpperCase().charAt(0);

        if (validCommand && !Spostamento.isSottoScacco(rigaTarget, colTarget, giocatore, scacchiera)) {
            Pezzo pezzo = scacchiera.getScacchiera()[rigaTarget][colTarget];
            m = Ricerca.trovaRe(rigaTarget, colTarget, giocatore, scacchiera);
            rigaMangiatore = Integer.valueOf(m.substring(0, 1)); // salvo la riga di partenza del Re
            colMangiatore = Integer.valueOf(m.substring(2)); // salvo la colonna di partenza del Re
            Re r = (Re) scacchiera.getScacchiera()[rigaMangiatore][colMangiatore];

            r.cattura(rigaMangiatore, colMangiatore, rigaTarget, colTarget, scacchiera); // effettuo la cattura

            System.out.println("   Hai eseguito una cattura!");
            GestoreStorico.aggiungiMossa(comando); // aggiungo la cattura allo storico mosse
            GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura allo storico catture
            if (giocatore.equals("bianco")) {
                Partita.getRe()[0] = rigaTarget;
                Partita.getRe()[1] = colTarget;
            } else {
                Partita.getRe()[2] = rigaTarget;
                Partita.getRe()[TRE] = colTarget;
            }
            giocatore = Partita.getPlayer(); // passo il turno all'avversario

        } else {
            System.out.println("   Mossa illegale, cattura non consentita!");
        }

    }

    /**
     * Gestisce la cattura del cavallo
     */
    public void catturaCavallo() {
        int colMangiatore;
        int rigaTarget = Traduttore.traduciRiga(comando.charAt(TRE));
        int colTarget = Traduttore.traduciColonna(comando.charAt(2));
        String m = null;
        int rigaMangiatore, spostamento;

        // se esiste un pezzo nelle coordinate date e appartiene all'avversario
        boolean validCommand = scacchiera.getScacchiera()[rigaTarget][colTarget] != null
                && scacchiera.getScacchiera()[rigaTarget][colTarget].getColor() != giocatore.toUpperCase().charAt(0);

        if (validCommand) {
            Pezzo pezzo = scacchiera.getScacchiera()[rigaTarget][colTarget];
            m = Ricerca.trovaCavallo(rigaTarget, colTarget, giocatore, scacchiera); // trova il Cavallo "mangiatore"

            if (m == null) {
                System.out.println("   Mossa illegale, cattura non consentita!");
            } else {
                rigaMangiatore = Integer.valueOf(m.substring(0, 1)); // salvo la riga del Cavallo "mangiatore"
                colMangiatore = Integer.valueOf(m.substring(2, TRE)); // salvo la colonna del Cavallo mangiatore
                spostamento = Integer.valueOf(m.substring(QUATTRO)); // salvo il numero di celle di cui bisogna
                // spostarsi
                Cavallo c = null;

                try {
                    c = (Cavallo) scacchiera.getScacchiera()[rigaMangiatore][colMangiatore];

                    // se la cattura va a buon fine
                    if (c.cattura(rigaMangiatore, colMangiatore, spostamento, scacchiera)) {

                        System.out.println("   Hai eseguito una cattura!");
                        GestoreStorico.aggiungiMossa(comando); // aggiungo la cattura allo storico mosse
                        GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura allo storico catture
                        giocatore = Partita.getPlayer(); // passo il turno all'avversario
                    } else {
                        System.out.println("   Mossa illegale, cattura non consentita!");
                    }
                } catch (Exception e) {
                    System.out.println("   Mossa illegale, cattura non consentita!");
                }
            }
        } else {
            System.out.println("   Mossa illegale, cattura non consentita!");
        }
    }

    /**
     * Gestisce la cattura dell'alfiere
     */
    public void catturaAlfiere() {

        int colMangiatore;
        int rigaTarget = Traduttore.traduciRiga(comando.charAt(TRE));
        int colTarget = Traduttore.traduciColonna(comando.charAt(2));
        String m = null;
        int rigaMangiatore, spostamento;
        Alfiere a = null;

        // se esiste un pezzo nelle coordinate date e appartiene all'avversario
        boolean validCommand = scacchiera.getScacchiera()[rigaTarget][colTarget] != null
                && scacchiera.getScacchiera()[rigaTarget][colTarget].getColor() != giocatore.toUpperCase().charAt(0);

        if (validCommand) {
            Pezzo pezzo = scacchiera.getScacchiera()[rigaTarget][colTarget];
            m = Ricerca.trovaDiagonale(rigaTarget, colTarget, giocatore, scacchiera, "Alfiere"); // trovo l'Alfiere
            // "mangiatore"

            if (m == null) {
                System.out.println("   Mossa illegale, cattura non consentita!");
            } else {
                rigaMangiatore = Integer.valueOf(m.substring(0, 1)); // salvo la riga dell'Alfiere "mangiatore"
                colMangiatore = Integer.valueOf(m.substring(2, TRE)); // salvo la colonna dell'Alfiere "mangiatore"
                spostamento = Integer.valueOf(m.substring(QUATTRO)); // salvo il numero di celle di cui bisogna
                // spostarsi

                try {
                    a = (Alfiere) scacchiera.getScacchiera()[rigaMangiatore][colMangiatore];

                    // se la cattura va a buon fine
                    if (a.cattura(rigaMangiatore, colMangiatore, rigaTarget, colTarget, spostamento, scacchiera)) {

                        System.out.println("   Hai eseguito una cattura!");
                        GestoreStorico.aggiungiMossa(comando); // aggiungo la cattura allo storico mosse
                        GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura allo storico catture
                        giocatore = Partita.getPlayer(); // passo il turno all'avversario
                    } else {
                        System.out.println("   Mossa illegale, cattura non consentita!");
                    }
                } catch (Exception e) {
                    System.out.println("   Mossa illegale, cattura non consentita!");
                }
            }
        } else {
            System.out.println("   Mossa illegale, cattura non consentita!");
        }
    }

    /**
     * Gestisce la cattura della torre
     */
    public void catturaTorre() {

        int colMangiatore;
        int rigaTarget = Traduttore.traduciRiga(comando.charAt(TRE));
        int colTarget = Traduttore.traduciColonna(comando.charAt(2));
        String m = null;
        int rigaMangiatore, spostamento;
        Torre t = null;

        // se esiste un pezzo nelle coordinate date e appartiene all'avversario
        boolean validCommand = scacchiera.getScacchiera()[rigaTarget][colTarget] != null
                && scacchiera.getScacchiera()[rigaTarget][colTarget].getColor() != giocatore.toUpperCase().charAt(0);

        if (validCommand) {
            Pezzo pezzo = scacchiera.getScacchiera()[rigaTarget][colTarget];
            m = Ricerca.trovaLato(rigaTarget, colTarget, giocatore, scacchiera, "Torre");
            // trovo la Torre "mangiatrice"
            if (m == null) {
                System.out.println("   Mossa illegale, cattura non consentita!");
            } else {
                rigaMangiatore = Integer.valueOf(m.substring(0, 1)); // salvo la riga della Torre "mangiatrice"
                colMangiatore = Integer.valueOf(m.substring(2, TRE)); // salvo la colonna della Torre "mangiatrice"
                spostamento = Integer.valueOf(m.substring(QUATTRO)); // salvo il numero di celle di cui bisogna
                // spostarsi
                try {
                    t = (Torre) scacchiera.getScacchiera()[rigaMangiatore][colMangiatore];

                    // se la cattura va a buon fine
                    if (t.cattura(rigaMangiatore, colMangiatore, rigaTarget, colTarget, spostamento, scacchiera)) {

                        System.out.println("   Hai eseguito una cattura!");
                        GestoreStorico.aggiungiMossa(comando); // aggiungo la cattura allo storico mosse
                        GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura allo storico catture
                        giocatore = Partita.getPlayer(); // passo il turno all'avversario
                    } else {
                        System.out.println("   Mossa illegale, cattura non consentita!");
                    }
                } catch (Exception e) {
                    System.out.println("   Mossa illegale, cattura non consentita!");
                }
            }
        } else {
            System.out.println("   Mossa illegale, cattura non consentita!");
        }
    }

    /**
     * Gestisce la cattura della donna
     */
    public void catturaDonna() {

        int colMangiatore;
        int rigaTarget = Traduttore.traduciRiga(comando.charAt(TRE));
        int colTarget = Traduttore.traduciColonna(comando.charAt(2));
        String m1, m2;
        int rigaMangiatore, spostamento;
        Donna d = null;

        // se esiste un pezzo nelle coordinate date e appartiene all'avversario
        boolean validCommand = scacchiera.getScacchiera()[rigaTarget][colTarget] != null
                && scacchiera.getScacchiera()[rigaTarget][colTarget].getColor() != giocatore.toUpperCase().charAt(0);

        if (validCommand) {
            Pezzo pezzo = scacchiera.getScacchiera()[rigaTarget][colTarget];
            m1 = Ricerca.trovaDiagonale(rigaTarget, colTarget, giocatore, scacchiera, "Donna");
            // mi muovo in diagonale alla ricerca della Donna "mangiatrice"
            m2 = Ricerca.trovaLato(rigaTarget, colTarget, giocatore, scacchiera, "Donna");
            // mi muovo sui lati alla ricerca della Donna "mangiatrice"

            if (m1 != null) {
                rigaMangiatore = Integer.valueOf(m1.substring(0, 1)); // salvo la riga della Donna "mangiatrice"
                colMangiatore = Integer.valueOf(m1.substring(2, TRE)); // salvo la colonna della Donna "mangiatrice"
                spostamento = Integer.valueOf(m1.substring(QUATTRO)); // salvo il numero di celle di cui bisogna
                // spostarsi

                try {
                    d = (Donna) scacchiera.getScacchiera()[rigaMangiatore][colMangiatore];

                    // se la cattura va a buon fine
                    if (d.catturaDiagonale(rigaMangiatore, colMangiatore, rigaTarget, colTarget, spostamento,
                            scacchiera)) {

                        System.out.println("   Hai eseguito una cattura!");
                        GestoreStorico.aggiungiMossa(comando); // aggiungo la cattura allo storico mosse
                        GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura allo storico catture
                        giocatore = Partita.getPlayer(); // passo il turno all'avversario
                    } else {
                        System.out.println("   Mossa illegale, cattura non consentita!");
                    }
                } catch (Exception e) {
                    System.out.println("   Mossa illegale, cattura non consentita!");
                }

            } else if (m2 != null) {
                rigaMangiatore = Integer.valueOf(m2.substring(0, 1)); // salvo la riga della Donna "mangiatrice"
                colMangiatore = Integer.valueOf(m2.substring(2, TRE)); // salvo la riga della Donna "mangiatrice"
                spostamento = Integer.valueOf(m2.substring(QUATTRO)); // salvo il numero di celle di cui bisogna
                // spostarsi
                try {
                    d = (Donna) scacchiera.getScacchiera()[rigaMangiatore][colMangiatore];

                    // se la cattura va a buon fine
                    if (d.catturaLato(rigaMangiatore, colMangiatore, rigaTarget, colTarget, spostamento, scacchiera)) {

                        System.out.println("   Hai eseguito una cattura!");
                        GestoreStorico.aggiungiMossa(comando); // aggiungo la cattura allo storico mosse
                        GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura allo storico catture
                        giocatore = Partita.getPlayer(); // passo il turno all'avversario
                    } else {
                        System.out.println("   Mossa illegale, cattura non consentita!");
                    }
                } catch (Exception e) {
                    System.out.println("   Mossa illegale, cattura non consentita!");
                }
            } else {
                System.out.println("   Mossa illegale, cattura non consentita!");
            }
        } else {
            System.out.println("   Mossa illegale, cattura non consentita!");
        }
    }

    /**
     * Gestisce la cattura del pedone
     */
    public void catturaPedone() {

        Pezzo pezzo = null;
        int colTarget = Traduttore.traduciColonna(comando.charAt(2));
        int rigaTarget = Traduttore.traduciRiga(comando.charAt(TRE));
        int colMangiatore = Traduttore.traduciColonna(comando.charAt(0));
        int offsetRiga = 0;
        int offset;
        boolean esitoMossa = false;
        int spostamento = 0;

        // se esiste un pezzo nelle coordinate nelle quali effettuare la cattura e se il
        // pezzo da catturare e' dell'avversario e non e' un re
        boolean valid = scacchiera.getScacchiera()[rigaTarget][colTarget] != null
                && scacchiera.getScacchiera()[rigaTarget][colTarget].getColor() != giocatore.toUpperCase().charAt(0)
                && !(scacchiera.getScacchiera()[rigaTarget][colTarget] instanceof Re)
                && (comando.length() == QUATTRO || (comando.length() == CINQUE && comando.charAt(QUATTRO) == ' '));

        if (giocatore.equals("bianco")) {
            offset = 1;
        } else {
            offset = -1;
        }

        // se l'utente inserisce delle coordinate corrette
        if (rigaTarget + offset <= SETTE && rigaTarget + offset >= 0) {
            try {
                // se il pezzo mangiatore appartiene al giocatore in turno
                if (scacchiera.getScacchiera()[rigaTarget + offset][colMangiatore].getColor() == giocatore.toUpperCase()
                        .charAt(0)) {

                    if (valid) { // cattura normale
                        pezzo = scacchiera.getScacchiera()[rigaTarget][colTarget];

                        // verifico che la cattura sia possibile
                        esitoMossa = Ricerca.verificaCatturaDaPedone(rigaTarget, colTarget, giocatore, colMangiatore,
                                spostamento, scacchiera);

                        // se e' possibile l'en passant
                    } else if (scacchiera.getScacchiera()[rigaTarget + offset][colTarget] instanceof Pedone) {
                        pezzo = scacchiera.getScacchiera()[rigaTarget + offset][colTarget];
                        spostamento = 1;
                        Pedone p = (Pedone) scacchiera.getScacchiera()[rigaTarget + offset][colTarget];

                        // se il pedone da catturare appartiene all'avversario
                        if (giocatore.toUpperCase().charAt(0) != p.getColor()) {
                            esitoMossa = Ricerca.verificaCatturaDaPedone(rigaTarget, colTarget, giocatore,
                                    colMangiatore, spostamento, scacchiera);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }

        // Calcolo lo spostamento di riga e colonna in cui andare a prendere il pedone
        // che deve catturare il pezzo in base al giocatore
        if (esitoMossa) { // se e' possibile una cattura (semplice, tramite enpassant)

            GestoreStorico.aggiungiMossa(comando);
            GestoreStorico.aggiungiCattura(giocatore, pezzo); // aggiungo la cattura nell'elenco delle catture
            if (giocatore.equals("bianco")) {
                offsetRiga = rigaTarget + 1;
            } else {
                offsetRiga = rigaTarget - 1;
            }

            Pedone p;
            p = (Pedone) scacchiera.getScacchiera()[offsetRiga][colMangiatore];
            // se il pedone come prima mossa effettua una cattura, allora non sar� pi�
            // possibile per lui spostarsi di due
            if (p.isPrimaMossa()) {
                p.setPrimaMossa(false);
            }
            p.cattura(rigaTarget, colTarget, scacchiera, giocatore, colMangiatore); // effettua la cattura
            giocatore = Partita.getPlayer(); // passa il turno all'avversario
        } else {
            System.out.println("   Mossa illegale, cattura non consentita!");
        }
    }
}

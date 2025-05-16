package general;

import pezzi.*;

/**
 * Control class Gestisce lo spostamento di un pezzo in base al comando ricevuto
 */
public class Spostamento {

    private static final int COLRENERO = 3;
    private static final int RIGINB = 7;
    private static final int COLINB = 4;
    private static final int RIGINN = 0;
    private static final int COLINN = 4;
    private static final int POSARR = 6;
    private static final int COLPAS1AC = 5;
    private static final int COLPAS2AC = 6;
    private static final int TRE = 3;
    private static final int QUATTRO = 4;
    private static Pedone pPrec = null;
    private static int spostamento = 0;
    private String comando;
    private String giocatore;
    private Scacchiera scacchiera;

    /**
     * Costruttore
     *
     * @param comandoRicevuto    comando inserito dall'utente
     * @param giocatoreRicevuto  giocatore richiedente lo spostamento
     * @param scacchieraRicevuta scacchiera
     */
    public Spostamento(final String comandoRicevuto, final String giocatoreRicevuto,
            final Scacchiera scacchieraRicevuta) {
        this.comando = comandoRicevuto;
        this.giocatore = giocatoreRicevuto;
        this.scacchiera = scacchieraRicevuta;
        interpreta();
    }

    /**
     * Controlla se il re e' sotto scacco
     *
     * @param rigaRe     riga di destinazione
     * @param colonnaRe  colonna di destinazione
     * @param giocatore  richiedente spostamento del re
     * @param scacchiera scacchiera
     * @return sottoScacco true se le coordinate di destinazione portano il re sotto
     *         scacco, false altrimenti
     */
    public static boolean isSottoScacco(final int rigaRe, final int colonnaRe, final String giocatore,
            final Scacchiera scacchiera) {

        boolean sottoScacco = true;
        String tempGiocatore;

        if (giocatore.equals("bianco")) {

            tempGiocatore = "nero";
        } else {
            tempGiocatore = "bianco";
        }

        String alf = Ricerca.trovaDiagonale(rigaRe, colonnaRe, tempGiocatore, scacchiera, "Alfiere");
        String tor = Ricerca.trovaLato(rigaRe, colonnaRe, tempGiocatore, scacchiera, "Torre");
        String cav = Ricerca.trovaCavallo(rigaRe, colonnaRe, tempGiocatore, scacchiera);
        String donnaDiagonale = Ricerca.trovaDiagonale(rigaRe, colonnaRe, tempGiocatore, scacchiera, "Donna");
        String donnaLato = Ricerca.trovaLato(rigaRe, colonnaRe, tempGiocatore, scacchiera, "Donna");
        String re = Ricerca.trovaRe(rigaRe, colonnaRe, tempGiocatore, scacchiera);

        if ((alf == null && tor == null && cav == null && donnaDiagonale == null && donnaLato == null && re == null)) {
            sottoScacco = false;
        }
        return sottoScacco;
    }

    /**
     * Esegue lo spostamento del pezzo richiesto
     */
    public void interpreta() {

        switch (comando.charAt(0)) {
            case 'A':
                spostamentoAlfiere();
                break;
            case 'D':
                spostamentoDonna();
                break;
            case 'T':
                spostamentoTorre();
                break;
            case 'C':
                spostamentoCavallo();
                break;
            case 'R':
                spostamentoRe();
                break;
            default:
                if (comando.equals("0-0") || comando.equals("O-O")) {
                    spostamentoArrocco(0);
                } else if (comando.equals("0-0-0") || comando.equals("O-O-O")) {
                    spostamentoArrocco(1);
                } else {
                    spostamentoPedone();
                }
                break;
        }
    }

    /**
     * Esegue lo spostamento del re
     */
    public void spostamentoRe() {

        int spostamentoCol = Traduttore.traduciColonna(comando.charAt(1));
        int spostamentoRiga = Traduttore.traduciRiga(comando.charAt(2));
        String m;
        int rigaPart = 0;
        int colPart = 0;
        Re r = null;

        // se la casella di destinazione e' vuota e se non porta il Re in una posizione
        // sotto scacco
        if (scacchiera.getScacchiera()[spostamentoRiga][spostamentoCol] == null
                && !isSottoScacco(spostamentoRiga, spostamentoCol, giocatore, scacchiera)) {

            m = Ricerca.trovaRe(spostamentoRiga, spostamentoCol, giocatore, scacchiera); // ricavo le coordinate di
            // partenza del Re

            if (m == null) {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            } else {
                rigaPart = Integer.valueOf(m.substring(0, 1)); // riga di partenza del Re
                colPart = Integer.valueOf(m.substring(2)); // colonna di partenza del Re
                r = (Re) scacchiera.getScacchiera()[rigaPart][colPart];
                r.move(rigaPart, colPart, spostamentoRiga, spostamentoCol, scacchiera);
                System.out.println("   Mossa effettuata!");
                GestoreStorico.aggiungiMossa(comando); // aggiungo lo spostamento allo storico mosse

                if (giocatore.equals("bianco")) {
                    Partita.getRe()[0] = spostamentoRiga;
                    Partita.getRe()[1] = spostamentoCol;
                } else {
                    Partita.getRe()[2] = spostamentoRiga;
                    Partita.getRe()[COLRENERO] = spostamentoCol;
                }
                giocatore = Partita.getPlayer(); // passo il turno all'avversario
            }
        } else {
            System.out.println("   Mossa illegale, spostamento non consentito!");
        }

    }

    /**
     * Gestisce lo spostamento tramite arrocco
     *
     * @param tipo 0 se � arrocco corto, 1 se � arrocco lungo
     */
    public void spostamentoArrocco(final int tipo) {

        Re re1;
        int riga1 = RIGINB;
        int col1 = POSARR;

        if (verificaCondizioniArrocco(tipo)) {

            if (giocatore.equals("bianco")) {
                re1 = (Re) scacchiera.getScacchiera()[RIGINB][COLINB];
            } else {
                re1 = (Re) scacchiera.getScacchiera()[RIGINN][COLINN];
            }
            re1.moveArrocco(giocatore, scacchiera, tipo);
            System.out.println("   Mossa effettuata!");

            if (giocatore.equals("bianco")) {
                if (tipo == 0) {
                    Partita.getRe()[0] = riga1;
                    Partita.getRe()[1] = col1;
                } else {
                    Partita.getRe()[0] = riga1;
                    Partita.getRe()[1] = 2;
                }
            } else {
                if (tipo == 0) {
                    Partita.getRe()[2] = 0;
                    Partita.getRe()[COLRENERO] = col1;
                } else {
                    Partita.getRe()[2] = 0;
                    Partita.getRe()[COLRENERO] = 2;
                }
            }

            giocatore = Partita.getPlayer(); // passo il turno all'avversario
        } else {
            // SE LE CONDIZIONI NON SONO VERIFICATE
            System.out.println("   Mossa illegale, arrocco non consentito!");
        }

    }

    /**
     * Gestisce lo spostamento del cavallo
     */
    public void spostamentoCavallo() {

        int spostamentoCol = Traduttore.traduciColonna(comando.charAt(1));
        int spostamentoRig = Traduttore.traduciRiga(comando.charAt(2));
        String m;
        int rigaPart = 0;
        int colPart = 0;

        if (scacchiera.getScacchiera()[spostamentoRig][spostamentoCol] == null) {

            // calcolo di quanto si deve spostare il Cavallo e le sue coordinate di partenza
            m = Ricerca.trovaCavallo(spostamentoRig, spostamentoCol, giocatore, scacchiera);

            if (m == null) {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            } else {
                rigaPart = Integer.valueOf(m.substring(0, 1));
                colPart = Integer.valueOf(m.substring(2, TRE));
                spostamento = Integer.valueOf(m.substring(QUATTRO));
                Cavallo c = (Cavallo) scacchiera.getScacchiera()[rigaPart][colPart];

                if (c.move(rigaPart, colPart, spostamento, scacchiera)) { // se lo spostamento va a buon fine
                    System.out.println("   Mossa effettuata!");
                    GestoreStorico.aggiungiMossa(comando); // aggiungo lo spostamento allo storico mosse
                    giocatore = Partita.getPlayer(); // passo il turno all'avversario
                } else {
                    System.out.println("   Mossa illegale, spostamento non consentito!");
                }

            }
        } else {
            System.out.println("   Mossa illegale, spostamento non consentito!");
        }

    }

    /**
     * Gestisce lo spostamento dell'alfiere
     */
    public void spostamentoAlfiere() {

        int spostamentoCol = Traduttore.traduciColonna(comando.charAt(1));
        int spostamentoRiga = Traduttore.traduciRiga(comando.charAt(2));
        String m;
        int rigaPart = 0;
        int colPart = 0;
        Alfiere a = null;

        // calcolo di quanto si deve spostare l'Alfiere e le sue coordinate di partenza
        m = Ricerca.trovaDiagonale(spostamentoRiga, spostamentoCol, giocatore, scacchiera, "Alfiere");

        if (m == null) {
            System.out.println("   Mossa illegale, spostamento non consentito!");
        } else {
            rigaPart = Integer.valueOf(m.substring(0, 1));
            colPart = Integer.valueOf(m.substring(2, TRE));
            spostamento = Integer.valueOf(m.substring(QUATTRO));
            a = (Alfiere) scacchiera.getScacchiera()[rigaPart][colPart];

            if (a.move(rigaPart, colPart, spostamentoRiga, spostamentoCol, spostamento, scacchiera)) { // se lo
                // spostamento
                // va a buon
                // fine
                System.out.println("   Mossa effettuata!");
                GestoreStorico.aggiungiMossa(comando); // aggiungo lo spostamento allo storico mosse
                giocatore = Partita.getPlayer(); // passo il turno all'avversario
            } else {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            }

        }
    }

    /**
     * Gestisce lo spostamento della torre
     */
    public void spostamentoTorre() {

        int spostamentoCol = Traduttore.traduciColonna(comando.charAt(1));
        int spostamentoRiga = Traduttore.traduciRiga(comando.charAt(2));
        String m;
        int rigaPart = 0;
        int colPart = 0;
        Torre t = null;

        if (scacchiera.getScacchiera()[spostamentoRiga][spostamentoCol] == null) {

            // calcolo di quanto si deve spostare la Torre e le sue coordinate di partenza
            m = Ricerca.trovaLato(spostamentoRiga, spostamentoCol, giocatore, scacchiera, "Torre");

            if (m == null) {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            } else {
                rigaPart = Integer.valueOf(m.substring(0, 1));
                colPart = Integer.valueOf(m.substring(2, TRE));
                spostamento = Integer.valueOf(m.substring(QUATTRO));
                t = (Torre) scacchiera.getScacchiera()[rigaPart][colPart];

                if (t.move(rigaPart, colPart, spostamentoRiga, spostamentoCol, spostamento, scacchiera)) {
                    // se lo spostamento va a buon fine
                    System.out.println("   Mossa effettuata!");
                    GestoreStorico.aggiungiMossa(comando);
                    // aggiungo lo spostamento allo storico mosse

                    giocatore = Partita.getPlayer(); // passo il turno all'avversario
                } else {
                    System.out.println("   Mossa illegale, spostamento non consentito!");
                }
            }
        } else {
            System.out.println("   Mossa illegale, spostamento non consentito!");
        }

    }

    /**
     * Gestisce lo spostamento della donna
     */
    public void spostamentoDonna() {

        String m1, m2;
        int spostamentoCol = Traduttore.traduciColonna(comando.charAt(1));
        int spostamentoRiga = Traduttore.traduciRiga(comando.charAt(2));
        int rigaPart = 0;
        int colPart = 0;
        Donna d = null;

        m1 = Ricerca.trovaDiagonale(spostamentoRiga, spostamentoCol, giocatore, scacchiera, "Donna");
        // mi muovo in diagonale alla ricerca di una donna e delle sue coordinate

        m2 = Ricerca.trovaLato(spostamentoRiga, spostamentoCol, giocatore, scacchiera, "Donna");
        // mi muovo sui lati alla ricerca di una donna e delle sue coordinate

        if (m1 != null) { // se ho trovato la Donna in diagonale
            rigaPart = Integer.valueOf(m1.substring(0, 1));
            colPart = Integer.valueOf(m1.substring(2, TRE));
            spostamento = Integer.valueOf(m1.substring(QUATTRO));
            d = (Donna) scacchiera.getScacchiera()[rigaPart][colPart];

            if (d.moveDiagonale(rigaPart, colPart, spostamentoRiga, spostamentoCol, spostamento, scacchiera)) {
                // se lo spostamento va a buon fine
                System.out.println("   Mossa effettuata!");
                GestoreStorico.aggiungiMossa(comando); // aggiungo lo spostamento allo storico mosse
                giocatore = Partita.getPlayer(); // passo il turno all'avversario
            } else {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            }

        } else if (m2 != null) { // se ho trovato la Donna sui lati
            rigaPart = Integer.valueOf(m2.substring(0, 1));
            colPart = Integer.valueOf(m2.substring(2, TRE));
            spostamento = Integer.valueOf(m2.substring(QUATTRO));
            d = (Donna) scacchiera.getScacchiera()[rigaPart][colPart];

            if (d.moveLato(rigaPart, colPart, spostamentoRiga, spostamentoCol, spostamento, scacchiera)) {
                // se lo spostamento va a buon fine
                System.out.println("   Mossa effettuata!");
                GestoreStorico.aggiungiMossa(comando); // aggiungo lo spostamento allo storico mosse
                giocatore = Partita.getPlayer(); // passo il turno all'avversario
            } else {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            }

        } else {
            System.out.println("   Mossa illegale, spostamento non consentito!");
        }
    }

    /**
     * Gestisce lo spostamento del pedone
     */
    public void spostamentoPedone() {

        // Traduco notazione algebrica negli indici della matrice
        int offset = 0;
        int spostamentoCol = Traduttore.traduciColonna(comando.charAt(0));
        int spostamentoRiga = Traduttore.traduciRiga(comando.charAt(1));

        // se la posizione in cui ci si vuole spostare e' vuota
        if (scacchiera.getScacchiera()[spostamentoRiga][spostamentoCol] == null) {

            // calcolo di quanto si deve spostare il pedone
            spostamento = Ricerca.trovaPedone(spostamentoRiga, spostamentoCol, giocatore, scacchiera);
            Pedone p;

            // Calcolo la riga dalla quale parte il pedone che si vuole spostare
            if (giocatore.equals("bianco")) {
                if (spostamento == 1) {
                    offset = spostamentoRiga + 1;
                } else {
                    offset = spostamentoRiga + 2;
                }
            } else if (spostamento == 1) {
                offset = spostamentoRiga - 1;
            } else {
                offset = spostamentoRiga - 2;
            }

            // Sposto il pedone in base al tipo di mossa
            if (spostamento == 1 || spostamento == 2) { // se e' possibile lo spostamento

                p = (Pedone) scacchiera.getScacchiera()[offset][spostamentoCol];
                p.setPrimaMossa(false);

                if (pPrec != null) { // reset del valore di en Passant pedone precedente
                    pPrec.setEnPassant(false);
                }

                if (spostamento == 1) {
                    p.setEnPassant(false); // il pedone non potra' mai piu' essere catturato per enpassant
                } else {
                    p.setEnPassant(true); // il pedone puo' essere catturato tramite enpassant solo per questo turno
                    pPrec = (Pedone) p;
                }

                scacchiera.getScacchiera()[offset][spostamentoCol] = p; // dopo aver modificato il pedone, lo rimetto
                // nella
                // scacchiera
                if (p.move(spostamentoRiga, spostamentoCol, scacchiera, spostamento, giocatore)) { // se lo spostamento
                    // va
                    // a buon fine

                    System.out.println("   Mossa effettuata!");
                    GestoreStorico.aggiungiMossa(comando); // aggiungo lo spostamento allo storico mosse
                    giocatore = Partita.getPlayer();
                } else {
                    System.out.println("   Mossa illegale");
                }
            } else {
                System.out.println("   Mossa illegale, spostamento non consentito!");
            }
            offset = 0;

        } else {
            System.out.println("   Mossa illegale, spostamento non consentito!");
        }

    }

    /**
     * Verfica che ci siano le condizioni per arroccare
     */
    public boolean verificaCondizioniArrocco(final int tipoArrocco) {

        int rigaPartRe = 0;
        final int colPartRe = 4;
        int colPartTorre = RIGINB;
        int rigaPartTorre = 0;
        int passo1 = 0;
        int passo2 = 0;
        final int passo3 = 3;

        Torre torreTemp = null;
        Re reTemp = null;
        boolean risultatoCond = true;

        // Imposto gli indici in base al giocatore corrente e al tipo di arrocco

        if (giocatore.equals("bianco")) {
            rigaPartRe = RIGINB;
            rigaPartTorre = RIGINB;

        } else {
            rigaPartRe = 0;
            rigaPartTorre = 0;
        }

        if (tipoArrocco == 0) { // Se e' un arrocco corto
            colPartTorre = RIGINB;
            passo1 = COLPAS1AC;
            passo2 = COLPAS2AC;
        } else {
            colPartTorre = 0;
            passo1 = 1;
            passo2 = 2;
        }

        // CONDIZIONI DA RISPETTARE:
        // Individuo la torre e il re se esistono e sono in posizione iniziale

        if ((scacchiera.getScacchiera()[rigaPartTorre][colPartTorre] instanceof Torre)
                && (scacchiera.getScacchiera()[rigaPartRe][colPartRe] instanceof Re)) {

            torreTemp = (Torre) scacchiera.getScacchiera()[rigaPartTorre][colPartTorre];
            reTemp = (Re) scacchiera.getScacchiera()[rigaPartRe][colPartRe];
            risultatoCond = true;
        } else {
            risultatoCond = false;
        }

        // Il giocatore non ha mai mosso il re, ne' la torre

        risultatoCond = risultatoCond && (reTemp.isPrimaMossa() && torreTemp.isPrimaMossa());

        // Non ci sono pezzi tra il re e la torre

        risultatoCond = risultatoCond && (scacchiera.getScacchiera()[rigaPartRe][passo1] == null);
        risultatoCond = risultatoCond && (scacchiera.getScacchiera()[rigaPartRe][passo2] == null);
        if (tipoArrocco == 1) {
            risultatoCond = risultatoCond && (scacchiera.getScacchiera()[rigaPartRe][1] == null);
        }

        // Il re non e' sotto scacco

        risultatoCond = risultatoCond && (!isSottoScacco(rigaPartRe, colPartRe, giocatore, scacchiera));

        // Il re non attraversa case dove sarebbe sotto scacco

        risultatoCond = risultatoCond && (!isSottoScacco(rigaPartRe, passo1, giocatore, scacchiera));
        risultatoCond = risultatoCond && (!isSottoScacco(rigaPartRe, passo2, giocatore, scacchiera));
        if (tipoArrocco == 1) {
            risultatoCond = risultatoCond && (!isSottoScacco(rigaPartRe, passo3, giocatore, scacchiera));
        }
        return risultatoCond;
    }

}

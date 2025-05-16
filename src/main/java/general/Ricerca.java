package general;

import pezzi.Pedone;

/**
 * noECB
 * Ricerca il pezzo sulla scacchiera
 */
public final class Ricerca {

    static final int TRE = 3;
    static final int QUATTRO = 4;
    static final int CINQUE = 5;
    static final int SEI = 6;
    static final int SETTE = 7;
    static final int OFFSET = 2;

    /**
     * Costruttore
     */
    private Ricerca() {
        // non chiamato
    }

    /**
     * Gestisce la ricerca del re
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param player           giocatore richiedente spostamento(o cattura)
     * @param s                scacchiera
     * @return m coordinate di partenza del pezzo da spostare
     */
    public static String trovaRe(final int rigaDestinazione, final int colDestinazione, final String player,
            final Scacchiera s) {

        int rigaDest = rigaDestinazione;
        int colDest = colDestinazione;
        String giocatore = player;
        Scacchiera scacchiera = s;

        final int t = 8;

        String m = null;
        int x = 0, y = 0;

        for (int j = 0; j < t; j++) {
            switch (j) {
                case 0:
                    x = rigaDest + 1;
                    y = colDest + 1;
                    break;
                case 1:
                    x = rigaDest + 1;
                    y = colDest;
                    break;
                case 2:
                    x = rigaDest + 1;
                    y = colDest - 1;
                    break;
                case TRE:
                    x = rigaDest;
                    y = colDest - 1;
                    break;
                case QUATTRO:
                    x = rigaDest - 1;
                    y = colDest - 1;
                    break;
                case CINQUE:
                    x = rigaDest - 1;
                    y = colDest;
                    break;
                case SEI:
                    x = rigaDest - 1;
                    y = colDest + 1;
                    break;
                case SETTE:
                    x = rigaDest;
                    y = colDest + 1;
                    break;
                default:
            }
            m = Movimento.adiacente(giocatore, scacchiera, x, y, j);

            if (m != null) { // se viene trovato un pezzo valido
                break; // concludo la ricerca
            }

        }
        return m;
    }

    /**
     * Trova il pezzo da spostare lungo le diagonali
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param player           giocatore richiedente spostamento(o cattura)
     * @param s                scacchiera
     * @param p                pezzo richiedente spostamento in diagonale
     * @return m coordinate di partenza del pezzo da spostare
     */
    public static String trovaDiagonale(final int rigaDestinazione, final int colDestinazione, final String player,
            final Scacchiera s, final String p) {

        int rigaDest = rigaDestinazione;
        int colDest = colDestinazione;
        String giocatore = player;
        Scacchiera scacchiera = s;
        String pezzo = p;

        String m = null;
        int x = 0, y = 0;
        final int z = 4;

        for (int j = 0; j < z; j++) { // provo tutte le diagonali possibili

            switch (j) {

                // suppongo che il pezzo si voglia muovere verso l'estremita' alta destra
                // della
                // scacchiera
                case 0:
                    x = rigaDest + 1;
                    y = colDest - 1;
                    break;

                // suppongo che il pezzo si voglia muovere verso l'estremita' alta sinistra
                // della scacchiera
                case 1:
                    x = rigaDest + 1;
                    y = colDest + 1;
                    break;

                // suppongo che il pezzo si voglia muovere verso l'estremita' bassa destra
                // della
                // scacchiera
                case 2:
                    x = rigaDest - 1;
                    y = colDest - 1;
                    break;

                // suppongo che il pezzo si voglia muovere verso l'estremita' bassa sinistra
                // della scacchiera
                case TRE:
                    x = rigaDest - 1;
                    y = colDest + 1;
                    break;
                default:
            }
            m = Movimento.diagonale(rigaDest, colDest, giocatore, scacchiera, x, y, j, pezzo);
            if (m != null) { // se viene trovato un pezzo valido
                break; // concludo la ricerca
            }
        }

        return m;
    }

    /**
     * Trova il cavallo da spostare
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param player           giocatore richiedente spostamento(o cattura)
     * @param s                scacchiera
     * @return m coordinate di partenza del pezzo da spostare
     */
    public static String trovaCavallo(final int rigaDestinazione, final int colDestinazione, final String player,
            final Scacchiera s) {

        int rigaDest = rigaDestinazione;
        int colDest = colDestinazione;
        String giocatore = player;
        Scacchiera scacchiera = s;
        String m = null;
        int x = 0, y = 0;
        final int c = 8;

        for (int j = 0; j < c; j++) { // provo tutte le direzioni possibili

            switch (j) {

                case 0:
                    x = rigaDest + 1;
                    y = colDest + 2;
                    break;
                case 1:
                    x = rigaDest + 1;
                    y = colDest - 2;
                    break;
                case 2:
                    x = rigaDest + 2;
                    y = colDest + 1;
                    break;
                case TRE:
                    x = rigaDest - 2;
                    y = colDest + 1;
                    break;
                case QUATTRO:
                    x = rigaDest - 1;
                    y = colDest + 2;
                    break;
                case CINQUE:
                    x = rigaDest - 1;
                    y = colDest - 2;
                    break;
                case SEI:
                    x = rigaDest - 2;
                    y = colDest - 1;
                    break;
                case SETTE:
                    x = rigaDest + 2;
                    y = colDest - 1;
                    break;
                default:
                    break;
            }

            m = Movimento.direzioneCavallo(giocatore, scacchiera, x, y, j);
            if (m != null) { // se viene trovato un Cavallo valido
                break; // concludo la ricerca
            }
        }

        return m;
    }

    /**
     * Trova il pedone da spostare
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param player           giocatore richiedente spostamento(o cattura)
     * @param s                scacchiera
     * @return spostamento di quanto deve spostarsi il pedone
     */
    public static int trovaPedone(final int rigaDestinazione, final int colDestinazione, final String player,
            final Scacchiera s) {

        int rigaDest = rigaDestinazione;
        int colDest = colDestinazione;
        String giocatore = player;
        Scacchiera scacchiera = s;

        int spostamento = 0;
        int offset = 0;
        int offset2 = 0;

        if (giocatore.equals("bianco")) {
            offset = 1;
            offset2 = 2;
        } else {
            offset = -1;
            offset2 = -OFFSET;
        }

        try {
            // se il pezzo nella riga precedente, che si vuole spostare, e' un Pedone
            if (scacchiera.getScacchiera()[rigaDest + offset][colDest] instanceof Pedone) {

                // se il pedone da spostare appartiene al giocatore in turno
                if (scacchiera.getScacchiera()[rigaDest + offset][colDest].getColor() == giocatore.toUpperCase()
                        .charAt(0)) {
                    spostamento = 1;
                }

                // altrimenti verifico che due righe prima ci sia un Pedone
            } else if (scacchiera.getScacchiera()[rigaDest + offset2][colDest] instanceof Pedone) {

                // se il Pedone da spostare appartiene al giocatore in turno
                if (scacchiera.getScacchiera()[rigaDest + offset2][colDest].getColor() == giocatore.toUpperCase()
                        .charAt(0)) {
                    Pedone p = (Pedone) scacchiera.getScacchiera()[rigaDest + offset2][colDest];

                    if (p.isPrimaMossa()) { // se il Pedone non si e' gia' precedentemente spostato di due righe
                        spostamento = 2;
                    } else {
                        System.out.println("   Hai gia' utilizzato la prima mossa per questo pedone");

                    }
                }

            }
        } catch (Exception e) {

        }

        return spostamento;
    }

    /**
     * Controlla se e' possibile effettuare la cattura del target dal Pedone
     * specificato
     *
     * @param rigaT         riga del pezzo da mangiare
     * @param colT          colonna del pezzo da mangiare
     * @param player        richiedente cattura
     * @param colMangiatore colonna del pedone che deve effettuare la cattura
     * @param cod           0 se è una cattura semplice, 1 se è per en passant
     * @param s             scacchiera
     * @return esito true se la cattura è possibile, false altrimenti
     */
    public static boolean verificaCatturaDaPedone(final int rigaT, final int colT, final String player,
            final int colMangiatore, final int cod, final Scacchiera s) {

        int rigaTarget = rigaT;
        int colTarget = colT;
        String giocatore = player;
        int colonnaMangiatore = colMangiatore;
        int codice = cod;
        Scacchiera scacchiera = s;
        boolean esito = false;
        int offset = 0;

        // Imposto gli offset della riga in base al giocatore
        if (giocatore.equals("bianco")) {
            offset = 1;
        } else {
            offset = -1;
        }

        if (codice == 0) { // se si tratta di una cattura normale

            // se la colonna del Pedone da mangiare, e' adiacente a quella del Pedone
            // mangiatore
            boolean possibile = (colonnaMangiatore == colTarget - 1) || (colonnaMangiatore == colTarget + 1);

            // se il pezzo "mangiatore" e' un Pedone
            if (scacchiera.getScacchiera()[rigaTarget + offset][colonnaMangiatore] instanceof Pedone && possibile) {
                esito = true;
            }

        } else if (codice == 1) { // se e' una cattura per enpassant
            boolean possibile = (colonnaMangiatore == colTarget - 1) || (colonnaMangiatore == colTarget + 1);

            if (possibile) {
                esito = Pedone.enPassant(rigaTarget, colTarget, giocatore, scacchiera);
            }

        }
        return esito;
    }

    /**
     * Trova il pezzo da spostare lungo i lati
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param player           giocatore richiedente spostamento(o cattura)
     * @param s                scacchiera
     * @param p                pezzo richiedente spostamento lungo i lati
     * @return m coordinate di partenza del pezzo da spostare
     */
    public static String trovaLato(final int rigaDestinazione, final int colDestinazione, final String player,
            final Scacchiera s, final String p) {

        final int nLati = 4;
        int rigaDest = rigaDestinazione;
        int colDest = colDestinazione;
        String giocatore = player;
        Scacchiera scacchiera = s;
        String pezzo = p;
        String m = null;
        int x = 0, y = 0;
        for (int j = 0; j < nLati; j++) { // provo tutte le direzioni possibili

            switch (j) {
                // suppongo che il pezzo si vuole muovere verso l'alto
                case 0:
                    x = rigaDest + 1;
                    y = colDest;
                    break;
                // suppongo che il pezzo si vuole muovere verso il basso
                case 1:
                    x = rigaDest - 1;
                    y = colDest;
                    break;
                // suppongo che il pezzo si vuole muovere verso destra
                case 2:
                    x = rigaDest;
                    y = colDest - 1;
                    break;
                // suppongo che il pezzo si vuole muovere verso sinistra
                case TRE:
                    x = rigaDest;
                    y = colDest + 1;
                    break;
                default:
                    break;
            }

            m = Movimento.direzione(rigaDest, colDest, giocatore, scacchiera, x, y, j, pezzo);
            if (m != null) { // se viene trovato un pezzo valido
                break; // concludo la ricerca
            }
        }
        return m;
    }
}

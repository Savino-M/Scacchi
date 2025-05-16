package pezzi;

import general.Movimento;
import general.Scacchiera;

/**
 * Entity Class
 * Gestisce la Donna
 */
public class Donna extends Pezzo {

    private static final int TRE = 3;

    /**
     * Costruttore
     *
     * @param colore il colore della donna
     */
    public Donna(final char colore) {
        setColore(colore);
    }

    /**
     * Sposta la donna in diagonale
     *
     * @param rigaPart    riga di partenza
     * @param colPart     colonna di partenza
     * @param rigaDest    riga di destinazione
     * @param colDest     colonna di destinazione
     * @param spostamento di quante caselle si deve spostare
     * @param s           scacchiera
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public boolean moveDiagonale(final int rigaPart, final int colPart, final int rigaDest, final int colDest,
            final int spostamento, final Scacchiera s) {

        boolean esito = false;
        int x, y, caso;

        // in base alla posizione delle coordinate 'target' e di quelle di partenza, mi
        // muovo in un determinato modo in diagonale
        if (rigaDest > rigaPart) {
            if (colDest > colPart) {
                x = rigaPart + 1;
                y = colPart + 1;
                caso = 0;

            } else {
                x = rigaPart + 1;
                y = colPart - 1;
                caso = 1;
            }
        } else {
            if (colDest > colPart) {
                x = rigaPart - 1;
                y = colPart + 1;
                caso = 2;

            } else {
                x = rigaPart - 1;
                y = colPart - 1;
                caso = TRE;
            }
        }

        esito = Movimento.spostaDiagonale(rigaPart, colPart, spostamento, s, x, y, caso, false, "Donna");
        return esito;
    }

    /**
     * Effettua la cattura in diagonale
     *
     * @param rigaPart    riga di partenza
     * @param colPart     colonna di partenza
     * @param rigaTarget  riga di destinazione
     * @param colTarget   colonna di destinazione
     * @param spostamento di quante caselle si deve spostare
     * @param s           scacchiera
     * @return esito true se la cattura va a buon fine, false altrimenti
     */
    public boolean catturaDiagonale(final int rigaPart, final int colPart, final int rigaTarget, final int colTarget,
            final int spostamento, final Scacchiera s) {

        boolean esito = false;
        int x, y, caso;

        if (rigaTarget > rigaPart) {
            if (colTarget > colPart) {
                x = rigaPart + 1;
                y = colPart + 1;
                caso = 0;

            } else {

                x = rigaPart + 1;
                y = colPart - 1;
                caso = 1;

            }
        } else {
            if (colTarget > colPart) {

                x = rigaPart - 1;
                y = colPart + 1;
                caso = 2;

            } else {

                x = rigaPart - 1;
                y = colPart - 1;
                caso = TRE;
            }
        }

        esito = Movimento.spostaDiagonale(rigaPart, colPart, spostamento, s, x, y, caso, true, "Donna");
        return esito;
    }

    /**
     * Sposta la donna sui lati
     *
     * @param rigaPart    riga di partenza
     * @param colPart     colonna di partenza
     * @param rigaDest    riga di destinazione
     * @param colDest     colonna di destinazione
     * @param spostamento di quante caselle si deve spostare
     * @param s           scacchiera
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public boolean moveLato(final int rigaPart, final int colPart, final int rigaDest, final int colDest,
            final int spostamento, final Scacchiera s) {

        boolean esito = false;
        int x = 0, y = 0, caso = 0;

        // in base alla posizione delle coordinate 'target' e di quelle di partenza, mi
        // muovo in un determinato lato
        if (colDest == colPart) {
            if (rigaDest < rigaPart) {
                y = colDest;
                x = rigaPart - 1;
                caso = 0;

            } else {
                y = colDest;
                x = rigaPart + 1;
                caso = 1;
            }
        }

        if (rigaDest == rigaPart) {
            if (colDest > colPart) {
                x = rigaDest;
                y = colPart + 1;
                caso = 2;

            } else {
                x = rigaDest;
                y = colPart - 1;
                caso = TRE;
            }
        }

        esito = Movimento.spostaLato(rigaPart, colPart, spostamento, s, x, y, caso, false, "Donna");
        return esito;
    }

    /**
     * Effettua la cattura sui lati
     *
     * @param rigaPart    riga di partenza
     * @param colPart     colonna di partenza
     * @param rigaTarget  riga di destinazione
     * @param colTarget   colonna di destinazione
     * @param spostamento di quante caselle si deve spostare
     * @param s           scacchiera
     * @return esito true se la cattura va a buon fine, false altrimenti
     */
    public boolean catturaLato(final int rigaPart, final int colPart, final int rigaTarget, final int colTarget,
            final int spostamento, final Scacchiera s) {

        boolean esito = false;
        int x = 0, y = 0, caso = 0;

        if (colTarget == colPart) {
            if (rigaTarget < rigaPart) {
                y = colTarget;
                x = rigaPart - 1;
                caso = 0;
            } else {
                y = colTarget;
                x = rigaPart + 1;
                caso = 1;
            }
        }

        if (rigaTarget == rigaPart) {
            if (colTarget > colPart) {
                x = rigaTarget;
                y = colPart + 1;
                caso = 2;

            } else {
                x = rigaTarget;
                y = colPart - 1;
                caso = TRE;
            }
        }
        esito = Movimento.spostaLato(rigaPart, colPart, spostamento, s, x, y, caso, true, "Donna");
        return esito;
    }

    /**
     * Modifica il codice unicode del pezzo in base al suo colore
     */
    @Override
    public String getUniCode() {
        if (getColor() == 'N') {
            setCode("\u265B");
        } else {
            setCode("\u2655");
        }
        return getCode();
    }

}

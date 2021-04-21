package pezzi;

import general.Movimento;
import general.Scacchiera;

/**
 * Entity Class
 * Gestisce l'Alfiere
 */
public class Alfiere extends Pezzo
{

    private static final int TRE = 3;

    /**
     * Costruttore
     *
     * @param colore il colore dell'Alfiere
     */
    public Alfiere(final char colore)
    {
        this.setColore(colore);
    }

    /**
     * Costruttore vuoto
     */
    public Alfiere()
    {
    }

    /**
     * Sposta l'Alfiere
     *
     * @param rigaPart    riga di partenza
     * @param colPart     colonna di partenza
     * @param rigaDest    riga di destinazione
     * @param colDest     colonna di destinazione
     * @param spostamento di quante caselle si deve spostare
     * @param s           scacchiera
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public boolean move(final int rigaPart, final int colPart, final int rigaDest, final int colDest,
                        final int spostamento, final Scacchiera s)
    {

        boolean esito = false;
        int x, y, caso;

        // in base alla posizione delle coordinate 'target' e di quelle di partenza, mi
        // muovo in un determinato modo in diagonale
        if (rigaDest > rigaPart)
        {
            if (colDest > colPart)
            {
                x = rigaPart + 1;
                y = colPart + 1;
                caso = 0;

            } else
            {
                x = rigaPart + 1;
                y = colPart - 1;
                caso = 1;
            }
        } else
        {
            if (colDest > colPart)
            {
                x = rigaPart - 1;
                y = colPart + 1;
                caso = 2;

            } else
            {
                x = rigaPart - 1;
                y = colPart - 1;
                caso = TRE;
            }
        }

        esito = Movimento.spostaDiagonale(rigaPart, colPart, spostamento, s, x, y, caso, false, "Alfiere");
        return esito;
    }

    /**
     * Effettua la cattura da parte dell'Alfiere
     *
     * @param rigaPart    riga di partenza
     * @param colPart     colonna di partenza
     * @param rigaTarget  riga di destinazione
     * @param colTarget   colonna di destinazione
     * @param spostamento di quante caselle si deve spostare
     * @param s           scacchiera
     * @return esito true se la cattura va a buon fine, false altrimenti
     */
    public boolean cattura(final int rigaPart, final int colPart, final int rigaTarget, final int colTarget,
                           final int spostamento, final Scacchiera s)
    {

        boolean esito = false;
        int x, y, caso;

        if (rigaTarget > rigaPart)
        {
            if (colTarget > colPart)
            {
                x = rigaPart + 1;
                y = colPart + 1;
                caso = 0;

            } else
            {

                x = rigaPart + 1;
                y = colPart - 1;
                caso = 1;

            }
        } else
        {
            if (colTarget > colPart)
            {

                x = rigaPart - 1;
                y = colPart + 1;
                caso = 2;

            } else
            {

                x = rigaPart - 1;
                y = colPart - 1;
                caso = TRE;
            }
        }

        esito = Movimento.spostaDiagonale(rigaPart, colPart, spostamento, s, x, y, caso, true, "Alfiere");
        return esito;
    }

    /**
     * Modifica il codice unicode del pezzo in base al suo colore
     */
    @Override
    public String getUniCode()
    {
        if (getColor() == 'N')
        {
            setCode("\u265D");
        } else
        {
            setCode("\u2657");
        }
        return getCode();
    }

}

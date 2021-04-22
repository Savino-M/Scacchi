package pezzi;

import general.Scacchiera;

/**
 * Entity Class
 * Gestisce il Pedone
 */
public class Pedone extends Pezzo
{

    private boolean primaMossa;
    private boolean enPassant = false;

    /**
     * Costruttore
     *
     * @param colore il colore del pedone
     */
    public Pedone(final char colore)
    {
        primaMossa = true;
        this.setColore(colore);
    }

    /**
     * Restituisce la variabile primaMossa
     *
     * @return primaMossa true se il pedone deve ancora effettuare la prima mossa, false altrimenti
     */
    public boolean isPrimaMossa()
    {
        return primaMossa;
    }

    /**
     * Restituisce la variabile isEnPassant
     *
     * @return enPassant restituisce true se il pedone pu� eseguire una cattura per en passant, false altrimenti
     */
    public boolean isEnPassant()
    {
        return enPassant;
    }

    /**
     * Modifica la variabile primaMossa
     *
     * @param newPrimaMossa il nuovo valore della variabile primaMossa
     */
    public void setPrimaMossa(final boolean newPrimaMossa)
    {
        this.primaMossa = newPrimaMossa;

    }

    /**
     * Modifica la variabile enPassant
     *
     * @param newEnPassant il nuovo valore della variabile enPassant
     */
    public void setEnPassant(final boolean newEnPassant)
    {
        this.enPassant = newEnPassant;

    }

    /**
     * Verifica se il pedone che voglio catturare ha appena fatto il passo da due
     *
     * @param rigaTarget riga del pezzo da catturare
     * @param colTarget  colonna del pezzo da catturare
     * @param giocatore  richiedente cattura
     * @param scacchiera scacchiera
     * @return esito true se il pedone ha appena fatto il passo da due, false altrimenti
     */
    public static boolean enPassant(final int rigaTarget, final int colTarget,
                                    final String giocatore, final Scacchiera scacchiera)
    {

        boolean esito = false;
        int offset;

        if (giocatore.equals("bianco"))
        {
            offset = 1;
        }
        else
        {
            offset = -1;
        }

        Pedone p = (Pedone) scacchiera.getScacchiera()[rigaTarget + offset][colTarget];
        if (p.isEnPassant())
        { // se il pedone si e' appena spostato di due righe al turno precedente
            esito = true;
            p.setEnPassant(false);
        }
        return esito;

    }

    /**
     * Sposta il pedone
     *
     * @param rigaDest    riga di destinazione
     * @param colonnaDest colonna di destinazione
     * @param s           scacchiera
     * @param tipo        di quanto spostarsi
     * @param giocatore   richiedente spostamento
     * @return valid true se lo spostamento va a buon fine, false altrimenti
     */
    public boolean move(final int rigaDest, final int colonnaDest, final Scacchiera s,
                        final int tipo, final String giocatore)
    {

        Pezzo temp;
        boolean valid = false;

        if (tipo == 1)
        { // spostamento di una riga
            if (giocatore.equals("nero"))
            {
                temp = s.getScacchiera()[rigaDest - 1][colonnaDest];
                s.getScacchiera()[rigaDest][colonnaDest] = temp;
                s.getScacchiera()[rigaDest - 1][colonnaDest] = null;
            }
            else
            {
                temp = s.getScacchiera()[rigaDest + 1][colonnaDest];
                s.getScacchiera()[rigaDest][colonnaDest] = temp;
                s.getScacchiera()[rigaDest + 1][colonnaDest] = null;
            }
            valid = true;
        }
        else
        { // spostamento di due righe

            if (giocatore.equals("nero"))
            {
                if (s.getScacchiera()[rigaDest - 1][colonnaDest] == null)
                {
                    temp = s.getScacchiera()[rigaDest - 2][colonnaDest];
                    s.getScacchiera()[rigaDest][colonnaDest] = temp;
                    s.getScacchiera()[rigaDest - 2][colonnaDest] = null;
                    valid = true;
                }
            }
            else
            {
                if (s.getScacchiera()[rigaDest + 1][colonnaDest] == null)
                {
                    temp = s.getScacchiera()[rigaDest + 2][colonnaDest];
                    s.getScacchiera()[rigaDest][colonnaDest] = temp;
                    s.getScacchiera()[rigaDest + 2][colonnaDest] = null;
                    valid = true;
                }

            }

        }
        return valid;
    }

    /**
     * Effettua una cattura da parte del pedone
     *
     * @param rigaTarget    riga del pezzo da catturare
     * @param colTarget     colonna del pezzo da catturare
     * @param s             scacchiera
     * @param giocatore     richiedente cattura
     * @param colMangiatore colonna del pezzo mangiatore
     */
    public void cattura(final int rigaTarget, final int colTarget, final Scacchiera s,
                        final String giocatore, final int colMangiatore)
    {

        int offset = 0;

        if (giocatore.equals("bianco"))
        {
            offset = 1;
        }
        else
        {
            offset = -1;
        }

        if (s.getScacchiera()[rigaTarget][colTarget] == null)
        { // se e' una cattura per enpassant
            s.getScacchiera()[rigaTarget + offset][colTarget] = null; // rimuovo il pedone da mangiare
        }

        // sposto il pedone "mangiatore" nella colonna del target
        s.getScacchiera()[rigaTarget][colTarget] = s.getScacchiera()[rigaTarget + offset][colMangiatore];

        // libero la posizione del pedone "mangiatore"
        s.getScacchiera()[rigaTarget + offset][colMangiatore] = null;
        System.out.println("   Hai eseguito una cattura!");

    }

    /**
     * Modifica il codice unicode del pezzo in base al suo colore
     */
    @Override
    public String getUniCode()
    {

        if (getColor() == 'N')
        {
            setCode("\u265F");
        }
        else
        {
            setCode("\u2659");
        }

        return getCode();
    }

}

package pezzi;

import general.Movimento;
import general.Scacchiera;

/**
 * Entity Class
 * Gestisce il Cavallo
 */
public class Cavallo extends Pezzo {

    private static final int CASO3 = 3;
    private static final int CASO4 = 4;
    private static final int CASO5 = 5;
    private static final int CASO6 = 6;
    private static final int CASO7 = 7;

    /**
     * Costruttore
     *
     * @param colore il colore del cavallo
     */
    public Cavallo(final char colore) {
        this.setColore(colore);
    }

    /**
     * Metodo che gestisce il movimento del Cavallo
     *
     * @param rigaPartenza    riga di partenza
     * @param colonnaPartenza colonna di partenza
     * @param spost           di quante caselle bisogna spostarsi
     * @param scacchieraRic   scacchiera
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public boolean move(final int rigaPartenza, final int colonnaPartenza, final int spost,
            final Scacchiera scacchieraRic) {

        int rigaPart = rigaPartenza;
        int colonnaPart = colonnaPartenza;
        int spostamento = spost;
        Scacchiera s = scacchieraRic;
        boolean esito = false;
        int x = 0, y = 0;

        switch (spostamento) {
            case 0:
                x = rigaPart - 1;
                y = colonnaPart - 2;
                break;
            default:
            case 1:
                x = rigaPart - 1;
                y = colonnaPart + 2;
                break;
            case 2:
                x = rigaPart - 2;
                y = colonnaPart - 1;
                break;
            case CASO3:
                x = rigaPart + 2;
                y = colonnaPart - 1;
                break;
            case CASO4:
                x = rigaPart + 1;
                y = colonnaPart - 2;
                break;
            case CASO5:
                x = rigaPart + 1;
                y = colonnaPart + 2;
                break;
            case CASO6:
                x = rigaPart + 2;
                y = colonnaPart + 1;
                break;
            case CASO7:
                x = rigaPart - 2;
                y = colonnaPart + 1;
                break;

        }
        esito = Movimento.spostaCavallo(rigaPart, colonnaPart, s, x, y, false);
        return esito;
    }

    /**
     * Metodo che gestisce la cattura del Cavallo
     *
     * @param rigaPartenza  riga di partenza
     * @param colPartenza   colonna di partenza
     * @param spost         di quante caselle bisogna spostarsi
     * @param scacchieraRic scacchiera
     * @return esito true se la cattura va a buon fine, false altrimenti
     */
    public boolean cattura(final int rigaPartenza, final int colPartenza, final int spost,
            final Scacchiera scacchieraRic) {

        int rigaPart = rigaPartenza;
        int colPart = colPartenza;
        int spostamento = spost;
        Scacchiera s = scacchieraRic;
        boolean esito = false;
        int x = 0, y = 0;

        switch (spostamento) {
            case 0:
                x = rigaPart - 1;
                y = colPart - 2;
                break;
            case 1:
                x = rigaPart - 1;
                y = colPart + 2;
                break;
            case 2:
                x = rigaPart - 2;
                y = colPart - 1;
                break;
            case CASO3:
                x = rigaPart + 2;
                y = colPart - 1;
                break;
            case CASO4:
                x = rigaPart + 1;
                y = colPart - 2;
                break;
            case CASO5:
                x = rigaPart + 1;
                y = colPart + 2;
                break;
            case CASO6:
                x = rigaPart + 2;
                y = colPart + 1;
                break;
            case CASO7:
                x = rigaPart - 2;
                y = colPart + 1;
                break;
            default:
        }

        esito = Movimento.spostaCavallo(rigaPart, colPart, s, x, y, true);
        return esito;
    }

    /**
     * Modifica il codice Unicode del pezzo in base al suo colore
     */
    @Override
    public String getUniCode() {
        if (getColor() == 'N') {
            setCode("\u265E");
        } else {
            setCode("\u2658");
        }
        return getCode();
    }

}

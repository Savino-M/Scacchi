package pezzi;

import general.Movimento;
import general.Scacchiera;

/**
 * Entity Class
 * Gestisce la Torre
 */
public class Torre extends Pezzo {

    private boolean primaMossa;
    private static final int CASO3 = 3;

    /**
     * Costruttore
     * @param colore il colore della torre
     */
    public Torre(final char colore) {
	this.setColore(colore);
	primaMossa = true;
    }

    /**
     * Restituisce il valore della variabile primaMossa
     * @return primaMossa true se la torre deve ancora effettuare la prima mossa, false altrimenti
     */
    public boolean isPrimaMossa() {
	return primaMossa;
    }

    /**
     * Sposta la torre
     * @param rigaPartenza riga di partenza
     * @param colPartenza colonna di partenza
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione colonna di destinazione
     * @param movement di quante caselle si deve spostare
     * @param scacchiera scacchiera
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public boolean move(final int rigaPartenza, final int colPartenza, final int rigaDestinazione,
	    final int colDestinazione, final int movement, final Scacchiera scacchiera) {

	int rigaPart = rigaPartenza;
	int colPart = colPartenza;
	int rigaDest = rigaDestinazione;
	int colDest = colDestinazione;
	int spostamento = movement;
	Scacchiera s = scacchiera;


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
		caso = CASO3;
	    }
	}

	esito = Movimento.spostaLato(rigaPart, colPart, spostamento, s, x, y, caso, false, "Torre");
	return esito;
    }

    /**
     * Effettua la cattura da parte della torre
     * @param rigaPartenza riga di partenza
     * @param colPartenza colonna di partenza
     * @param rigaTarget riga di destinazione
     * @param colTarget colonna di destinazione
     * @param movement di quante caselle si deve spostare
     * @param scacchiera scacchiera
     * @return esito true se la cattura va a buon fine, false altrimenti
     */
    public boolean cattura(final int rigaPartenza, final int colPartenza, final int rigaTarget,
	    final int colTarget, final int movement, final Scacchiera scacchiera) {

	int rigaPart = rigaPartenza;
	int colPart = colPartenza;
	int rigaDest = rigaTarget;
	int colDest = colTarget;
	int spostamento = movement;
	Scacchiera s = scacchiera;

	boolean esito = false;
	int x = 0, y = 0, caso = 0;

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
		caso = CASO3;
	    }
	}
	esito = Movimento.spostaLato(rigaPart, colPart, spostamento, s, x, y, caso, true, "Torre");
	return esito;
    }

    /**
     * Modifica il codice unicode del pezzo in base al colore
     */
    @Override
    public String getUniCode() {
	if (getColor() == 'N') {
	    setCode("\u265C");
	} else {
	    setCode("\u2656");
	}
	return getCode();
    }

}

package pezzi;

import general.Scacchiera;

/**
 * Entity Class
 * Gestisce il Re
 */
public class Re extends Pezzo {
    private static final int CPARTRE = 4;
    private static final int CDESTRE = 6;
    private static final int CPARTORRE = 7;
    private static final int CDESTORRE = 5;
    private static final int RPARTRE = 7;
    private static final int RPARTORRE = 7;
    private static final int TORREARR = 3;
    private boolean primaMossa;

    /**
     * Costruttore
     * @param colore il colore del re
     */
    public Re(final char colore) {
	primaMossa = true;
	this.setColore(colore);
    }

    /**
     * Muove il Re
     * @param rigaPartenza riga di partenza del re
     * @param colPartenza colonna di partenza del re
     * @param rigaDestinazione riga di destinazione del re
     * @param colDestinazione colonna di destinazione del re
     * @param scacchieraRic scacchiera
     */
    public void move(final int rigaPartenza, final int colPartenza, final int rigaDestinazione,
	    final int colDestinazione, final Scacchiera scacchieraRic) {
	int rigaPart = rigaPartenza;
	int colPart = colPartenza;
	int rigaDest = rigaDestinazione;
	int colDest = colDestinazione;
	Scacchiera s = scacchieraRic;
	s.getScacchiera()[rigaDest][colDest] = s.getScacchiera()[rigaPart][colPart];
	s.getScacchiera()[rigaPart][colPart] = null;
    }

    /**
     * Effettua una cattura da parte del Re
     * @param rigaPartenza riga di partenza del re
     * @param colPartenza colonna di partenza del re
     * @param rigaTarg riga del pezzo da catturare
     * @param colTarg colonna del pezzo da catturare
     * @param scacchieraRic scacchiera
     */
    public void cattura(final int rigaPartenza, final int colPartenza, final int rigaTarg, final int colTarg,
	    final Scacchiera scacchieraRic) {
	int rigaPart = rigaPartenza;
	int colPart = colPartenza;
	int rigaTarget = rigaTarg;
	int colTarget = colTarg;
	Scacchiera s = scacchieraRic;
	s.getScacchiera()[rigaTarget][colTarget] = s.getScacchiera()[rigaPart][colPart];
	s.getScacchiera()[rigaPart][colPart] = null;
    }

    /**
     * Modifica il codice Unicode del pezzo in base al suo colore
     */
    @Override
    public String getUniCode() {
	if (getColor() == 'N') {
	    setCode("\u265A");
	} else {
	    setCode("\u2654");
	}
	return getCode();
    }

    /**
     * Restituisce il valore della variabile primaMossa
     * @return primaMossa true se il re deve ancora effettuare la prima mossa, false altrimenti
     */
    public boolean isPrimaMossa() {
	return primaMossa;
    }

    /**
     * Modifica il valore della variabile primaMossa
     * @param primaMossaRicevuto il nuovo valore delle variabile primaMossa
     */
    public void setPrimaMossa(final boolean primaMossaRicevuto) {
	this.primaMossa = primaMossaRicevuto;
    }

    /**
     * Gestisce l'arrocco lungo o l'arrocco corto
     * @param gioc giocatore richiedente lo spostamento
     * @param scacchieraRic scacchiera
     * @param tip 0 se l'arrocco � corto, 1 se lungo
     */
    public void moveArrocco(final String gioc, final Scacchiera scacchieraRic, final int tip) {
	String giocatore = gioc;
	Scacchiera scacchiera = scacchieraRic;
	int tipo = tip;
	int rigaPartRe = -1;
	int colPartRe = CPARTRE;
	int colDestRe = CDESTRE;

	int rigaPartTorre = -1;
	int colPartTorre = CPARTORRE;
	int colDestTorre = CDESTORRE;

	// Imposto gli indici in base al giocatore corrente

	if (giocatore.equals("bianco")) {
	    rigaPartRe = RPARTRE;
	    rigaPartTorre = RPARTORRE;
	} else {
	    rigaPartRe = 0;
	    rigaPartTorre = 0;
	}

	// Imposto gli indici in base al tipo di arrocco (lungo/corto)

	if (tipo == 0) {
	    colDestRe = colPartRe + 2;
	    colPartTorre = CPARTORRE;
	    colDestTorre = CDESTORRE;
	} else {
	    colDestRe = colPartRe - 2;
	    colPartTorre = 0;
	    colDestTorre = TORREARR;
	}

	// Sposto i pezzi

	scacchiera.getScacchiera()[rigaPartRe][colDestRe] = scacchiera.getScacchiera()[rigaPartRe][colPartRe];
	scacchiera.getScacchiera()[rigaPartRe][colPartRe] = null;
	scacchiera.getScacchiera()[rigaPartTorre][colDestTorre] = scacchiera
		.getScacchiera()[rigaPartTorre][colPartTorre];
	scacchiera.getScacchiera()[rigaPartTorre][colPartTorre] = null;

	if (isPrimaMossa()) {
	    setPrimaMossa(false);
	}
    }

}

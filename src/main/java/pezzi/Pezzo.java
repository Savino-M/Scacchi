package pezzi;

/**
 * Entity Class
 * Rappresenta un Pezzo in maniera generica
 */
public abstract class Pezzo {

    private char colore;
    private String code;

    /**
     * Modifica il codice unicode del pezzo in base al suo colore
     *
     * @return code il codice unicode associato al colore del pezzo
     */
    public abstract String getUniCode();

    /**
     * Restituisce il colore del pezzo
     *
     * @return colore il colore del pezzo
     */
    public char getColor() {
        return colore;
    }

    /**
     * Modifica il colore del pezzo
     *
     * @param coloreRicevuto il nuovo colore del pezzo
     */
    public void setColore(final char coloreRicevuto) {
        this.colore = coloreRicevuto;
    }

    /**
     * Restituisce il codice unicode del pezzo
     *
     * @return code il codice unicode del pezzo
     */
    public String getCode() {
        return code;
    }

    /**
     * Modifica il codice unicode del pezzo
     *
     * @param codeRicevuto il nuovo codice unicode del pezzo
     */
    public void setCode(final String codeRicevuto) {
        this.code = codeRicevuto;
    }
}

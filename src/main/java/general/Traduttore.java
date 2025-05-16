package general;

import general.Partita.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * noECB
 * Traduce il comando ricevuto dall'utente
 */
public final class Traduttore {

    private static final int NUMRIGHE = 8;
    private static final int TRE = 3;
    private static final int QUATTRO = 4;
    private static final int CINQUE = 5;
    private static final int SEI = 6;
    private static final int SETTE = 7;

    /**
     * Costruttore
     */
    private Traduttore() {
        // non chiamato
    }

    /**
     * Ricava la riga della matrice(scacchiera)
     *
     * @param notazioneRiga riga inserita dall'utente
     * @return riga riga nella matrice
     */
    public static int traduciRiga(final char notazioneRiga) {

        int riga = Integer.parseInt(String.valueOf(notazioneRiga));
        riga = NUMRIGHE - riga;
        return riga;

    }

    /**
     * Ricava la colonna della matrice(scacchiera)
     *
     * @param notazioneColonna colonna inserita dall'utente
     * @return colonna colonna della matrice
     */
    public static int traduciColonna(final char notazioneColonna) {

        int colonna = 0;

        switch (notazioneColonna) {

            case 'a':
                colonna = 0;
                break;

            case 'b':
                colonna = 1;
                break;

            case 'c':
                colonna = 2;
                break;

            case 'd':
                colonna = TRE;
                break;

            case 'e':
                colonna = QUATTRO;
                break;

            case 'f':
                colonna = CINQUE;
                break;

            case 'g':
                colonna = SEI;
                break;

            case 'h':
                colonna = SETTE;
                break;

            default:
        }

        return colonna;
    }

    /**
     * Analizza il tipo di comando ricevuto
     *
     * @param comando comando inserito dall'utente
     * @return command operazione richiesta dall'utente
     */
    public static Command traduciComando(final String comando) {

        Command command = null;

        // espressione regolare relativa allo spostamento
        String movePawn = "([ADTCR])?([a-h])([1-8])";
        Pattern p = Pattern.compile(movePawn);
        Matcher matcher = p.matcher(comando);

        // Controllo relativo all'arrocco
        boolean arrocco = (comando.equals("0-0") || comando.equals("0-0-0") || comando.equals("O-O-O")
                || comando.equals("O-O"));

        // espressione regolare relativa alla cattura
        String capturePawn = "([ADTCR | a-h])(x)([a-h])([1-8])( )?(e.p.)?";
        Pattern p1 = Pattern.compile(capturePawn);
        Matcher matcher1 = p1.matcher(comando);

        if (comando.equals("play")) {
            command = Partita.Command.play;
        } else if (comando.equals("board")) {
            command = Partita.Command.board;
        } else if (comando.equals("help")) {
            command = Partita.Command.help;
        } else if (comando.equals("quit")) {
            command = Partita.Command.quit;
        } else if (comando.equals("moves")) {
            command = Partita.Command.moves;
        } else if (comando.equals("captures")) {
            command = Partita.Command.captures;
        } else if (matcher.matches() || arrocco) { // Se il comando rispecchia uno spostamento
            command = Partita.Command.spostamento;
        } else if (matcher1.matches()) { // Se il comando rispecchia una cattura
            command = Partita.Command.cattura;
        } else {
            command = Partita.Command.nullo;
        }

        return command;
    }

}

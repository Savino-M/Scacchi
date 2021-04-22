package general;

import pezzi.*;

import java.util.Scanner;

/**
 * noECB Gestione del movimento dei pezzi lungo la scacchiera
 */
public final class Movimento
{

    /**
     * Costruttore
     */
    private Movimento()
    {
        // not called
    }

    private static Scanner scanner;
    private static boolean trovato;
    private static String pezzo1 = null;
    private static String pezzo2 = null;
    private static final int DIMSUP = 7;
    private static final int DIMINF = 0;
    private static final int CURSORE = 16;
    private static final int TRE = 3;
    private static final int SETTE = 7;

    /**
     * Cerca le coordinate di partenza del pezzo muovendosi sulle caselle adiacenti
     *
     * @param giocatore  richiedente il movimento
     * @param scacchiera scacchiera
     * @param x          riga da esaminare
     * @param y          colonna da esaminare
     * @param caso       indica
     * @return m coordinate di partenza del pezzo
     */
    public static String adiacente(final String giocatore, final Scacchiera scacchiera, final int x, final int y,
                                   final int caso)
    {

        String m = null;
        boolean trovatoAdiacente = false;
        int rigaPart = 0; // memorizza la riga del pezzo che vuole spostarsi
        int colPart = 0; // memorizza la colonna del pezzo che vuole spostarsi

        if (caso == 0)
        {
            pezzo1 = null;
            pezzo2 = null;
            trovatoAdiacente = false;
        }

        // se il punto in cui voglio accedere rispetta la dimensione della scacchiera
        if (x <= DIMSUP && x >= DIMINF && y <= DIMSUP && y >= DIMINF)
        {

            if (scacchiera.getScacchiera()[x][y] instanceof Re)
            { // se trovo un re

                // ed e' del giocatore giocatore
                if (scacchiera.getScacchiera()[x][y].getColor() == giocatore.toUpperCase().charAt(0))
                {

                    trovatoAdiacente = true;
                    rigaPart = x; // salvo la riga del pezzo che si vuole spostare
                    colPart = y; // salvo la colonna del pezzo che si vuole spostare
                }

            }
        }

        if (trovatoAdiacente)
        {
            m = rigaPart + "," + colPart;
        }

        return m;
    }

    /**
     * Cerca le coordinate di partenza del pezzo muovendosi sui lati
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param giocatore        richiedente lo spostamento
     * @param scacchiera       scacchiera
     * @param x1               riga da esaminare
     * @param y1               colonna da esaminare
     * @param caso             direzione da seguire
     * @param pezzo            pezzo da spostare
     * @return pezzoF coordinate di partenza del pezzo
     */
    public static String direzione(final int rigaDestinazione, final int colDestinazione, final String giocatore,
                                   final Scacchiera scacchiera, final int x1, final int y1, final int caso, final String pezzo)
    {

        int rigaDest = rigaDestinazione;
        int colDest = colDestinazione;
        int x = x1;
        int y = y1;
        int rigaPart = 0; // memorizza la riga del pezzo che vuole spostarsi
        int colPart = 0; // memorizza la colonna del pezzo che vuole spostarsi
        int spostamento = 1; // conta il numero di caselle di cui deve spostarsi il pezzo
        String pezzoF = null; // stringa finale contentente il pezzo di partenza
        String coordinate = null;

        if (caso == 0)
        {
            pezzo1 = null;
            pezzo2 = null;
            trovato = false;
        }

        // se il punto in cui voglio accedere rispetta la dimensione della scacchiera
        while (x <= DIMSUP && x >= DIMINF && y <= DIMSUP && y >= DIMINF)
        {
            try
            {
                // se l'elemento trovato e' quello che ha richiesto l'operazione
                if (scacchiera.getScacchiera()[x][y].getClass().getName().substring(CURSORE).equals(pezzo))
                {

                    // se il pezzo trovato appartiene al giocatore in turno
                    if (scacchiera.getScacchiera()[x][y].getColor() == giocatore.toUpperCase().charAt(0))
                    {
                        trovato = true;
                        rigaPart = x; // salvo la riga del pezzo che si vuole spostare
                        colPart = y; // salvo la colonna del pezzo che si vuole spostare
                        if (pezzo1 == null)
                        {
                            pezzo1 = rigaPart + "," + colPart + "," + spostamento;
                            break;
                        }
                        else
                        {
                            pezzo2 = rigaPart + "," + colPart + "," + spostamento;
                            break;
                        }
                    }
                }
                else if (scacchiera.getScacchiera()[x][y] instanceof Pezzo)
                {
                    break;
                }
            }
            catch (Exception e)
            {

            }
            spostamento++; // incremento il numero di celle che ditanziano il pezzo dalle coordinate target

            switch (caso)
            { // case 0 e 1 esaminano righe, case 2 e 3 le colonne
                case 0:
                    rigaDest++;
                    x = rigaDest + 1;
                    break;
                case 1:
                    rigaDest--;
                    x = rigaDest - 1;
                    break;
                case 2:
                    colDest--;
                    y = colDest - 1;
                    break;
                case TRE:
                    colDest++;
                    y = colDest + 1;
                    break;
                default:
            }
        }

        if (trovato && caso == TRE)
        { // se ha trovato tutte le Torri possibili

            if (pezzo2 == null)
            { // mette la prima stringa in f se non ne ha trovate altre
                pezzoF = pezzo1;

            }

            if (pezzoF == null && pezzo.equals("Torre"))
            {

                System.out.println("\n   >ATTENZIONE! Sono state rilevate due torri.");
                System.out.print("   >Specifica le cordinate della torre di partenza: ");
                scanner = new Scanner(System.in);
                coordinate = scanner.nextLine();
                if (coordinate.length() < TRE)
                {
                    try
                    {
                        String colUser = Integer.toString(Traduttore.traduciColonna(coordinate.charAt(0)));
                        String rigaUser = Integer.toString(Traduttore.traduciRiga(coordinate.charAt(1)));
                        if (rigaUser.contentEquals(pezzo1.substring(0, 1))
                                && colUser.contentEquals(pezzo1.substring(2, TRE)))
                        {
                            pezzoF = pezzo1;
                        }
                        else if (rigaUser.contentEquals(pezzo2.substring(0, 1))
                                && colUser.contentEquals(pezzo2.substring(2, TRE)))
                        {
                            pezzoF = pezzo2;
                        }
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        }
        return pezzoF;
    }

    /**
     * Sposta il pezzo lungo il lato
     *
     * @param rigaPartenza riga di partenza
     * @param colPartenza  colonna di partenza
     * @param spostamento  di quante caselle spostarsi
     * @param s            scacchiera
     * @param x1           riga successiva in cui spostarsi
     * @param y1           colonna successiva in cui spostarsi
     * @param caso         lato da seguire
     * @param cattura      true se � richiesta una cattura, false altrimenti
     * @param pezzo        richiedente lo spostamento
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public static boolean spostaLato(final int rigaPartenza, final int colPartenza, final int spostamento,
                                     final Scacchiera s, final int x1, final int y1, final int caso, final boolean cattura, final String pezzo)
    {

        int colPart = colPartenza;
        int rigaPart = rigaPartenza;
        int x = x1;
        int y = y1;
        Pezzo t;

        if (pezzo.equals("Torre"))
        {
            t = (Torre) s.getScacchiera()[rigaPart][colPart]; // Torre che si vuole spostare
        }
        else
        {
            t = (Donna) s.getScacchiera()[rigaPart][colPart]; // Donna che si vuole spostare
        }

        boolean esito = false;
        int riga = rigaPart, colonna = colPart;

        for (int i = 0; i < spostamento; i++)
        { // sposto la torre di una casella alla volta

            if (s.getScacchiera()[x][y] == null)
            { // se la casella in cui mi voglio spostare e' vuota
                s.getScacchiera()[rigaPart][colPart] = null; // rimuovo il pezzo dalla casella in cui si trova
                s.getScacchiera()[x][y] = t; // e lo metto nella casella successiva
                esito = true;
                switch (caso)
                {
                    case 0:
                        y = colPart;
                        rigaPart--;
                        x = rigaPart - 1;

                        break;
                    case 1:
                        y = colPart;
                        rigaPart++;
                        x = rigaPart + 1;

                        break;
                    case 2:
                        colPart++;
                        x = rigaPart;
                        y = colPart + 1;
                        break;
                    case TRE:
                        colPart--;
                        x = rigaPart;
                        y = colPart - 1;
                        break;
                    default:
                }

            }
            else
            {
                if (cattura)
                { // se si deve effettuare una cattura

                    if (i == spostamento - 1)
                    {
                        s.getScacchiera()[x][y] = null; // rimuovo il pezzo da mangiare
                        s.getScacchiera()[x][y] = s.getScacchiera()[rigaPart][colPart]; // e metto al suo posto il pezzo
                        // "mangiatore"
                        s.getScacchiera()[rigaPart][colPart] = null; // svuoto la casella in cui si trovava il pezzo
                        // "mangiatore"
                        esito = true;
                    }
                }
                else
                { // altrimenti annullo gli spostamenti del pezzo
                    esito = false;
                    s.getScacchiera()[rigaPart][colPart] = null;
                    s.getScacchiera()[riga][colonna] = t;
                }

                break;
            }
        }
        return esito;
    }

    /**
     * Cerca le coordinate di partenza del pezzo muovendosi a L
     *
     * @param giocatore  richiedente spostamento
     * @param scacchiera scacchiera
     * @param x          riga da esaminare
     * @param y          colonna da esaminare
     * @param caso       direzione da seguire
     * @return f coordinate di partenza del pezzo
     */
    public static String direzioneCavallo(final String giocatore, final Scacchiera scacchiera, final int x, final int y,
                                          final int caso)
    {

        int rigaPart = 0; // memorizza la riga del cavallo che vuole spostarsi
        int colPart = 0; // memorizza la colonna del cavallo che vuole spostarsi
        String f = null;
        String coordinate = null;

        if (caso == 0)
        {
            pezzo1 = null;
            pezzo2 = null;
            trovato = false;
        }

        // se il punto in cui voglio accedere rispetta la dimensione della scacchiera
        if (x <= DIMSUP && x >= DIMINF && y <= DIMSUP && y >= DIMINF)
        {

            // se l'elemento trovato e' un Cavallo
            if (scacchiera.getScacchiera()[x][y] instanceof Cavallo)
            {

                // se il Cavallo trovato appartiene al giocatore in turno
                if (scacchiera.getScacchiera()[x][y].getColor() == giocatore.toUpperCase().charAt(0))
                {
                    trovato = true;
                    rigaPart = x; // salvo la riga del Cavallo che si vuole spostare
                    colPart = y; // salvo la colonna del Cavallo che si vuole spostare

                    if (pezzo1 == null)
                    {
                        pezzo1 = rigaPart + "," + colPart + "," + caso;
                    }
                    else
                    {
                        pezzo2 = rigaPart + "," + colPart + "," + caso;
                    }
                }
            }
        }

        if (trovato && caso == SETTE)
        {
            if (pezzo2 == null)
            {
                f = pezzo1;
            }

            if (f == null)
            {
                System.out.println("\n   >ATTENZIONE! Sono stati rilevati due cavalli.");
                System.out.print("   >Specifica le cordinate del cavallo di partenza: ");
                scanner = new Scanner(System.in);
                coordinate = scanner.nextLine();

                if (coordinate.length() < TRE)
                {
                    try
                    {
                        String colUser = Integer.toString(Traduttore.traduciColonna(coordinate.charAt(0)));
                        String rigaUser = Integer.toString(Traduttore.traduciRiga(coordinate.charAt(1)));
                        if (rigaUser.contentEquals(pezzo1.substring(0, 1))
                                && colUser.contentEquals(pezzo1.substring(2, TRE)))
                        {
                            f = pezzo1;
                        }
                        else if (rigaUser.contentEquals(pezzo2.substring(0, 1))
                                && colUser.contentEquals(pezzo2.substring(2, TRE)))
                        {
                            f = pezzo2;
                        }
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        }
        return f;
    }

    /**
     * Sposta il cavallo
     *
     * @param rigaPart riga di partenza
     * @param colPart  colonna di partenza
     * @param s        scacchiera
     * @param x        riga successiva
     * @param y        colonna successiva
     * @param cattura  true se � richiesta una cattura, false altrimenti
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public static boolean spostaCavallo(final int rigaPart, final int colPart, final Scacchiera s, final int x,
                                        final int y, final boolean cattura)
    {

        Cavallo c = (Cavallo) s.getScacchiera()[rigaPart][colPart]; // Cavallo che si vuole spostare
        boolean esito = false;
        int riga = rigaPart, colonna = colPart;

        // sposto il Cavallo di una casella alla volta

        if (s.getScacchiera()[x][y] == null)
        { // se la casella in cui mi voglio spostare e' vuota
            s.getScacchiera()[rigaPart][colPart] = null; // rimuovo il Cavallo dalla casella in cui si trova
            s.getScacchiera()[x][y] = c; // e lo metto nella casella successiva
            esito = true;

        }
        else
        {
            if (cattura)
            { // se si deve effettuare una cattura

                s.getScacchiera()[x][y] = null; // rimuovo il pezzo da mangiare
                s.getScacchiera()[x][y] = s.getScacchiera()[rigaPart][colPart]; // e metto al suo posto il Cavallo
                s.getScacchiera()[rigaPart][colPart] = null; // svuoto la casella in cui si trovava Cavallo
                esito = true;

            }
            else
            { // altrimenti annullo gli spostamenti del cavallo
                esito = false;
                s.getScacchiera()[rigaPart][colPart] = null;
                s.getScacchiera()[riga][colonna] = c;
            }

        }
        return esito;
    }

    /**
     * Cerca le coordinate di partenza del pezzo muovendosi in diagonale
     *
     * @param rigaDestinazione riga di destinazione
     * @param colDestinazione  colonna di destinazione
     * @param giocatore        richiedente spostamento
     * @param scacchiera       scacchiera
     * @param x1               riga da esaminare
     * @param y1               colonna da esaminare
     * @param caso             diagonale da seguire
     * @param pezzo            richiedente spostamento
     * @return m coordinate di partenza del pezzo
     */
    public static String diagonale(final int rigaDestinazione, final int colDestinazione, final String giocatore,
                                   final Scacchiera scacchiera, final int x1, final int y1, final int caso, final String pezzo)
    {

        int colDest = colDestinazione;
        int rigaDest = rigaDestinazione;
        int x = x1;
        int y = y1;
        boolean trovatoDiagonale = false;
        int rigaPart = 0; // memorizza la riga del pezzo che vuole spostarsi
        int colPart = 0; // memorizza la colonna del pezzo che vuole spostarsi
        int spostamento = 1; // conta il numero di caselle di cui deve spostarsi il pezzo
        String m = null;

        if (caso == 0)
        {
            pezzo1 = null;
            pezzo2 = null;
            trovatoDiagonale = false;
        }

        // se il punto in cui voglio accedere rispetta la dimensione della scacchiera
        while (x <= DIMSUP && x >= DIMINF && y <= DIMSUP && y >= DIMINF)
        {

            try
            {
                if (scacchiera.getScacchiera()[x][y].getClass().getName().substring(CURSORE).equals(pezzo))
                {

                    // se il pezzo trovato appartiene al giocatore in turno
                    if (scacchiera.getScacchiera()[x][y].getColor() == giocatore.toUpperCase().charAt(0))
                    {
                        trovatoDiagonale = true;
                        rigaPart = x; // salvo la riga del pezzo che si vuole spostare
                        colPart = y; // salvo la colonna del pezzo che si vuole spostare
                        break; // esco dal ciclo
                    }
                }
                else if (scacchiera.getScacchiera()[x][y] instanceof Pezzo)
                {
                    break;
                }
            }
            catch (Exception e)
            {

            }

            spostamento++; // incremento il numero di celle che distanziano il pezzo dalle coordinate
            // target
            switch (caso)
            {
                case 0:
                    colDest--; // passo alla colonna precedente
                    rigaDest++; // passo alla riga successiva
                    x = rigaDest + 1; // esamino la riga successiva
                    y = colDest - 1; // esamino la colonna precedente
                    break;
                case 1:
                    colDest++;
                    rigaDest++;
                    x = rigaDest + 1;
                    y = colDest + 1;
                    break;
                case 2:
                    colDest--;
                    rigaDest--;
                    x = rigaDest - 1;
                    y = colDest - 1;
                    break;
                case TRE:
                    colDest++;
                    rigaDest--;
                    x = rigaDest - 1;
                    y = colDest + 1;
                    break;
                default:
            }

        }

        if (trovatoDiagonale)
        {
            m = rigaPart + "," + colPart + "," + spostamento;
        }
        return m;
    }

    /**
     * Sposta il pezzo lungo la diagonale
     *
     * @param rigaPartenza riga di partenza
     * @param colPartenza  colonna di partenza
     * @param spostamento  di quante caselle spostarsi
     * @param s            scacchiera
     * @param x1           riga in cui spostarsi
     * @param y1           colonna in cui spostarsi
     * @param caso         diagonale da seguire
     * @param cattura      true se � richiesta una cattura, false altrimenti
     * @param pezzo        richiedente lo spostamento
     * @return esito true se lo spostamento va a buon fine, false altrimenti
     */
    public static boolean spostaDiagonale(final int rigaPartenza, final int colPartenza, final int spostamento,
                                          final Scacchiera s, final int x1, final int y1, final int caso, final boolean cattura, final String pezzo)
    {

        int colPart = colPartenza;
        int rigaPart = rigaPartenza;
        int x = x1;
        int y = y1;

        Pezzo a;
        if (pezzo.equals("Alfiere"))
        {
            a = (Alfiere) s.getScacchiera()[rigaPart][colPart]; // Alfiere che si vuole spostare
        }
        else
        {
            a = (Donna) s.getScacchiera()[rigaPart][colPart]; // Donna che si vuole spostare
        }

        boolean esito = false;
        int riga = rigaPart, colonna = colPart;

        for (int i = 0; i < spostamento; i++)
        { // sposto il pezzo di una casella alla volta

            if (s.getScacchiera()[x][y] == null)
            { // se la casella in cui mi voglio spostare e' vuota
                s.getScacchiera()[rigaPart][colPart] = null; // rimuovo il pezzo dalla casella in cui si trova
                s.getScacchiera()[x][y] = a; // e lo metto nella casella successiva
                esito = true;
                switch (caso)
                {
                    case 0:
                        colPart++;
                        rigaPart++;
                        x = rigaPart + 1;
                        y = colPart + 1;
                        break;
                    case 1:
                        colPart--;
                        rigaPart++;
                        x = rigaPart + 1;
                        y = colPart - 1;
                        break;
                    case 2:
                        colPart++;
                        rigaPart--;
                        x = rigaPart - 1;
                        y = colPart + 1;
                        break;
                    case TRE:
                        colPart--;
                        rigaPart--;
                        x = rigaPart - 1;
                        y = colPart - 1;
                        break;
                    default:
                }

            }
            else
            {
                if (cattura)
                { // se si deve effettuare una cattura

                    if (i == spostamento - 1)
                    {
                        s.getScacchiera()[x][y] = null; // rimuovo il pezzo da mangiare
                        s.getScacchiera()[x][y] = s.getScacchiera()[rigaPart][colPart]; // e metto al suo posto il pezzo
                        // "mangiatore"
                        s.getScacchiera()[rigaPart][colPart] = null; // svuoto la casella in cui si trovava il pezzo
                        // "mangiatore"
                        esito = true;
                    }
                    else
                    { // altrimenti annullo gli spostamenti del pezzo
                        esito = false;
                        s.getScacchiera()[rigaPart][colPart] = null;
                        s.getScacchiera()[riga][colonna] = a;
                    }

                }
                else
                { // altrimenti annullo gli spostamenti del pezzo
                    esito = false;
                    s.getScacchiera()[rigaPart][colPart] = null;
                    s.getScacchiera()[riga][colonna] = a;
                }

                break;
            }
        }
        return esito;
    }

}

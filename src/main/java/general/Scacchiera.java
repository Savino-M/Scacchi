package general;

import pezzi.*;

import java.io.PrintStream;

/**
 * noECB
 * Crea e visualizza lo stato della scacchiera
 */
public class Scacchiera
{
    private static final int MAXCOL = 7;
    private static final int ROW = 8;
    private static final int COLUMN = 8;
    private static final int PRIGAB = 7;
    private static final int COLTOR = 7;
    private static final int COLD = 3;
    private static final int COLRE = 4;
    private static final int COLALF = 5;
    private static final int COLCAV = 6;
    private static final int RIGAPED = 6;
    public static final String ANSI_WHITE_BACKGROUND = "\033[0;107m";
    /**
     * Colore bianco per il background
     */
    public static final String ANSI_BLUE_BACKGROUND = "\033[0;104m";
    /**
     * Colore blu per il background
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * Resetta i colori
     */
    public static final String ANSI_BLACK = "\u001B[30m";
    /**
     * Colore nero per le pedine
     */
    // crea la scacchiera
    private Pezzo[][] scacchiera = new Pezzo[ROW][COLUMN];

    /**
     * Costruttore
     */
    public Scacchiera()
    {

        // pone i pezzi neri sulla scacchiera
        getScacchiera()[0][0] = new Torre('N');
        getScacchiera()[0][1] = new Cavallo('N');
        getScacchiera()[0][2] = new Alfiere('N');
        getScacchiera()[0][COLD] = new Donna('N');
        getScacchiera()[0][COLRE] = new Re('N');
        getScacchiera()[0][COLALF] = new Alfiere('N');
        getScacchiera()[0][COLCAV] = new Cavallo('N');
        getScacchiera()[0][COLTOR] = new Torre('N');

        for (int i = 0; i < COLUMN; i++)
        {
            getScacchiera()[1][i] = new Pedone('N');
        }

        // pone i pezzi bianchi sulla scacchiera
        getScacchiera()[PRIGAB][0] = new Torre('B');
        getScacchiera()[PRIGAB][1] = new Cavallo('B');
        getScacchiera()[PRIGAB][2] = new Alfiere('B');
        getScacchiera()[PRIGAB][COLD] = new Donna('B');
        getScacchiera()[PRIGAB][COLRE] = new Re('B');
        getScacchiera()[PRIGAB][COLALF] = new Alfiere('B');
        getScacchiera()[PRIGAB][COLCAV] = new Cavallo('B');
        getScacchiera()[PRIGAB][COLTOR] = new Torre('B');

        for (int i = 0; i < COLUMN; i++)
        {
            getScacchiera()[RIGAPED][i] = new Pedone('B');
        }
    }

    /**
     * Stampa la scacchiera
     */
    public void show()
    {
        int i, j, z;
        boolean bianco1 = true;
        boolean bianco = true;
        System.out.println("      a    b    c    d    e    f    g    h       \n");

        for (i = 0; i < ROW; i++)
        {

            System.out.print("    ");
            for (z = 0; z < COLUMN; z++)
            {

                if (bianco1)
                {
                    if (z < COLUMN)
                    {
                        bianco1 = !bianco1;
                        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK);
                    }

                } else
                {
                    if (z < COLUMN)
                    {
                        bianco1 = !bianco1;
                        System.out.print(ANSI_BLUE_BACKGROUND + ANSI_BLACK);
                    }
                }
                try
                {
                    System.setOut(new PrintStream(System.out, false, "UTF-8"));
                    System.out.print("     " + ANSI_RESET);
                }
                catch (Exception e)
                {

                }
                if (z == MAXCOL)
                {
                    if (bianco1)
                    {
                        bianco1 = !bianco1;

                    } else
                    {
                        bianco1 = !bianco1;
                    }
                    System.out.print("     \n" + ANSI_RESET);
                }
            }

            System.out.print(" " + (ROW - i) + "  ");
            for (j = 0; j < COLUMN; j++)
            {

                if (bianco)
                {
                    if (j < COLUMN)
                    {
                        bianco = !bianco;
                        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK);
                    }

                } else
                {
                    if (j < COLUMN)
                    {
                        bianco = !bianco;
                        System.out.print(ANSI_BLUE_BACKGROUND + ANSI_BLACK);

                    }
                }
                try
                {
                    System.setOut(new PrintStream(System.out, false, "UTF-8"));
                    System.out.print("  " + getScacchiera()[i][j].getUniCode() + "  " + ANSI_RESET);
                }
                catch (Exception e)
                {
                    System.out.print("     " + ANSI_RESET);
                }

                if (j == MAXCOL)
                {

                    if (bianco)
                    {
                        bianco = !bianco;

                    } else
                    {
                        bianco = !bianco;
                    }

                    System.out.print("  " + (ROW - i) + " \n" + ANSI_RESET);
                }
            }

        }
        System.out.println("\n      a    b    c    d    e    f    g    h       ");
    }

    /**
     * Restituisce la scacchiera
     *
     * @return scacchiera matrice che rappresenta la scacchiera
     */
    public Pezzo[][] getScacchiera()
    {
        return scacchiera;
    }

    /**
     * Modifica la scacchiera
     *
     * @param scacchieraRicevuta nuova matrice(scacchiera) da andare a sostituire a quella vecchia
     */
    public void setScacchiera(final Pezzo[][] scacchieraRicevuta)
    {
        this.scacchiera = scacchieraRicevuta;
    }

}

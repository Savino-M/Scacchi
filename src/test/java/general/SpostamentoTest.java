package general;

import org.junit.jupiter.api.Test;
import pezzi.*;

import static org.junit.jupiter.api.Assertions.*;


public class SpostamentoTest
{

    @Test
    public void spostamentoCavalloTest()
    {

        // spostamento riuscito
        String comando = "Ca6";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoCavallo();

        assertNull(scacchieraTest.getScacchiera()[0][1]);
        assertTrue(scacchieraTest.getScacchiera()[2][0] instanceof Cavallo);

        // spostamento non riuscito per comando errato
        comando = "Cd5";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoCavallo();
        assertTrue(scacchieraTest.getScacchiera()[7][1] instanceof Cavallo);
        assertTrue(scacchieraTest.getScacchiera()[7][6] instanceof Cavallo);

        // Spostamento riuscito
        comando = "Cd5";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[4][5] = new Cavallo('B');
        scacchieraTest.getScacchiera()[7][6] = null;
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoCavallo();
        assertNull(scacchieraTest.getScacchiera()[4][5]);
        assertTrue(scacchieraTest.getScacchiera()[3][3] instanceof Cavallo);

    }

    @Test
    public void spostamentoReTest()
    {

        // spostamento riuscito
        String comando = "Re7";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][4] = null;
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoRe();

        assertNull(scacchieraTest.getScacchiera()[0][4]);
        assertTrue(scacchieraTest.getScacchiera()[1][4] instanceof Re);

        // spostamento non riuscito perch� il Re � sotto scacco
        comando = "Rg7";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }

        }
        scacchieraTest.getScacchiera()[0][7] = new Re('N');
        scacchieraTest.getScacchiera()[7][6] = new Donna('B');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoRe();

        assertTrue(scacchieraTest.getScacchiera()[0][7] instanceof Re);

    }

    @Test
    public void spostamentoAlfiereTest()
    {

        // spostamento non riuscito a causa di un pezzo in mezzo al percorso
        String comando = "Ae6";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoAlfiere();

        assertTrue(scacchieraTest.getScacchiera()[0][2] instanceof Alfiere);

        // spostamento riuscito
        comando = "Aa5";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[6][3] = null;
        scacchieraTest.getScacchiera()[6][3] = new Alfiere('B');
        scacchieraTest.getScacchiera()[6][2] = null;
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoAlfiere();

        assertNull(scacchieraTest.getScacchiera()[6][3]);
        assertTrue(scacchieraTest.getScacchiera()[3][0] instanceof Alfiere);

    }

    @Test
    public void spostamentoTorreTest()
    {

        // spostamento riuscito
        String comando = "Ta5";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][0] = null;
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoTorre();
        assertNull(scacchieraTest.getScacchiera()[0][0]);
        assertTrue(scacchieraTest.getScacchiera()[3][0] instanceof Torre);

        // spostamento non riuscito a causa del pedone in mezzo al percorso
        comando = "Ta4";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoTorre();
        assertTrue(scacchieraTest.getScacchiera()[7][0] instanceof Torre);

    }

    @Test
    public void spostamentoDonnaTest()
    {

        // spostamento riuscito
        String comando = "Df4";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][3] = null;
        scacchieraTest.getScacchiera()[2][3] = new Donna('N');
        scacchieraTest.getScacchiera()[0][3] = null;
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoDonna();

        assertNull(scacchieraTest.getScacchiera()[2][3]);
        assertTrue(scacchieraTest.getScacchiera()[4][5] instanceof Donna);

        // spostamento non riuscito a causa del percorso occupato da un pezzo
        comando = "Df3";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoDonna();
        assertTrue(scacchieraTest.getScacchiera()[7][3] instanceof Donna);

        // spostamento non riuscito a causa di un comando errato
        comando = "De6";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoDonna();
        assertTrue(scacchieraTest.getScacchiera()[0][3] instanceof Donna);

    }

    @Test
    public void spostamentoPedoneTest()
    {

        // spostamento riuscito
        String comando = "a3";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);

        assertTrue(scacchieraTest.getScacchiera()[5][0] instanceof Pedone);

        // spostamento non riuscito per comando errato
        comando = "d5";
        giocatore = "bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[6][3] instanceof Pedone);
    }

    @Test
    public void verificaCondizioniArroccoTest()
    {

        String comando;
        String giocatore;
        Scacchiera scacchieraTest;

        // arrocco lungo
        comando = "0-0-0";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }

        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        scacchieraTest.getScacchiera()[0][0] = new Torre('N');
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);

        assertNull(scacchieraTest.getScacchiera()[0][0]);
        assertTrue(scacchieraTest.getScacchiera()[0][2] instanceof Re);
        assertNull(scacchieraTest.getScacchiera()[0][4]);
        assertTrue(scacchieraTest.getScacchiera()[0][3] instanceof Torre);

        // arrocco corto
        comando = "0-0";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][7] = new Torre('N');
        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        spost = new Spostamento(comando, giocatore, scacchieraTest);

        assertNull(scacchieraTest.getScacchiera()[0][7]);
        assertTrue(scacchieraTest.getScacchiera()[0][6] instanceof Re);
        assertNull(scacchieraTest.getScacchiera()[0][4]);
        assertTrue(scacchieraTest.getScacchiera()[0][5] instanceof Torre);

        // arrocco non riuscito
        comando = "0-0";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][7] = new Torre('N');
        scacchieraTest.getScacchiera()[1][3] = new Re('N');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[1][3] instanceof Re);
        assertTrue(scacchieraTest.getScacchiera()[0][7] instanceof Torre);

    }

    @Test
    public void isSottoScaccoTest()
    {

        // caso in cui il re � sotto scacco
        Scacchiera test = new Scacchiera();
        int rigaRe = 1;
        int colonnaRe = 6;
        String giocatore = "Nero";
        test = new Scacchiera();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                test.getScacchiera()[i][j] = null;
            }
        }
        test.getScacchiera()[0][7] = new Re('N');
        test.getScacchiera()[2][7] = new Re('B');
        test.getScacchiera()[1][5] = new Donna('B');
        assertTrue(Spostamento.isSottoScacco(rigaRe, colonnaRe, giocatore, test));

        // caso in cui il re non � sotto scacco
        test = new Scacchiera();
        rigaRe = 1;
        colonnaRe = 4;
        giocatore = "Nero";
        test.getScacchiera()[1][4] = null;
        assertFalse(Spostamento.isSottoScacco(rigaRe, colonnaRe, giocatore, test));
    }

    @Test
    public void spostamentoArroccoTest()
    {

        // arrocco corto riuscito
        String comando = "0-0";
        String giocatore = "Nero";
        int tipo = 0;
        Scacchiera scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][7] = new Torre('N');
        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

        assertNull(scacchieraTest.getScacchiera()[0][7]);
        assertTrue(scacchieraTest.getScacchiera()[0][5] instanceof Torre);
        assertNull(scacchieraTest.getScacchiera()[0][4]);
        assertTrue(scacchieraTest.getScacchiera()[0][6] instanceof Re);

        // arrocco lungo riuscito
        comando = "0-0-0";
        giocatore = "Nero";
        tipo = 1;
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][0] = new Torre('N');
        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

        assertNull(scacchieraTest.getScacchiera()[0][0]);
        assertTrue(scacchieraTest.getScacchiera()[0][3] instanceof Torre);
        assertNull(scacchieraTest.getScacchiera()[0][4]);
        assertTrue(scacchieraTest.getScacchiera()[0][2] instanceof Re);

        // arrocco lungo non riuscito a causa dei pezzi gi� spostati
        comando = "0-0-0";
        giocatore = "Bianco";
        tipo = 1;
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }

        scacchieraTest.getScacchiera()[6][0] = new Torre('B');
        scacchieraTest.getScacchiera()[7][5] = new Re('B');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);
        assertTrue(scacchieraTest.getScacchiera()[6][0] instanceof Torre);
        assertTrue(scacchieraTest.getScacchiera()[7][5] instanceof Re);

        // arrocco corto non riuscito a causa dei pezzi gi� spostati
        comando = "0-0";
        giocatore = "Nero";
        tipo = 0;
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }

        scacchieraTest.getScacchiera()[2][5] = new Torre('N');
        scacchieraTest.getScacchiera()[3][4] = new Re('N');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

        assertTrue(scacchieraTest.getScacchiera()[2][5] instanceof Torre);
        assertTrue(scacchieraTest.getScacchiera()[3][4] instanceof Re);
    }

}

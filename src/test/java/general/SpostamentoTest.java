package general;

import org.junit.jupiter.api.Test;
import pezzi.*;

import static org.junit.jupiter.api.Assertions.*;

public class SpostamentoTest {

    @Test
    public void spostamentoCavalloTest() {

        // spostamento riuscito
        String comando = "Ca6";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoCavallo();

        // spostamento non riuscito per comando errato
        comando = "Cd5";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoCavallo();

        // Spostamento riuscito
        comando = "Cd5";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[4][5] = new Cavallo('B');
        scacchieraTest.getScacchiera()[7][6] = null;
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoCavallo();

    }

    @Test
    public void spostamentoReTest() {

        // spostamento riuscito
        String comando = "Re7";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][4] = null;
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoRe();

        // spostamento non riuscito perch� il Re � sotto scacco
        comando = "Rg7";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }

        }
        scacchieraTest.getScacchiera()[0][7] = new Re('N');
        scacchieraTest.getScacchiera()[7][6] = new Donna('B');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoRe();

    }

    @Test
    public void spostamentoAlfiereTest() {

        // spostamento non riuscito a causa di un pezzo in mezzo al percorso
        String comando = "Ae6";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoAlfiere();

        // spostamento riuscito
        comando = "Aa5";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[6][3] = null;
        scacchieraTest.getScacchiera()[6][3] = new Alfiere('B');
        scacchieraTest.getScacchiera()[6][2] = null;
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoAlfiere();

    }

    @Test
    public void spostamentoTorreTest() {

        // spostamento riuscito
        String comando = "Ta5";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][0] = null;
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoTorre();

        // spostamento non riuscito a causa del pedone in mezzo al percorso
        comando = "Ta4";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoTorre();

    }

    @Test
    public void spostamentoDonnaTest() {

        // spostamento riuscito
        String comando = "Df4";
        String giocatore = "Nero";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][3] = null;
        scacchieraTest.getScacchiera()[2][3] = new Donna('N');
        scacchieraTest.getScacchiera()[0][3] = null;
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoDonna();

        // spostamento non riuscito a causa del percorso occupato da un pezzo
        comando = "Df3";
        giocatore = "Bianco";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoDonna();

        // spostamento non riuscito a causa di un comando errato
        comando = "De6";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoDonna();

    }

    @Test
    public void spostamentoPedoneTest() {

        // spostamento riuscito
        String comando = "a3";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        new Spostamento(comando, giocatore, scacchieraTest);

        // spostamento non riuscito per comando errato
        comando = "d5";
        giocatore = "bianco";
        scacchieraTest = new Scacchiera();
        new Spostamento(comando, giocatore, scacchieraTest);

    }

    @Test
    public void verificaCondizioniArroccoTest() {

        String comando;
        String giocatore;
        Scacchiera scacchieraTest;

        // arrocco lungo
        comando = "0-0-0";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }

        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        scacchieraTest.getScacchiera()[0][0] = new Torre('N');
        new Spostamento(comando, giocatore, scacchieraTest);

        // arrocco corto
        comando = "0-0";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][7] = new Torre('N');
        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        new Spostamento(comando, giocatore, scacchieraTest);

        // arrocco non riuscito
        comando = "0-0";
        giocatore = "Nero";
        scacchieraTest = new Scacchiera();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][7] = new Torre('N');
        scacchieraTest.getScacchiera()[1][3] = new Re('N');
        new Spostamento(comando, giocatore, scacchieraTest);

    }

    @Test
    public void isSottoScaccoTest() {

        // caso in cui il re � sotto scacco
        Scacchiera test = new Scacchiera();
        int rigaRe = 1;
        int colonnaRe = 6;
        String giocatore = "Nero";
        test = new Scacchiera();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test.getScacchiera()[i][j] = null;
            }
        }
        test.getScacchiera()[0][7] = new Re('N');
        test.getScacchiera()[2][7] = new Re('B');
        test.getScacchiera()[1][5] = new Donna('B');

        // caso in cui il re non � sotto scacco
        test = new Scacchiera();
        rigaRe = 1;
        colonnaRe = 4;
        giocatore = "Nero";
        test.getScacchiera()[1][4] = null;

    }

    @Test
    public void spostamentoArroccoTest() {

        // arrocco corto riuscito
        String comando = "0-0";
        String giocatore = "Nero";
        int tipo = 0;
        Scacchiera scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][7] = new Torre('N');
        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

        // arrocco lungo riuscito
        comando = "0-0-0";
        giocatore = "Nero";
        tipo = 1;
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }
        scacchieraTest.getScacchiera()[0][0] = new Torre('N');
        scacchieraTest.getScacchiera()[0][4] = new Re('N');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

        // arrocco lungo non riuscito a causa dei pezzi gi� spostati
        comando = "0-0-0";
        giocatore = "Bianco";
        tipo = 1;
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }

        scacchieraTest.getScacchiera()[6][0] = new Torre('B');
        scacchieraTest.getScacchiera()[7][5] = new Re('B');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

        // arrocco corto non riuscito a causa dei pezzi gi� spostati
        comando = "0-0";
        giocatore = "Nero";
        tipo = 0;
        scacchieraTest = new Scacchiera();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                scacchieraTest.getScacchiera()[i][j] = null;
            }
        }

        scacchieraTest.getScacchiera()[2][5] = new Torre('N');
        scacchieraTest.getScacchiera()[3][4] = new Re('N');
        spost = new Spostamento(comando, giocatore, scacchieraTest);
        spost.spostamentoArrocco(tipo);

    }

}

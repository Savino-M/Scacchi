package general;

import org.junit.jupiter.api.Test;
import pezzi.Alfiere;
import pezzi.Donna;

import static org.junit.jupiter.api.Assertions.*;

public class MovimentoTest {

    @Test
    void testAdiacente() {
        
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        int x = 7;
        int y = 4;
        int caso = 2;
        String risultatoAtteso = "7,4";
        String risultatoRicerca;
        scacchieraTest.getScacchiera()[6][4] = null;
        risultatoRicerca = Movimento.adiacente(giocatore, scacchieraTest, x, y, caso);

    }

    @Test
    void testDirezione() {
        int rigaDestinazione = 3;
        int colDestinazione = 7;
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        String p = "Torre";
        String risultatoRicerca;
        String risultatoAtteso = "7,7,4";
        scacchieraTest.getScacchiera()[6][7] = null;

        risultatoRicerca = Ricerca.trovaLato(rigaDestinazione, colDestinazione, giocatore, scacchieraTest, p);

    }

    @Test
    void testSpostaLato() {
        
        int rigaPartenza = 4;
        int colPartenza = 6;
        int spostamento = 4;
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[4][6] = new Donna('B');
        scacchieraTest.getScacchiera()[7][3] = null;
        int x1 = 4;
        int y1 = 6 - 1;
        int caso = 3;
        boolean cattura = false;
        String pezzo = "Donna";
        boolean risultatoMovimento;
        risultatoMovimento = Movimento.spostaLato(rigaPartenza, colPartenza, spostamento, scacchieraTest, x1, y1, caso,
                cattura, pezzo);

    }

    @Test
    void testDirezioneCavallo() {
        
        int rigaDestinazione = 5;
        int colDestinazione = 5;
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        String risultatoRicerca;
        String risultatoAtteso = "7,6,2";

        risultatoRicerca = Ricerca.trovaCavallo(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);

    }

    @Test
    void testSpostaCavallo() {
        
        int rigaPart = 7;
        int colPart = 1;
        int x = 5;
        int y = 2;
        Scacchiera scacchieraTest = new Scacchiera();
        boolean cattura = false;
        boolean risultatoSpostamento = Movimento.spostaCavallo(rigaPart, colPart, scacchieraTest, x, y, cattura);

    }

    @Test
    void testDiagonale() {
        
        int rigaDestinazione = 3;
        int colDestinazione = 1;
        String giocatore = "bianco";
        int x1 = 7;
        int y1 = 5;
        int caso = 1;
        String pezzo = "Alfiere";
        String risultatoRicerca;
        String risultatoAtteso = "7,5,1";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[6][4] = null;
        risultatoRicerca = Movimento.diagonale(rigaDestinazione, colDestinazione, giocatore, scacchieraTest, x1, y1,
                caso, pezzo);

    }

    @Test
    void testSpostaDiagonale() {
        
        int rigaPart = 7;
        int colPart = 5;
        int x = 6;
        int y = 4;
        int spostamento = 1;
        int caso = 1;
        String pezzo = "Alfiere";
        boolean cattura = false;
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[6][4] = null;

        Scacchiera scacchieraAttesa = new Scacchiera();
        scacchieraAttesa.getScacchiera()[7][5] = null;
        scacchieraAttesa.getScacchiera()[6][4] = new Alfiere('B');
        boolean risultatoSpostamento = Movimento.spostaDiagonale(rigaPart, colPart, spostamento, scacchieraTest, x, y,
                caso, cattura, pezzo);

    }

}

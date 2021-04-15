package general;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pezzi.Alfiere;
import pezzi.Pedone;
import pezzi.Re;
import pezzi.Torre;

public class RicercaTest {

    @Test
    void testTrovaRe() {
	int rigaDestinazione = 7;
	int colDestinazione = 5;
	String giocatore = "bianco";
	Scacchiera scacchieraTest = new Scacchiera();
	String risultatoRicerca;
	String risultatoAtteso = "7,4";
	scacchieraTest.getScacchiera()[7][5] = null;
	
	// Test ricerca posizione re orizzontale
	risultatoRicerca = Ricerca.trovaRe(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
	
	scacchieraTest.getScacchiera()[7][4] = null;
	scacchieraTest.getScacchiera()[5][4] = new Re('B');
	rigaDestinazione = 4;
	colDestinazione = 5;
	risultatoAtteso = "5,4";
	
	// Test ricerca posizione re diagonale
	risultatoRicerca = Ricerca.trovaRe(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
	
	rigaDestinazione = 4;
	colDestinazione = 4;
	
	// Test ricerca posizione re verticale
	risultatoRicerca = Ricerca.trovaRe(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
    }

    @Test
    void testTrovaDiagonale() {
	int rigaDestinazione = 2;
	int colDestinazione = 4;
	String giocatore = "bianco";
	String pezzo = "Alfiere";
	Scacchiera scacchieraTest = new Scacchiera();
	String risultatoRicerca;
	String risultatoAtteso = "5,7,3";
	scacchieraTest.getScacchiera()[7][5] = null;
	scacchieraTest.getScacchiera()[5][7] = new Alfiere('B');
	
	// Test ricerca nella diagonale
	
	risultatoRicerca = Ricerca.trovaDiagonale(rigaDestinazione, colDestinazione, giocatore, scacchieraTest, pezzo);
	assertEquals(risultatoAtteso, risultatoRicerca);
    }

    @Test
    void testTrovaCavallo() {
	int rigaDestinazione = 5;
	int colDestinazione = 5;
	String giocatore = "bianco";
	Scacchiera scacchieraTest = new Scacchiera();
	
	String risultatoRicerca;
	String risultatoAtteso = "7,6,2";
	
	
	risultatoRicerca = Ricerca.trovaCavallo(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
	
	scacchieraTest.getScacchiera()[6][3] = null;
	rigaDestinazione = 6;
	colDestinazione = 3;
	risultatoAtteso = "7,1,1";
	
	risultatoRicerca = Ricerca.trovaCavallo(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
    }

    @Test
    void testTrovaPedone() {
	int rigaDestinazione = 5;
	int colDestinazione = 5;
	String giocatore = "bianco";
	Scacchiera scacchieraTest = new Scacchiera();	
	int risultatoRicerca;
	int risultatoAtteso = 1;
	
	// Testo ricerca pedone per passo da 1
	risultatoRicerca = Ricerca.trovaPedone(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
	
	rigaDestinazione = 4;
	colDestinazione = 1;
	risultatoAtteso = 2;
	
	// Testo ricerca pedone per passo da 2
	risultatoRicerca = Ricerca.trovaPedone(rigaDestinazione, colDestinazione, giocatore, scacchieraTest);
	assertEquals(risultatoAtteso, risultatoRicerca);
    }

    @Test
    void testVerificaCatturaDaPedone() {
	int rigaTarget = 5;
	int colTarget = 3;
	String giocatore = "bianco";
	int colMangiatore = 2;
	int cod = 0;
	Scacchiera scacchieraTest = new Scacchiera();
	boolean risultatoVerifica;
	
	
	scacchieraTest.getScacchiera()[5][3] = new Pedone('N');
	
	// Test verifica cattura normale da un pedone
	
	risultatoVerifica = Ricerca.verificaCatturaDaPedone(rigaTarget, colTarget, giocatore, colMangiatore, cod, scacchieraTest);
	assertTrue(risultatoVerifica);
	
	scacchieraTest.getScacchiera()[1][5] = null;
	scacchieraTest.getScacchiera()[3][5] = new Pedone('N');
	scacchieraTest.getScacchiera()[3][4] = new Pedone('B');
	rigaTarget = 2;
	colTarget = 5;
	colMangiatore = 4;
	
	// Test verifica cattura en passant da un pedone
	risultatoVerifica = Ricerca.verificaCatturaDaPedone(rigaTarget, colTarget, giocatore, colMangiatore, cod, scacchieraTest);
	assertTrue(risultatoVerifica);
    }

    @Test
    void testTrovaLato() {
	int rigaDestinazione = 3;
	int colDestinazione = 7;
	String giocatore = "bianco";
	Scacchiera scacchieraTest = new Scacchiera();	
	String p = "Torre";
	String risultatoRicerca;
	String risultatoAtteso = "7,7,4";
	
	scacchieraTest.getScacchiera()[6][7] = null;
	
	// Test ricerca laterale verticale
	
	risultatoRicerca = Ricerca.trovaLato(rigaDestinazione, colDestinazione, giocatore, scacchieraTest, p);
	assertEquals(risultatoAtteso, risultatoRicerca);
	
	scacchieraTest.getScacchiera()[4][7] = new Torre('B');
	rigaDestinazione = 4;
	colDestinazione = 0;
	risultatoAtteso = "4,7,7";
	
	
	// Test ricerca laterale orizzontale
	risultatoRicerca = Ricerca.trovaLato(rigaDestinazione, colDestinazione, giocatore, scacchieraTest, p);
	assertEquals(risultatoAtteso, risultatoRicerca);
    }

}
package pezzi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import general.Scacchiera;
import general.Spostamento;

public class PedoneTest {


    @Test
    void testPedone() {
	Pedone p = new Pedone('N');
	Pedone p2 = new Pedone('B');
	
	assertNotNull(p);
	assertNotNull(p2);
    }

    @Test
    void testEnPassant() {
	
	Scacchiera scacchieraTest = new Scacchiera();
	int rigaDest = 3;
	int colonnaDest = 5;
	String comando = "f4";
	String giocatore = "bianco";
	Spostamento spost = new Spostamento(comando, giocatore, scacchieraTest);
	assertTrue(Pedone.enPassant(rigaDest, colonnaDest, giocatore, scacchieraTest));
	
	scacchieraTest = new Scacchiera();
	rigaDest = 4;
	colonnaDest = 3;
	comando = "d5";
	giocatore = "nero";
	spost = new Spostamento(comando, giocatore, scacchieraTest);
	assertTrue(Pedone.enPassant(rigaDest, colonnaDest, giocatore, scacchieraTest));
    }

    @Test
    
    void testMove() {
	Pedone p = new Pedone('B');
	Scacchiera scacchieraTest = new Scacchiera();	
	
	int rigaDest = 4;
	int colonnaDest = 2;
	int tipo = 2;
	String giocatore = "bianco";
	p.move(rigaDest, colonnaDest, scacchieraTest, tipo, giocatore);
	
	// Test movimento bianco da 2 passi
	
	assertNull(scacchieraTest.getScacchiera()[6][2]);
	assertTrue(scacchieraTest.getScacchiera()[4][2] instanceof Pedone);
	
	rigaDest = 5;
	colonnaDest = 4;
	tipo = 1;
	scacchieraTest = new Scacchiera();
	
	p.move(rigaDest, colonnaDest, scacchieraTest, tipo, giocatore);
	
	// Test movimento bianco da 1 passo
	assertNull(scacchieraTest.getScacchiera()[6][4]);
	assertTrue(scacchieraTest.getScacchiera()[5][4] instanceof Pedone);
	
	
	p = new Pedone('N');
	rigaDest = 2;
	colonnaDest = 3;
	tipo = 1;
	giocatore = "nero";
	scacchieraTest = new Scacchiera();
	
	p.move(rigaDest, colonnaDest, scacchieraTest, tipo, giocatore);
	
	// Test movimento bianco da 1 passo
	assertNull(scacchieraTest.getScacchiera()[1][3]);
	assertTrue(scacchieraTest.getScacchiera()[2][3] instanceof Pedone);
	
	p = new Pedone('N');
	rigaDest = 3;
	colonnaDest = 3;
	tipo = 2;
	giocatore = "nero";
	scacchieraTest = new Scacchiera();
	
	p.move(rigaDest, colonnaDest, scacchieraTest, tipo, giocatore);
	
	// Test movimento bianco da 2 passo
	assertNull(scacchieraTest.getScacchiera()[1][3]);
	assertTrue(scacchieraTest.getScacchiera()[3][3] instanceof Pedone);
	
    }

    @Test
    void testCattura() {
	Pedone p;
	int rigaTarget = 3;
	int colTarget = 5;
	Scacchiera scacchieraTest = new Scacchiera();
	String giocatore = "bianco";
	int colMangiatore = 6;
	
	scacchieraTest.getScacchiera()[6][6] = null;
	scacchieraTest.getScacchiera()[4][6] = new Pedone('B');
	scacchieraTest.getScacchiera()[1][5] = null;
	scacchieraTest.getScacchiera()[3][5] = new Pedone('N');
	
	p = (Pedone) scacchieraTest.getScacchiera()[4][6];
	p.cattura(rigaTarget, colTarget, scacchieraTest, giocatore, colMangiatore);
	
	// Test cattura da un pedone bianco
	assertNull(scacchieraTest.getScacchiera()[4][6]);
	assertTrue(scacchieraTest.getScacchiera()[3][5] instanceof Pedone);	
	
	p=null;
	rigaTarget = 2;
	colTarget = 2;
	scacchieraTest = new Scacchiera();
	giocatore = "nero";
	colMangiatore = 1;
	scacchieraTest.getScacchiera()[2][2] = new Pedone('B');
	p = (Pedone) scacchieraTest.getScacchiera()[1][1];
	p.cattura(rigaTarget, colTarget, scacchieraTest, giocatore, colMangiatore);
	
	assertNull(scacchieraTest.getScacchiera()[1][1]);
	assertTrue(scacchieraTest.getScacchiera()[2][2] instanceof Pedone);	
	
	p=null;
	rigaTarget = 2;
	colTarget = 2;
	scacchieraTest = new Scacchiera();
	giocatore = "nero";
	colMangiatore = 1;
	scacchieraTest.getScacchiera()[2][2] = new Pedone('B');
	p = (Pedone) scacchieraTest.getScacchiera()[1][1];
    Pedone p1 = (Pedone) scacchieraTest.getScacchiera()[2][2];
    p.setEnPassant(true);
    
	p.cattura(rigaTarget, colTarget, scacchieraTest, giocatore, colMangiatore);
	
	assertNull(scacchieraTest.getScacchiera()[1][1]);
	assertTrue(scacchieraTest.getScacchiera()[2][2] instanceof Pedone);	
	
    }

}

package pezzi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import general.*;

public class TorreTest {

    @Test
    public void moveTest() {

	Torre t = new Torre('N');
	Scacchiera scacchieraTest = new Scacchiera();
	int rigaP = 0;
	int colonnaP = 7;
	int rigaD = 3;
	int colonnaD = 7;
	int spost = 3;
	
	scacchieraTest.getScacchiera()[1][7] = null;
	t.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertNull(scacchieraTest.getScacchiera()[0][7]);
	assertTrue(scacchieraTest.getScacchiera()[3][7] instanceof Torre);
    
	t = new Torre('B');
	scacchieraTest = new Scacchiera();
	rigaP = 7;
	colonnaP = 0;
	rigaD = 3;
	colonnaD = 0;
	spost = 4;
	
	scacchieraTest.getScacchiera()[6][0] = null;
	t.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertNull(scacchieraTest.getScacchiera()[7][0]);
	assertTrue(scacchieraTest.getScacchiera()[3][0] instanceof Torre);
    
	t = new Torre('B');
	scacchieraTest = new Scacchiera();
	rigaP = 4;
	colonnaP = 0;
	rigaD = 4;
	colonnaD = 2;
	spost = 2;
	
	scacchieraTest.getScacchiera()[7][0] = null;
	scacchieraTest.getScacchiera()[4][0] = new Torre ('B');
	t.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertNull(scacchieraTest.getScacchiera()[4][0]);
	assertTrue(scacchieraTest.getScacchiera()[4][2] instanceof Torre);
	
	
	t = new Torre('N');
	scacchieraTest = new Scacchiera();
	rigaP = 4;
	colonnaP = 4;
	rigaD = 4;
	colonnaD = 1;
	spost = 3;
	
	scacchieraTest.getScacchiera()[0][0] = null;
	scacchieraTest.getScacchiera()[4][4] = new Torre ('N');
	t.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertNull(scacchieraTest.getScacchiera()[4][4]);
	assertTrue(scacchieraTest.getScacchiera()[4][1] instanceof Torre);
	
    }

    @Test
    public void catturaTest() {
    		
	Torre t = new Torre('N');
	Scacchiera scacchieraTest = new Scacchiera();
	int rigaP = 0;
	int colonnaP = 7;
	int rigaD = 3;
	int colonnaD = 7;
	int spost = 3;
	
	scacchieraTest.getScacchiera()[3][7] = new Pedone ('B');
	scacchieraTest.getScacchiera()[1][7] = null;
	t.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertTrue(scacchieraTest.getScacchiera()[3][7] instanceof Torre);
    
	t = new Torre('B');
	scacchieraTest = new Scacchiera();
	rigaP = 7;
	colonnaP = 0;
	rigaD = 3;
	colonnaD = 0;
	spost = 4;
	
	scacchieraTest.getScacchiera()[3][0] = new Donna ('N');
	scacchieraTest.getScacchiera()[6][0] = null;
	t.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertTrue(scacchieraTest.getScacchiera()[3][0] instanceof Torre);
    
	t = new Torre('B');
	scacchieraTest = new Scacchiera();
	rigaP = 4;
	colonnaP = 0;
	rigaD = 4;
	colonnaD = 2;
	spost = 2;
	
	scacchieraTest.getScacchiera()[4][2] = new Cavallo ('N');
	scacchieraTest.getScacchiera()[7][0] = null;
	scacchieraTest.getScacchiera()[4][0] = new Torre ('B');
	t.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertTrue(scacchieraTest.getScacchiera()[4][2] instanceof Torre);
	
	
	t = new Torre('N');
	scacchieraTest = new Scacchiera();
	rigaP = 4;
	colonnaP = 4;
	rigaD = 4;
	colonnaD = 1;
	spost = 3;
	
	scacchieraTest.getScacchiera()[4][1] = new Torre ('B');
	scacchieraTest.getScacchiera()[0][0] = null;
	scacchieraTest.getScacchiera()[4][4] = new Torre ('N');
	t.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);
	
	assertTrue(scacchieraTest.getScacchiera()[4][1] instanceof Torre);
	
    }
}

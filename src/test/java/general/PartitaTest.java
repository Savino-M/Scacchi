package general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class PartitaTest {

    @Test
    public void scaccoMattoTest() {
    
	// scacco matto vero
    
	Scacchiera test = new Scacchiera();
	int[] testScacco = new int[4];
	
	/*
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		test.getScacchiera()[i][j] = null;
	    }
	}
	test.getScacchiera()[0][0] = new Re('B');
	test.getScacchiera()[4][1] = new Re('N');
	test.getScacchiera()[1][0] = new Pedone('B');
	test.getScacchiera()[2][0] = new Pedone('N');
	test.getScacchiera()[0][2] = new Torre('N');
	test.getScacchiera()[2][6] = new Pedone('B');
	test.getScacchiera()[2][7] = new Alfiere('B');
	testScacco[0] = 0;
	testScacco[1] = 0;
	testScacco[2] = 4;
	testScacco[3] = 1;
	Partita.setRe(testScacco);
	Partita.getPlayer();
	assertTrue(Partita.scaccoMatto(test));
	*/
	
	//scacco matto falso
	test = new Scacchiera();
	testScacco[0] = 7;
	testScacco[1] = 4;
	testScacco[2] = 0;
	testScacco[3] = 4;
	Partita.setRe(testScacco);
	assertFalse(Partita.scaccoMatto(test));
    }

}

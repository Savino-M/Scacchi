package general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import java.util.ArrayList;

public class GestoreStoricoTest {
	
	@Test
	public void resettaListaTest() {
		
		ArrayList<String> mosse = new ArrayList<String>();
		mosse.add("S");
	    ArrayList<String> cattureBianco = new ArrayList<String>();
	    cattureBianco.add("A");
	    ArrayList<String> cattureNero = new ArrayList<String>();
	    cattureNero.add("B");
	    
	    ArrayList<String> mosseAtt = new ArrayList<String>();
	    ArrayList<String> cattureBiancoAtt = new ArrayList<String>();
	    ArrayList<String> cattureNeroAtt = new ArrayList<String>();

	    GestoreStorico.resettaLista();
	    mosse.clear();
	    cattureBianco.clear();
	    cattureNero.clear();
	 
	    assertEquals(mosse,mosseAtt);
	    assertEquals(cattureBianco,cattureBiancoAtt);
	    assertEquals(cattureNero,cattureNeroAtt);

	    }
}

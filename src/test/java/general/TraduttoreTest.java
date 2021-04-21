package general;

import general.Partita.Command;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TraduttoreTest
{

    @Test
    public void traduciRigaTest()
    {

        char notazioneRiga = '1';
        int riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(7, riga);

        notazioneRiga = '4';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(4, riga);

        notazioneRiga = '3';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(5, riga);

        notazioneRiga = '8';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(0, riga);

        notazioneRiga = '2';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(6, riga);

        notazioneRiga = '6';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(2, riga);

        notazioneRiga = '5';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(3, riga);

        notazioneRiga = '7';
        riga = Traduttore.traduciRiga(notazioneRiga);
        assertEquals(1, riga);
    }

    @Test
    public void traduciColonna()
    {

        char notazioneColonna = 'b';
        int colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(1, colonna);

        notazioneColonna = 'a';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(0, colonna);

        notazioneColonna = 'c';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(2, colonna);

        notazioneColonna = 'd';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(3, colonna);

        notazioneColonna = 'e';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(4, colonna);

        notazioneColonna = 'f';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(5, colonna);

        notazioneColonna = 'g';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(6, colonna);

        notazioneColonna = 'h';
        colonna = Traduttore.traduciColonna(notazioneColonna);
        assertEquals(7, colonna);
    }

    @Test
    public void traduciComando()
    {

        String comando = "play";
        Command com = Traduttore.traduciComando(comando);
        assertEquals(Command.play, com);

        comando = "quit";
        com = Traduttore.traduciComando(comando);
        assertEquals(Command.quit, com);

        comando = "e3";
        com = Traduttore.traduciComando(comando);
        assertEquals(Command.spostamento, com);

        comando = "help";
        com = Traduttore.traduciComando(comando);
        assertEquals(Command.help, com);

        comando = "Axf4";
        com = Traduttore.traduciComando(comando);
        assertEquals(Command.cattura, com);

    }
}

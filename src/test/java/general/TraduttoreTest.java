package general;

import general.Partita.Command;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TraduttoreTest {

    @Test
    public void traduciRigaTest() {

        char notazioneRiga = '1';
        int riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '4';
        riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '3';
        riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '8';
        riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '2';
        riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '6';
        riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '5';
        riga = Traduttore.traduciRiga(notazioneRiga);

        notazioneRiga = '7';
        riga = Traduttore.traduciRiga(notazioneRiga);

    }

    @Test
    public void traduciColonna() {

        char notazioneColonna = 'b';
        int colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'a';
        colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'c';
        colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'd';
        colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'e';
        colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'f';
        colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'g';
        colonna = Traduttore.traduciColonna(notazioneColonna);

        notazioneColonna = 'h';
        colonna = Traduttore.traduciColonna(notazioneColonna);

    }

    @Test
    public void traduciComando() {

        String comando = "play";
        Command com = Traduttore.traduciComando(comando);

        comando = "quit";
        com = Traduttore.traduciComando(comando);

        comando = "e3";
        com = Traduttore.traduciComando(comando);

        comando = "help";
        com = Traduttore.traduciComando(comando);

        comando = "Axf4";
        com = Traduttore.traduciComando(comando);

    }
    
}

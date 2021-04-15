# scacchi ![CI/CD](https://github.com/softeng1920-inf-uniba/progetto1920-knuth/workflows/CI/CD/badge.svg) [![Coverage Status](https://coveralls.io/repos/github/softeng1920-inf-uniba/progetto1920-knuth/badge.svg?t=NMuq2h)](https://coveralls.io/github/softeng1920-inf-uniba/progetto1920-knuth)

La struttura della repository si presenta nel seguente modo:
```
|-- .github
|    |-- workflows
|    |      |-- ingsw1920.yml
|-- build
|    |-- reports
|    |      |-- checkstyle
|    |      |-- findbugs
|    |      |-- jacoco/tests
|    |      |-- tests/test
|–– config
|    |–– checkstyle
|–– doc
|    |–– drawings
|    |–– javadoc 
|    |–– Report.md
|–– lib
|–– res
|–– src
|    |–– main
|    |–– test
|–– .gitignore
|–– README.md
```

Nel seguito si dettagliano i ruoli dei diversi componenti:
- **.github/workflows/ingsw1920.yml**: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di GitHub Actions;
- **build**: ospita la sottocartella *reports*, contenente gli output dei tool automatici di test e controllo di qualità;
- **config**: ospita i file di configurazione. L’unica configurazione di base richiesta è quella per il tool checkstyle;
- **doc**: in questa cartella deve essere inserita tutta la documentazione relativa al progetto. In particolare, in *drawings* dovranno essere salvati i diagrammi UML e *javadoc* ospiterà la documentazione generata automaticamente per il codice Java. Il file *Report.md* rappresenta la relazione finale del progetto;
- **lib**: creata per includere eventuali *jar* di librerie esterne utilizzate dal progetto.
- **res**: la cartella deve contenere tutte le risorse usate dal sistema (immagini, testi ecc.)
- **src**: la cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In *main* ci saranno i file sorgente e *test* conterrà i test di unità previsti.


In alcune cartelle è possibile notare la presenza di un unico file nascosto “.keep”: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.

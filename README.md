# Scacchi

La struttura della repository si presenta nel seguente modo:
```
|–– doc
|    |–– drawings
|    |–– javadoc 
|    |–– Report.md
|–– src
|    |–– main
|    |–– test
|–– .gitignore
|–– README.md
|--pom.xml
```

Nel seguito si dettagliano i ruoli dei diversi componenti:
- **doc**: in questa cartella deve essere inserita tutta la documentazione relativa al progetto. In particolare, in *drawings* dovranno essere salvati i diagrammi UML e *javadoc* ospiterà la documentazione generata automaticamente per il codice Java. Il file *Report.md* rappresenta la relazione finale del progetto;
- **src**: la cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In *main* ci saranno i file sorgente e *test* conterrà i test di unità previsti.
- **pom.xml**: file di configurazione per Maven;

In alcune cartelle è possibile notare la presenza di un unico file nascosto “.keep”: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.

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
|–– scacchi.iml
|–– README.md
|--pom.xml
```

Nel seguito si dettagliano i ruoli dei diversi componenti:
- **doc**: in questa cartella deve essere inserita tutta la documentazione relativa al progetto. In particolare, in *drawings* dovranno essere salvati i diagrammi UML e *javadoc* ospiterà la documentazione generata automaticamente per il codice Java. Il file *Report.md* rappresenta la relazione finale del progetto;
- **src**: la cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In *main* ci saranno i file sorgente e *test* conterrà i test di unità previsti.
- **pom.xml**: file di configurazione per Maven;

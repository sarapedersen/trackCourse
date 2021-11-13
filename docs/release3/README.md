## Brukerhistorier
Applikasjonen vår er basert på forskjellige brukerhistorier. Se brukerhistoriene [her](brukerhistorier.md).

## Oppbygning av applikasjonen
### Modulering
I mappen trackcourse har vi to moduler, "core" og "fxui". I core mappen finner vi javafilene til logikken, og deres tilhørende tester. Tilsvarende ligger javafx klassene til appen i fxui mappen, med deres tester. 

### Applikasjonen
App klassen vår som kjører applikasjonen heter [App.java](../../trackcourse/fxui/src/main/java/trackcourse/ui/App.java) og ligger under trackcourse/fxui/src/main/java/trackcouse/ui. Klassen bruker launch metoden til å kjøre appen og kjøres ved å skrive mvn javafx:run ifra fxui mappen i terminalen. (Se instruksjoner for kjøring av applikasjonen nederst i dokumentet)

I tillegg har vi implementert en AppController klasse som binder logikken vår opp mot brukergrensesnittet. Kontrollerklassen implementerer lagring av data, en submit metode som legger til et nytt Subject objekt med korresponderende variabler og en funksjon for oppdatering av listen.

(------------- SE OVER DENNE DELEN TIL SLUTT, I TILFELLE NY IMPLEMENTASJON ---------------)
Hoveddelen av logikken vår kommer gjennom Subject og FileHandler klassen. Subject klassen bruker en variabel kalt “name” til å identifisere de ulike fagene. I tillegg har vi implementert et HashMap som holder oversikt over gjennomsnittet til de ulike kriteriene vi har implementert (difficulty, timeconsumption og entertainment). HashMappet har i tillegg en oversikt over antall ganger et fag har blitt vurdert. 
Grunnen til at vi valgte en HashMap-løsning istedenfor en liste-løsning var for senke kjøretiden og gjøre appen mer effektiv. Siden man må iterere gjennom en liste hver gang man ønsker gjennomsnittet, vil en HashMap løsning være mer effektiv så lenge vi har få variabler som brukeren kan gi en vurdering på.

FileHandlerApp-klasse står for lagringen av dataen. Før fungerte denne med en txt fil, men nå lagres det via JSON. Det fungerer nå også å lasgre og laste inn fritt.
(------------- SE OVER DENNE DELEN TIL SLUTT, I TILFELLE NY IMPLEMENTASJON ---------------)

I resource/app mappen finner man [App.fxml](../../trackcourse/fxui/src/main/resources/trackcourse/ui/App.fxml) filen vår. Brukergrensesnittet vårt er tegnet opp med Scenebuilder. 

## Hvordan kjøre applikasjonen
* Fra mappen "trackcourse", skriv "mvn clean compile install"
* Bytt til mappen "fxui" og kjør: "mvn javafx:run"
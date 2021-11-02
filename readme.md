[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2184/gr2184)


Kodingsprosjektet vårt består av to moduler, "core" og "fxui". I core mappen finner vi javafilene til logikken, og deres tilhørende tester. Tilsvarende ligger javafx klassene til appen i fxui mappen, med deres tester. Appen heter TrackCourse, så modulene ligger under denne mappen. 

Mappen som kjører applikasjonen vår heter app og ligger under TrackCourse/fxui/src/main/java/trackcouse/ui. I mappen finner vi App-filen som er filen som kjører applikasjonen. App filen bruker launch metoden til å kjøre appen og kjøres ved å skrive mvn javafx:run i terminalen. Denne kommandoen må kjøres fra fxui mappen. 

I tillegg har vi implementert en AppController klasse som binder logikken vår opp mot brukergrensesnittet. Foreløpig implementasjon av kontrollerklassen innebærer lagring av data, en submit metode som legger til et nytt Subject objekt med korresponderende variabler.

Hoveddelen av logikken vår kommer gjennom Subject og FileHandler klassen. Subject klassen bruker en variabel kalt “name” til å identifisere det ulike fagene. I tillegg har vi implementert et HashMap som holder oversikt over gjennomsnittet til de ulike kriteriene vi har implementert (difficulty, timeconsumption og entertainment). HashMappet har i tillegg en oversikt over antall ganger et fag har blitt vurdert. 
Grunnen til at vi valgte en HashMap-løsning istedenfor en liste-løsning var for senke kjøretiden og gjøre appen mer effektiv. Siden man må iterere gjennom en liste hver gang man ønsker gjennomsnittet, vil en HashMap løsning være mer effektiv så lenge vi har få variabler som brukeren kan gi en vurdering på.

FileHandlerApp-klasse står for lagringen av dataen. Før fungerte denne med en txt fil, men nå lagres det via JSON. Det fungerer nå også å lasgre og laste inn fritt.

I resource/app mappen finner man App.fxml filen vår. Brukergrensesnittet vårt er tegnet opp med Scenebuilder. 





## Hvordan kjøre
* Fra mappen "trackcourse", skriv "mvn clean compile install"
* Bytt til mappen "fxui" og kjør: "mvn javafx:run"

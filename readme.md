[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2184/gr2184)

Prosjektet vårt består av tre hovedmoduler: 
- core
- fxui
- springserver

##core:

I core-mappen ligger logikken til appen vår. I rotnivå har vi en pom.xml fil som implementerer jacoco og Jackson slik at det kan brukes i modulen
core mappen består hovedsakelig av tre deler:

- json: Mappen står for den lokale lagringen av appen. Her oppretter hvert "subject" som blir lagret sin egne JSON fil hvor objektet lagres. Vi lagrer dermed kun et objekt per JSON fil.
- main: Main-mappen står for hoveddelen av logikken vår og inneholder tre klasser: FileHandlerApp, Subject og CourseList.

CourseList: //Casper

Subject-klassen inneholder seks variabler: FullName, courseCode, ratings, difficulty, timeconsumption og entertainment. Fullname er navnet til faget, mens courseCode er fagkoden og brukes som primærnøkkel for å hente ut riktig fag fra databasen vår.
ratings er en hashMap som har oversikt over gjennomsnittsvurderingen til hver av de ulike variablene. I tillegg har "ratings" oversikt over hvor mange ganger en variabel har blitt vurdert. 
Når man kaller på en update funksjon multipliseres gjennomsnittet til variabelen med antall ganger variabelen har blitt vurdert. Så legges den nye vurderingen til summen, antall vurderinger økes med en, før man igjen regner ut gjennomsnittet og lagrer det i "ratings".
Grunnen til at vi valgte et HashMap til å finne gjennomsnittet istedenfor å iterere gjennom en liste var for å senke kjøretiden til applikasjonen. 
I dette faget får vi neppe store nok datamengder til at kjøretid blir relevant, men vi har hvertfall implementert logikk som støtter store datamengder.
Vi har i tillegg til HashMapet valgt å implementere tre Arrays som holder oversikt over hver enkelt vurdering. Arraysene kan brukes til å holde oversikt over hver enkelt vurdering slik at man kan for eksempel analysere om det er stor variasjon i vurderingene, eller om det holder seg rundt gjennomsnittet.
Appen vår støtter ikke en slik analyse, så den må utføres i terminalen. Arraysene er mest ment for å gi utviklerene en måte å analysere resultatene på.
I tillegg til set-ere og get-ere har Subject-klassen en "average" funksjon som returnerer det samlede gjennomsnittet til alle variablene ved hjelp av enkel addisjon og divisjon.

FileHandlerApp-klassen er klassen vi bruker til å lagre Subject-objektene til JSON fil.
klassen bruker Jackson-grensesnittet til å lagre hvert objekt som en egen JSON fil. 
- Metoden "writeToJson" tar inn en Collection og bruker "ObjectMapper" til å lagre hvert objekt fra listen 
- Metoden "readFromJson" bruker de eksisterende lagrede objektene og returnerer de i form av en liste. 
- metoden StringSplitter() tar inn en String på formatet: "[{subject1},{subject2},{subject3}]" og konverterer den til en array med strings i formatet: "['{subject1}','{subject2}','{subject3}']". Dette brukes videre i metoden Get()
- metoden Get() sender en GET-request til rest serveren, som henter en String bestående av subjects som er lagret i serveren i JSON-format, som lagres lokalt i "data"-stringen. Videre konverteres "data"-stringen til subjects ved bruk av ObjectMapper som adder hvert subjects til en Collection av subjects, som returneres etter at alle subjects er konvertert.
- metoden Post(Collection<Subject> subs) sender en POST-request til rest serveren. Den tar inn en Collection av subjects og konverterer det til en String ved bruk av ObjectMapper sin writeValueAsString-metode. Videre sender den POST-requesten til serveren.

I tillegg har vi en txt-fil som inneholder alle fagene NTNU har oppført på nettsiden sin. //Casper noe å tilføye?

- test: Test-mappen står for testene til core-mappen. "test" inneholder to test-klasser: FileHandlerAppTest og SubjectTest. 
Vi har i tillegg implementert jacoco i prosjektet vårt i pom.xml filene for å sørge for at vi har tilstrekkelig dekning med testene våre.
Testene er skrevet med standard Junit testframework siden vi føler det er et grensesnitt vi har godt kjennskap til.

FileHandlerAppTest består av 3 tester:
- testWriteToJson oppretter først en liste med subjects, før den oppretter et FileHandlerApp-objekt som kaller på writeToJson-metoden sin. Til slutt sjekker den om filen eksisterer. Filen må eksistere for at testen skal være godkjent
- testReadfromJson bruker samme oppsett som testWriteToJson. Etterpå sjekker den om hver fil er tom. //Kan dere utdype her, skjønte ikke helt testen.

CourseListTest består av 3 tester og tester om validate-metoden til CourseList fungerer og om den henter ut riktig fagnavn gitt en fagkode.

SubjectTest implementerer ganske basis tester, og bruker assertFalse, Equals og True for å verifisere. Eneste ulempen med denne metoden er at det fort kan bli rotete for oss hvis vi skal skrive mange tester til en større app, men foreløpig er det ikke et problem.

##fxui:

fxui-mappen vår består av to hovedmoduler: main og test.

main: main-mappen inneholder to klasser: App og AppController, i tillegg til fxml mappen og css stylingen vi har brukt.
fxml filen er laget ved å bruke scene-builder. Mer om brukergrensesnittet ligger i README-filen under trackcourse
CSS har vi brukt for å style appen vår, slik at den blir mer appelerende for brukeren.

App-klassen har ansvaret for å kjøre appen og inneholder kun en metode (i tillegg til main metoden) som heter start. Når man kjører mvn javafx:run er det main metoden til App-klassen som kalles på.

AppController er klassen vi bruker for å koble logikken til brukergrensesnittet.
- onLoad initialiserer en instans av FileHandlerApp og henter de lagrede subject-objektene ved hjelp av Get()-metoden i FileHandlerApp. Videre sorteres disse basert på vurderinger og legges til i ListViewet
- onSave initialiserer en instans av FileHandlerApp og sender den nåværende listen av subjects til rest serveren ved bruk av Post()-metoden i FileHandlerApp.
- subjectSelected er en metode vi bruker for å hente ut fagkoden til faget og sette det i inputfeltet slik at brukeren alltid vet hvilket fag som er valgt.
- onDetails metoden generer et popupvindu med detaljert informasjon om faget som er valgt og formaterer det på en fin måte.
- onClose metoden lukker dette popupvinduet
- validate-metoden bruker vi til å sjekke om brukeren har skrevet inn en gyldig fagkode. Appen støtter at brukeren kan velge mellom små og store bokstaver.
- sortSubjects er en metode vi bruker for å sortere fagene etter høyeste gjennomsnittsvurdering. Metoden oppretter en ny liste og legger til hvert objekt med hensyn på gjennomsnittsvurdering. Siden metoden bruker en dobbel for-løkke kan kjøretiden bli lang hvis appen inneholder mange fag. Siden vi jobber på en mindre skala valgte vi å ikke implementere en mer avansert sorteringsalgoritme. sortSubjects blir kalt på hver gang brukeren trykker på submit eller load knappen i appen.
- updateLists initialiserer først en tom observableArrayList, før den går gjennom listen med Subjects. For hvert fag lager den til en string på formatet; "fagkode // gjennomsnittsvurdering", og legger det til observableArrayList-en. Etter den har loopen gjennom alle fagene, setter den listen til ListViewet på brukergrensesnittet. Grunnen til at vi konverterer fra vår vanlige ArrayList til observableArrayList, er for at den kan skrives ut til ListViewet i brukergrensesnittet, og gir oss itillegg muligheten til å formatere cellene på en brukervennelig måte.

test: test-mappen tester grensesnittet til appen vår.
AppTest tester om appen greier å opprette en kontroller og å kjøre den.

AppControllerTest inneholder en test som sjekker om AppController kjører konstruktøren på riktig måte.
I tillegg tester den input-feltet og submit-knappen fungerer slik de skal i testValidation-metoden.

##springserver: 

main: main-mappen i springserver inneholder 2 klasser; TrackcourseApplication og TrackcourseController. Vi
bruker Spring rammeverket, spesifikt Spring Boot, for å bygge opp rest serveren vår.
TrackcourseApplication: initialiserer en SpringApplication som vi setter til serverport 8080
TrackcourseController: inneholder to metoder getData() og setData():
- getData() lytter etter GET-requester, og returnerer all data i rest serveren
- setData() lytter etter POST-requester, og returnerer "true" etter dataen er posten til rest serveren

## Hvordan kjøre Trackcourse
1. Gå til mappen "springserver", og kjør kommando "mvn clean install", deretter "mvn spring-boot:run"
2. Åpne en ny terminal og kom deg til hovedmappen; "trackcourse", og kjør deretter kommandoen "mvn clean install"
3. I samme terminal gå til mappen "fxui" og kjør: "mvn javafx:run"

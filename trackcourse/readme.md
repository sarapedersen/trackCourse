# Trackcourse - en fagevalueringsapp #

Vi har laget en applikasjon som er som gir brukeren mulighet til å vurdere ulike fag ved NTNU. For å gi en detaljert beskrivelse av hvert fag har vi valgt å implementere tre variabler: 
- "Difficulty" er den første og er et mål på hvor vanskelig selve faget er. Variabelen omfavner både øvingsopplegget og eksamen.
- "Timeconsumption" er den andre variabelen og er et mål på hvor mye tid som kreves for å fullføre øvingsopplegget, og hvor mye tid brukeren må/måtte legge inn for å få karakteren C.
- "Joy" er den siste variabelen og måler hvor morsomt brukeeren syntes faget var. "Joy" er den mest subjektive variabelen vi har og derfor den mest upålielige, men vi valgte å inkludere variabelen siden scoren vil gjevne seg ut når vi får inn mange nok vurderinger. I tillegg er "joy" kanskje den viktigste variabelen når studenter skal velge fag.

![picture](https://i.imgur.com/oaPPx1A.png)

Bildet over viser et bilde av appen vår sin startside. På venstre siden ser vi et listview med oversikt over alle fagene vi har lagt inn fra tidligere etterfulgt av gjennomsnittet til alle vurderingene til faget. Fagene er sortert etter gjennomsnittsvurdering hvor høyest score står øverst. Kun tidligere vurderte fag kommer opp på listen. 
Gjennomsnittet regnes ut ved at vi har et HashMap med en gjennomsnittsvurdering for hver av de tre variablene for hvert fag. Vi henter ut gjennomsnittsvurderingene og bruker de til å finne gjennomsnittsvurderingen for faget.
Alle variablene vektlegges med lik tyngde.


Øverst på høyre side har vi et input felt hvor brukeren skriver inn faget brukeren ønsker å vurdere. Man kan også hente et tidligere vurdert fag ved å klikke på det på listviewet på venstre siden. Hvis brukeren prøver å legge inn et fag som ikke eksisterer i NTNU sine databaser vil ikke submit knappen vises. Brukeren kan derfor kun vurdere fag som eksisterer. Man kan bare søke opp fag ved bruk av fagkode, ikke med fagnavn.
Under inputfeltet har vi implementert en slider til hver variabel som brukeren kan justere for å vurdere faget. Under slideren har vi tallverdier som gjør det lettere for brukeren å gi en nøyaktig vurdering.

Under sliderne ligger "submit" knappen. Når brukeren trykker på "submit" vil faget som er valgt bli oppdatert med de nye vurderingene. Gjennomsnittet regnes ut på nytt og listen over fag blir sortert i tilfelle de nye vurderingene skulle påvirke rekkefølgen.
Hvis faget ikke har blitt vurdert tidligere blir faget lagt til i listen med vurderingene som er gitt og listen blir sortert.

Vi har også en "load" og en "save" knapp lokalisert under "submit" knappen. 
Save knappen lagrer alle fagene i listen til databasen i JSON format. Hvert fag blir lagret i en egen JSON fil.
Når brukeren trykker på load lastes alle fagene som tidligere har blitt vurdert opp fra databasen. 
Eventuelle fag som brukeren har lagt inn før load blir aktivert blir værende. Listen blir i tillegg sortert slik at fag som ikke har blitt lagret til databasen står på riktig plass i listviewet. 

![picture](https://imgur.com/a/VfBMtbD)

Til slutt har vi en "details" knapp som kun fungerer når et fag er valgt fra listviewet. Når brukeren trykker på knappen åpnes et pop-up vindu med detaljer om faget.
Detaljene som vises er:
- "Average difficulty"
- "Average timeconsumption"
- "Average joy"
- "Average overall"
- "Navn på fag"

//Bilde UML beskrivelse av prosjektet.

Bildet over viser en oversikt over hvordan prosjektet henger sammen.
//TODO:

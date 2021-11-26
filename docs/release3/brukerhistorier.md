## **Se et fags vanskelighetsgrad  (brukerhistorie 1)** 
Jeg som student på NTNU ønsker å planlegge mitt neste semester. Det er viktig for meg å kunne finne en oversikt over de vanskeligste og mest krevende fagene slik at jeg har mulighet til å sette sammen en overkommelig studieplan til dette semesteret. 

For å tilfredstille brukerens krav, har vi innført en applikasjon hvor man kan se hvor vanskelig enkelte fag er i forhold til andre. Her skal man få opp en fagscore som kan sammenlignes i en liste blant andre fag, hvor man enkelt ser hvilke fag som oppleves som krevende av tidligere studenter.

### Krav til funksjonalitet:
- Et listview som viser de fagene som allerede har en rating. 
- Ved hvert enkelt fag i listen skal det vises en gjennomsnittlig verdi basert på tidligere studenters tilbakemeldinger om hvordan faget er. 
- Faglisten skal kunne sorteres etter hvor krevende er, med de tøffeste fagene på toppen.

## **Varsle andre studenter (brukerhistorie 2)** 
Jeg som en erfaren student på NTNU ønsker en plattform der jeg har mulighet til å varsle nye studenter om hvilke fag som kan være svært utfordrene eller tidkrevende. 

For å tilfredstille brukerens krav, har vi innført et inndatapanel hvor brukeren kan skrive inn fagkode, vanskelighetsgrad, hvor tidkrevende faget er og hvor morsomt brukeren synes faget er.

### Krav til funksjonalitet:
- Et textfield for å skrive inn fagkoden.
- Tre slidere for de forskjellige vurderingskriteriene av faget. 
- En submit knapp for å sende inn den all inndata til faglisten.
- En save knapp for å lagre dataen. 
- En load knapp for å hente inn siste data.
- Det skal også være mulig å klikke på et fag i listen og dermed få opp dette faget i inndatapanelet, slik at det blir enda lettere å legge inn flere tilbakemeldinger.

## **Se detaljer for rangering av faget  (brukerhistorie 3)** 
Jeg som stud.ass ved NTNU øsnker jeg å friske opp kunnskapen min i et gammelt fag jeg skal hjelpe nye studenter i. Jeg ønsker at rangeringen av et fag jeg har vurdert skal kunne tas opp igjen og leses i sin helhet, med alle rangeringene jeg har lagt inn i faget. 

For å tilfredsstille brukerens krav, har vi implementert on "detaljer" knapp. Her kan bruker trykke på et tidligere rangert fag, og få full oversikt over alle rangeringselementene, og deres tilhørende rangeringer, samt et gjennomsnitt av alle disse. 

### Krav til funksjonalitet:
-Et eller flere fag må være rangert og lagret i listview. 
-Mulighet til å trykke på et gitt allerede rangert fag og markere det. 
-En "details" knapp som henter tilhørende data lagret for et fag, og viser en tekstpanelt tekstpanel med utdata som fremviser oversikten over rangeringene til det gitte faget. 
-En "close details" knapp som lar bruker lukke oversikten, og fortsette videre rangering.

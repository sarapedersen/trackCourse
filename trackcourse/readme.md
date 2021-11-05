# Fagevaluering #

Appen er ment til å gi scores til ulike fag, basert på deres vanskelighetsgrad, tidsforbruk og fornøyelse. Dette er en nyttig funksjon for studenter som er usikre på hvilke fag de ønsker å ta, og er noe utviklerene bak den selv har savnet. 

- - - -

![picture](https://i.imgur.com/4xAOYJp.png)

Slik ser appen ut for øyeblikket. Vi tenker å oppdatere brukergrensesnittet i release 3. Dette vil innebære å tilpasse brukergrensesnittet til å bli mer brukervennlig. Foreløpig har vi to ulike kolonner som viser fagene brukeren har lagt inn og gjennomsnittsscoren til hvert av disse fagene. 

Vi har valgt å bruke slidere til å gi vurdering fordi det gjør det enkelt for brukeren å gi score. Ulempen med slidere er at det ikke er like oversiktlig som input-felt, derfor kan det hende at vi bytter til input-felt i en senere release.
Sliderene går fra 1 til 20, og scoren til faget er snittet av disse tre. Man legger til evalueringen med submit, og lagrer med save.

Foreløpig har vi ikke implementert en slette-funksjon for enkelt fag, men vi har en resetknapp. I release 3 kommer vi til å implementere logikk slik at det blir mulig å fjerne et enkelt fag.

Slik det er nå blir hvert Subject(fag) lagret til en egen json fil når brukeren trykker på save. Tilsvarende vil alle lagrede Subjects bli lastet opp når man trykker på load. Loadingen fungerer per dags dato slik at Subjects som ikke er lagret blir slettet når man trykker på load. Dette er noe vi skal se på og revurdere i release 3.

![picture](https://i.imgur.com/4oXMTzL.png)
Bildet viser tilstandsdiagrammene laget i en PlantUML fil. Core peker her på fxui, json filen implementerer jackson grensesnittet. Javafx inneholder fxml filene.

- - - -
Se brukerhistorier releasen er basert på i egen mappe

# Erfüllungsscore

## Erklärung des Erfüllungsscores
Der Erfüllungsscore misst, wie gut die Wünsche der Schüler (SuS) erfüllt wurden. Er wird in Prozent angegeben und basiert darauf, welche Wünsche eines Schülers erfüllt wurden und wie wichtig sie für ihn waren.


## **Wie wird der Erfüllungsscore berechnet?**
Die Formel zur Berchnung ist folgende:

![Berechnungsformel](src/Doku/userInfo/bilder/mathematischeFormel.jpg)
$$
\frac{\sum (\text{Punkte} \cdot \text{Gewichtung})}{\text{Anzahl der SuS} \cdot 20}
$$

- **Gewichtung der Wünsche**:

    - Der 1. Wunsch ist am wichtigsten und bekommt 6 Punkte.

    - Der 2. Wunsch bekommt 5 Punkte.

    - Der 3. Wunsch bekommt 4 Punkte, usw.

    - Der letzte Wunsch bekommt 1 Punkt.


- **Berechnung der Punkte pro Schüler**:

    - Nur erfüllte Wünsche zählen.

    - Die Gewichtung wird mit der Anzahl der erfüllten Wünsche multipliziert.

- **Maximale Punktzahl**:

    - Wenn alle Wünsche eines Schülers erfüllt wären, hätte er maximal 20 Punkte.

- **Gesamtformel**:

    - Die Punkte aller Schüler werden summiert.

    - Das Ergebnis wird durch (Anzahl der Schüler × 20) geteilt, um einen Wert zwischen 0 und 1 zu erhalten.

    - Multipliziert mit 100 ergibt sich der prozentuale Erfüllungsscore.
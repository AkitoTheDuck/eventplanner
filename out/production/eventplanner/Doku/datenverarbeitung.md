## 6. Datenverarbeitung

Die Schüler:innen geben ihre Wünsche in einer Excel-Tabelle an. Jede Zeile enthält die Schüler-ID und die Wunschunternehmen (bis zu 6 Wünsche pro Schüler).

- **Struktur der Excel-Datei**:
    - **Identifikation der Schüler**: Besteht aus Vor- und Nachname und dem Klassenkürzel
    - **Unternehmen-Nr. 1–6**: Wunschunternehmen des Schülers (Priorität von 1 bis 6).

Der **Zuweisungsalgorithmus** arbeitet wie folgt:

1. Der **erste Wunsch** wird immer erfüllt.
2. Der Algorithmus prüft die Verfügbarkeit der Unternehmen und versucht, mindestens 5 von 6 Wünschen zu realisieren.
3. Es wird eine Liste erstellt, in der mit der **size-Funktion** überprüft wird, wie viele Schüler:innen sich für eine Veranstaltung angemeldet haben.
4. **Gleichmäßige Verteilung**: Der Algorithmus sorgt dafür, dass die maximale Teilnehmerzahl pro Unternehmen nicht überschritten wird.

**Technologien**: Apache POI für die Excel-Verarbeitung, Swing für die Benutzeroberfläche.
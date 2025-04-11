## 4. Anforderungen

### 4.1 Technische Anforderungen

Um eine reibungslose Planung und Durchführung zu gewährleisten, müssen folgende technische Anforderungen erfüllt werden:

- Verwendung einer **Java-Swing-GUI** mit Drag-and-Drop-Funktionalität
- Verarbeitung von **drei hochgeladenen Dateien** (Exceldateien)
- Algorithmen zur **Wunscherfüllung**, basierend auf Schülerangaben in einer **Excel-Tabelle**
- Möglichkeit zum **Herunterladen verarbeiteter Dateien**
- Unterstützung verschiedener Download-Optionen (Einzel- und Gesamtdownload)
- **Java 8 oder höher** als Entwicklungsumgebung

### 4.2 Funktionale Anforderungen

Das Projekt soll die folgenden funktionalen Anforderungen erfüllen:

- **Organisation der Klassen und Teilnehmer:innen**:
    - Verwaltung der Zuweisung von Schüler:innen zu verschiedenen Unternehmen
    - Organisation der Vorträge und Workshops
- **Erfassung der Schülerwünsche**:
    - Die Schüler:innen geben ihre Wünsche in einer Excel-Tabelle an.
    - Der Algorithmus berücksichtigt die Priorisierung und versucht, mindestens 5 von 6 Wünschen pro Schüler zu erfüllen.
- **Verarbeitung von Dateien**:
    - Die hochgeladenen Excel-Dateien werden mit **Apache POI** oder **OpenCSV** verarbeitet.
    - Bereitstellung eines **Download-Buttons**, nachdem alle Dateien hochgeladen und verarbeitet wurden.
- **Gleichmäßige Verteilung der Schüler:innen**:
    - **ZÄHLENWENN-Funktion** in Excel wird zur Analyse der Teilnehmerzahlen je Veranstaltung verwendet.
    - Automatische Zuordnung basierend auf Verfügbarkeit und Schülerwünschen.
- **Maximale Teilnehmerzahl pro Betrieb**:
    - Es gibt eine festgelegte, aber anpassbare maximale Teilnehmerzahl für jede Veranstaltung.
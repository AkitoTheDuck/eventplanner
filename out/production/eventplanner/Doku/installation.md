## 3. Installation & Setup

### 3.1 Voraussetzungen

Für die Nutzung des Projekts müssen folgende Voraussetzungen erfüllt sein:

- **Java Development Kit (JDK)** Version 8 oder höher
- **IDE** (Integrated Development Environment) wie **IntelliJ IDEA**, **Eclipse** oder **NetBeans**
- **Bibliotheken** für die GUI-Entwicklung und Excel-Verarbeitung:
    - **Swing** (für GUI)
    - **Apache POI** oder **OpenCSV** (zur Verarbeitung von Excel-Dateien)
    - **JFreeChart** (optional für die grafische Darstellung der Daten)

### 3.2 Installationsanleitung

1. **Klone das Repository** oder lade die **JAR-Datei** herunter.
2. **Öffne das Projekt** in deiner bevorzugten Java-IDE (z. B. IntelliJ IDEA oder Eclipse).
3. **Abhängigkeiten installieren**:
    - Stelle sicher, dass alle benötigten Bibliotheken (wie Apache POI und Swing) in der `pom.xml` (bei Maven) oder im `build.gradle` (bei Gradle) definiert sind.
4. **Projekt kompilieren** und starten:
    - In IntelliJ IDEA: Gehe zu **Build** > **Build Project** und starte das Projekt mit **Run**.
    - In Eclipse: Klicke mit der rechten Maustaste auf das Projekt und wähle **Run As** > **Java Application**.

### 3.3 Testlauf der Veranstaltungssimulation

- Lade einige Beispiel-Excel-Dateien hoch, die die Schülerwünsche enthalten.
- Teste die **Drag-and-Drop-Funktionalität** und stelle sicher, dass alle Dateien korrekt verarbeitet und gespeichert werden.

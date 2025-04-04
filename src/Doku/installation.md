## 3. Installation & Setup

Für die Nutzung des Projekts für Lehrkräfte schau hier: 

### 3.1 Voraussetzungen

Für die Bearbeitung des Projekts müssen folgende Voraussetzungen erfüllt sein:

- **Java Development Kit (JDK)** Version 8 oder höher
- **IDE** (Integrated Development Environment) wie **IntelliJ IDEA** oder **Eclipse**

### 3.3 Techspec

- **Bibliotheken** für die GUI-Entwicklung und Excel-Verarbeitung:
    - **Swing** (für GUI)
    - **Apache POI** (zur Verarbeitung von Excel-Dateien)
  
### 3.2 Installationsanleitung

1. **Klone das Repository**
2. **Öffne das Projekt** in deiner bevorzugten Java-IDE (z. B. IntelliJ IDEA oder Eclipse).
3. **Projekt kompilieren** und starten:
    - In IntelliJ IDEA: Gehe zu **Build** > **Build Project** und starte das Projekt mit **Run**.
    - In Eclipse: Klicke mit der rechten Maustaste auf das Projekt und wähle **Run As** > **Java Application**.
4. **Jar erstellen**
   - ```jar cfe U:\Documents\Eventplanner.jar GUI.Main -C out/production/eventplanner .```
   - Mit Notepad++ Datei erstellen mit folgendem Inhalt:
   - ```
     @echo off
     java -jar Eventplanner.jar
     pause
     ```
     und als **start.bat** speichern
### 3.3 Testlauf der Veranstaltungssimulation

- Lade einige Beispiel-Excel-Dateien hoch, die die Schülerwünsche enthalten.
- Teste die **Drag-and-Drop-Funktionalität** und stelle sicher, dass alle Dateien korrekt verarbeitet und gespeichert werden.

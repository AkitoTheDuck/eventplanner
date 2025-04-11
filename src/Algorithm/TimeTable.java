package Algorithm;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapperSorter.CompanySorter;

import java.util.ArrayList;
import java.util.Objects;

public class TimeTable {

    private ArrayList<ClassRoom> classRooms;
    private ArrayList<Company> companies;

    public TimeTable(ArrayList<Company> companies, ArrayList<ClassRoom> classRooms) {
        this.companies = companies;
        this.classRooms = classRooms;
    }

    public void assign() {

        CompanySorter sorter = new CompanySorter();
        sorter.sortByStudentsCount(companies);

        // Für jedes Unternehmen den Raum zuweisen
        for (Company company : companies) {
            int timeSlotsNeeded = company.calcAmountEvents();
            int earliest = company.getEarliestStart();
            int studentsPerSlot = company.getSchuelerListe().size() / timeSlotsNeeded;

            if (studentsPerSlot > company.getMaxStudents()) {
                studentsPerSlot = company.getMaxStudents();
            }

            // Nur ein Raum für das Unternehmen zuweisen
            ClassRoom selectedRoom = null;
            int selectedStartSlot = -1;

            // Suche nach einem passenden Raum ab dem frühesten Slot
            outerLoop:
            for (int startSlot = earliest; startSlot <= 5; startSlot++) {
                // Überprüfe jeden Raum für den aktuellen Slot
                for (ClassRoom room : classRooms) {
                    int capacity = Integer.parseInt(room.getCapacity());

                    // Überprüfe, ob der Raum genügend Kapazität für alle Schüler pro Slot hat
                    if (capacity < studentsPerSlot) {
                        continue;
                    }

                    if(studentsPerSlot <= 10) {
                        if(capacity != studentsPerSlot) {
                            continue;
                        }
                    }

                    if (isEarliestSlotAvailable(room, timeSlotsNeeded, startSlot)) {
                        selectedRoom = room;
                        selectedStartSlot = startSlot;
                        break outerLoop; // Sobald ein Raum und Slot gefunden sind, bricht die Suche ab
                    }
                }

                startSlot++;
                for (ClassRoom room : classRooms) {
                    int capacity = Integer.parseInt(room.getCapacity());

                    // Überprüfe, ob der Raum genügend Kapazität für alle Schüler pro Slot hat
                    if (capacity < studentsPerSlot) {
                        continue;
                    }

                    if (isRoomAvailableForSlots(room, timeSlotsNeeded, startSlot)) {
                        selectedRoom = room;
                        selectedStartSlot = startSlot;
                        break outerLoop; // Sobald ein Raum und Slot gefunden sind, bricht die Suche ab
                    }
                }
            }

            // Wenn ein Raum gefunden wurde, weisen wir alle Slots zu
            if (selectedRoom != null && selectedStartSlot != -1) {
                assignSlotsToRoom(selectedRoom, timeSlotsNeeded, selectedStartSlot, company);
            } else {
                // Wenn kein Raum für das Unternehmen gefunden wurde, gebe eine Warnung oder führe alternative Logik aus
                System.out.println("Kein passender Raum für " + company.getName() + " gefunden.");
            }
        }

        sorter.sortByNr(companies);
    }

    private void assignSlotsToRoom(ClassRoom room, int timeSlotsNeeded, int earliest, Company company) {
        // Zuweisung der Slots ab dem gefundenen Start-Slot
        for (int startSlot = earliest; startSlot <= 5 - timeSlotsNeeded + 1; startSlot++) {
            boolean allFree = true;

            // Überprüfe, ob alle benötigten Slots im Raum ab dem Start-Slot frei sind
            for (int y = startSlot; y < startSlot + timeSlotsNeeded; y++) {
                if (isSlotOccupied(room, y)) {
                    allFree = false;
                    break;
                }
            }

            if (allFree) {
                // Wenn alle benötigten Slots frei sind, weise sie dem Raum zu
                for (int y = startSlot; y < startSlot + timeSlotsNeeded; y++) {
                    markSlotAsOccupied(room, y, company);
                }
                break; // Sobald die Slots zugewiesen sind, breche die Schleife ab
            }
        }
    }

    private void markSlotAsOccupied(ClassRoom room, int slot, Company company) {
        // Markiere den Slot als belegt für das angegebene Unternehmen
        switch (slot) {
            case 1:
                company.setSlotAClass(room);
                room.setSlotA(true);
                company.getRoomList().add(0, room);
                break;
            case 2:
                company.setSlotBClass(room);
                room.setSlotB(true);
                company.getRoomList().add(1, room);
                break;
            case 3:
                company.setSlotCClass(room);
                room.setSlotC(true);
                company.getRoomList().add(2, room);
                break;
            case 4:
                company.setSlotDClass(room);
                room.setSlotD(true);
                company.getRoomList().add(3, room);
                break;
            case 5:
                company.setSlotEClass(room);
                room.setSlotE(true);
                company.getRoomList().add(4, room);
                break;
        }
    }

    private boolean isSlotOccupied(ClassRoom classRoom, int slot) {
        // Überprüfe, ob der Slot im Raum besetzt ist
        return switch (slot) {
            case 1 -> classRoom.getSlotA();
            case 2 -> classRoom.getSlotB();
            case 3 -> classRoom.getSlotC();
            case 4 -> classRoom.getSlotD();
            case 5 -> classRoom.getSlotE();
            default -> throw new IllegalStateException("Unexpected value: " + slot);
        };
    }

    private boolean isRoomAvailableForSlots(ClassRoom room, int timeSlotsNeeded, int earliest) {
        // Überprüft, ob der Raum für die benötigten Zeit-Slots ab dem frühesten Slot verfügbar ist
        for (int startSlot = earliest; startSlot <= 5 - timeSlotsNeeded + 1; startSlot++) {
            boolean allFree = true;

            // Überprüfe, ob alle benötigten Slots im Raum ab dem Start-Slot frei sind
            for (int y = startSlot; y < startSlot + timeSlotsNeeded; y++) {
                if (isSlotOccupied(room, y)) {
                    allFree = false;
                    break;
                }
            }

            if (allFree) {
                return true; // Der Raum ist für die benötigten Slots verfügbar
            }
        }
        return false; // Der Raum ist nicht verfügbar
    }

    private boolean isEarliestSlotAvailable(ClassRoom room, int timeSlotsNeeded, int earliest) {
        // Überprüfe, ob alle benötigten Slots im Raum ab dem Start-Slot frei sind
        for (int slot = earliest; slot < earliest + timeSlotsNeeded; slot++) {
            if (isSlotOccupied(room, slot)) {
                return false; // Ein Slot ist besetzt, also nicht verfügbar
            }
        }
        return true; // Alle benötigten Slots sind frei
    }
}
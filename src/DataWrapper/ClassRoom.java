package DataWrapper;

/**
 * @author Christian
 */
public class ClassRoom extends DataWrapper{

    private String roomNumber;

    private String capacity;

    private Boolean slotA = false;
    private Boolean slotB = false;
    private Boolean slotC = false;
    private Boolean slotD = false;
    private Boolean slotE = false;

    public ClassRoom(String roomNumber, String capacity) {
        setRoomNumber(roomNumber);
        setCapacity(capacity);
    }

    public Boolean getSlotE() {
        return slotE;
    }

    public void setSlotE(Boolean slotE) {
        this.slotE = slotE;
    }

    public Boolean getSlotD() {
        return slotD;
    }

    public void setSlotD(Boolean slotD) {
        this.slotD = slotD;
    }

    public Boolean getSlotC() {
        return slotC;
    }

    public void setSlotC(Boolean slotC) {
        this.slotC = slotC;
    }

    public Boolean getSlotB() {
        return slotB;
    }

    public void setSlotB(Boolean slotB) {
        this.slotB = slotB;
    }

    public Boolean getSlotA() {
        return slotA;
    }

    public void setSlotA(Boolean slotA) {
        this.slotA = slotA;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}

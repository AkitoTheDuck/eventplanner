package DataWrapper;

public class ClassRoom extends DataWrapper{

    private String roomNumber;

    private String capacity;

    public ClassRoom(String roomNumber, String capacity) {
        setRoomNumber(roomNumber);
        setCapacity(capacity);
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

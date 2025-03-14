import DataWrapper.ClassRoom;
import DataWrapper.Company;

public class Event{

    private final String roomNumber;
    private final String companyName;
    private final String timing;
    private final int capacity = 0;
    private final int totalCapacity;
    private int nummer;

    enum eventTime{
        A, B, C, D, E
    }

    public Event(ClassRoom room, Company company){
        this.roomNumber = room.getRoomNumber();
        this.companyName = company.getName();
        this.timing = company.getEarliestStart();
        this.totalCapacity = Integer.parseInt(room.getCapacity());
        this.nummer = Integer.parseInt(company.getNr());
    }
    public String printInfo(){
        return "RNr.: " + roomNumber + "\n" +
                "CompanyName.: " + companyName + "\n" +
                "timing.: " + timing + "\n" +
                "total cap.: " + totalCapacity + "\n" +
                "nummer company.: " + nummer + "\n";
    }
}

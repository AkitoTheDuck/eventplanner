package DataWrapper;

public class Student {

    private String klasse;

    private String lastName;

    private String firstName;

    private int choice1;

    private int choice2;

    private int choice3;

    private int choice4;

    private int choice5;

    private int choice6;

    public Student(String klasse, String lastName, String firstName, int choice1, int choice2, int choice3, int choice4, int choice5, int choice6) {
        this.klasse = klasse;
        this.lastName = lastName;
        this.firstName = firstName;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
        this.choice6 = choice6;
    }

    public String getKlasse() {
        return klasse;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getChoice1() {
        return choice1;
    }

    public int getChoice2() {
        return choice2;
    }

    public int getChoice3() {
        return choice3;
    }

    public int getChoice4() {
        return choice4;
    }

    public int getChoice5() {
        return choice5;
    }

    public int getChoice6() {
        return choice6;
    }
}

public class Guest implements Comparable<Guest> {
    private String name;
    private String surname;
    private Group group;
    private boolean plusOne;

    public Guest(String name, String surname, Group group) {
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int compareTo(Guest guest) {
        int val = this.surname.compareTo(guest.surname);
        if(val == 0) return this.name.compareTo(guest.name);
        else return val;
    }
}

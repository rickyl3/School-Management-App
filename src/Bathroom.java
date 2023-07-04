public class Bathroom extends Room
{
    private String gender;
    public Bathroom(String g, boolean a) {
        super(a);
        gender = g;
    }
    public String getGender() {
        return gender;
    }
    public String toString() {
        return gender + " bathroom";
    }
    public boolean equals(Room room) {
        return ((Bathroom) room).getGender().equals(gender);
    }
}

public class Student
{
    private int OSIS;
    private String name;
    private String gender;
    private int gradeLevel;
    private double average;
    public Student(int OSIS, String name, String gender, int gradeLevel, double average) {
        this.OSIS = OSIS;
        this.name = name;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.average = average;
    }
    public void setOSIS(int os) {
        OSIS = os;
    }
    public void setName(String na) {
        name = na;
    }
    public void setGender(String g) {
        gender = g;
    }
    public void setGradeLevel(int g) {
        gradeLevel = g;
    }
    public void setAverage(double a) {
        average = a;
    } 
    public int getOSIS() {
        return OSIS;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public int getGrade() {
        return gradeLevel;
    }
    public double getAverage() {
        return average;
    }
}

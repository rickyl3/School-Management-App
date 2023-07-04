import java.util.*;

public class Classroom extends Room
{
    private ArrayList<Student> students;
    public Classroom(ArrayList<Student> s, boolean a) {
        super(a);
        students = s;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void addStudent(Student a) {
        students.add(a);
    }
    public void removeStudent(String name, String gender, int gradeLevel) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name) && students.get(i).getGender().equals(gender) && students.get(i).getGrade() == gradeLevel) {
                students.remove(i);
            }
        }
    }
    public String toString() {
        return students.toString();
    }
    public boolean equals(Classroom room) {
        for (Student st: students) {
            for (Student s: room.getStudents()) {
                if (st != s) {
                    return false;
                }
            }
        }
        return true;
    }
}

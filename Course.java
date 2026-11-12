import java.util.Arrays;

public class Course {
    private String name;
    private Student[] students = new Student[5];
    private int studentCount = 0;
    private Session[] sessions = new Session[50];
    private int sessionCount = 0;

    public Course(String name) { this.name = name; }
    public String getName() { return name; }
    public int getStudentCount() { return studentCount; }
    public int getSessionCount() { return sessionCount; }
    public Student getStudent(int i) { return students[i]; }
    public Session getSession(int i) { return sessions[i]; }

    public int findStudent(String regNo) {
        if (studentCount == 0) return -1;
        int i = 0;
        do {
            if (students[i].getRegNo().equals(regNo)) return i;
            i++;
        } while (i < studentCount);
        return -1;
    }
    public boolean addStudent(Student s) {
        if (findStudent(s.getRegNo()) != -1) return false;           // duplicate Reg No
        if (studentCount == students.length) students = Arrays.copyOf(students, students.length * 2);
        students[studentCount] = s; studentCount++;
        return true;
    }
    public boolean recordSession(boolean[] present, String date) {
        if (sessionCount == sessions.length) return false;
        int p = 0;
        for (int i = 0; i < studentCount; i++) {
            if (present[i]) { students[i].markPresent(); p++; }
            else students[i].markAbsent();
        }
        sessions[sessionCount] = new Session(date, p, studentCount); sessionCount++;
        return true;
    }
}

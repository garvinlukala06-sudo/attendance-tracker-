public class Student {
    private String name, regNo;
    private int attended = 0, total = 0;
    public Student(String name, String regNo) { this.name = name; this.regNo = regNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRegNo() { return regNo; }
    public int getAttended() { return attended; }
    public int getTotal() { return total; }
    public void markPresent() { attended++; total++; }
    public void markAbsent() { total++; }
    public int getPercent() { return total == 0 ? 0 : attended * 100 / total; }
    public String getStanding() {
        if (total == 0) return "No sessions yet";
        switch (getPercent() / 10) {
            case 10: case 9: case 8: return "Excellent";
            case 7: case 6: return "Good";
            case 5: return "Warning";
            default: return "At risk";
        }
    }
}

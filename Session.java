public class Session {
    private String date;
    private int presentCount, total;
    public Session(String date, int presentCount, int total) { this.date = date; this.presentCount = presentCount; this.total = total; }
    public String getDate() { return date; }
    public int getPresentCount() { return presentCount; }
    public int getTotal() { return total; }
}

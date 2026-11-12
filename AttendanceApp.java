/* CSC 1102 project - Group:11 Members:Lukala Garvin, Sumbatala Dorah, Nabacwa Charlotte, Mulema Joakim,Mayengo John Mary GitHub: garvinlukala06-sudo*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class AttendanceApp extends JFrame {
    private Course course = new Course("CSC 1102");
    private JTextField nameF = new JTextField(10), regF = new JTextField(8);
    private JLabel info = new JLabel();
    private DefaultTableModel model = new DefaultTableModel(
            new String[]{"Name", "Reg No", "Present today?", "Attended", "%", "Standing"}, 0) {
        public Class<?> getColumnClass(int c) { return c == 2 ? Boolean.class : Object.class; }
        public boolean isCellEditable(int r, int c) { return c == 2; }
    };
    private JTable table = new JTable(model);

    public AttendanceApp() {
        super("Attendance Tracker");
        JPanel top = new JPanel();
        JButton add = new JButton("Register student");
        top.add(new JLabel("Name:")); top.add(nameF); top.add(new JLabel("Reg No:")); top.add(regF); top.add(add);
        JPanel bottom = new JPanel();
        JButton rec = new JButton("Record today's session");
        bottom.add(rec); bottom.add(info);
        add.addActionListener(e -> {
            if (nameF.getText().trim().isEmpty() || regF.getText().trim().isEmpty()) { msg("Enter a name and a Reg No."); return; }
            if (!course.addStudent(new Student(nameF.getText().trim(), regF.getText().trim()))) msg("That Reg No is already registered.");
            else { nameF.setText(""); regF.setText(""); }
            refresh();
        });
        rec.addActionListener(e -> {
            if (table.isEditing()) table.getCellEditor().stopCellEditing();
            int n = course.getStudentCount();
            if (n == 0) { msg("Register students first."); return; }
            boolean[] present = new boolean[n];
            for (int i = 0; i < n; i++) present[i] = (Boolean) model.getValueAt(i, 2);
            if (!course.recordSession(present, LocalDate.now().toString())) msg("Session limit reached.");
            refresh();
        });
        add(top, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER); add(bottom, BorderLayout.SOUTH);
        setSize(700, 360); setDefaultCloseOperation(EXIT_ON_CLOSE); setLocationRelativeTo(null);
        refresh();
    }
    private void msg(String m) { JOptionPane.showMessageDialog(this, m); }
    private void refresh() {
        model.setRowCount(0);
        for (int i = 0; i < course.getStudentCount(); i++) {
            Student s = course.getStudent(i);
            model.addRow(new Object[]{s.getName(), s.getRegNo(), false, s.getAttended() + "/" + s.getTotal(), s.getPercent() + "%", s.getStanding()});
        }
        info.setText("Sessions recorded: " + course.getSessionCount());
    }
    public static void main(String[] a) { SwingUtilities.invokeLater(() -> new AttendanceApp().setVisible(true)); }
}

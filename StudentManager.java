import java.util.*;

public class StudentManager {
    ArrayList<Student> list = new ArrayList<>();

    // ---------- Part C ----------
    void add(Student s) {                              // 1 + 10 + rules
        if (find(s.id) != null) { System.out.println("Duplicate ID " + s.id); return; }
        if (s.marks.length != 5) { System.out.println("Need 5 marks"); return; }
        for (int m : s.marks)
            if (m < 0 || m > 100) { System.out.println("Marks must be 0-100"); return; }
        list.add(s);
    }

    void showAll() { for (Student s : list) System.out.println(s); }   // 2

    Student find(int id) {                             // 3
        for (Student s : list) if (s.id == id) return s;
        return null;
    }

    ArrayList<Student> byDept(String d) {              // 4
        ArrayList<Student> r = new ArrayList<>();
        for (Student s : list) if (s.dept.equals(d)) r.add(s);
        return r;
    }

    void changeDept(int id, String d) {                // 5
        Student s = find(id);
        if (s != null) s.dept = d;
    }

    void remove(int id) {                              // 6
        Student s = find(id);
        if (s != null) list.remove(s);
    }

    ArrayList<Student> sorted() {                      // copy sorted by average, highest first
        ArrayList<Student> c = new ArrayList<>(list);
        for (int i = 0; i < c.size(); i++)
            for (int j = 0; j < c.size() - 1; j++)
                if (c.get(j).avg() < c.get(j + 1).avg()) {
                    Student t = c.get(j); c.set(j, c.get(j + 1)); c.set(j + 1, t);
                }
        return c;
    }

    void topFive() {                                   // 7
        ArrayList<Student> c = sorted();
        for (int i = 0; i < 5 && i < c.size(); i++) System.out.println(c.get(i));
    }

    void below50() {                                   // 8
        for (Student s : list) if (s.avg() < 50) System.out.println(s);
    }

    void countDept() {                                 // 9
        ArrayList<String> seen = new ArrayList<>();
        for (Student s : list) {
            if (seen.contains(s.dept)) continue;
            int n = 0;
            for (Student o : list) if (o.dept.equals(s.dept)) n++;
            System.out.println(s.dept + ": " + n);
            seen.add(s.dept);
        }
    }

    // 11: TIE RULE: sorted by average; equal averages keep the order they were added.
    Student third() {
        ArrayList<Student> c = sorted();
        return c.size() >= 3 ? c.get(2) : null;
    }

    ArrayList<Student> passedAllList() {               // 12
        ArrayList<Student> r = new ArrayList<>();
        for (Student s : list) if (s.passedAll()) r.add(s);
        return r;
    }

    // ---------- Part B ----------
    void highLow() {                                   // B1 + B2
        for (Student s : list) {
            int hi = s.marks[0], lo = s.marks[0];
            for (int m : s.marks) {
                if (m > hi) hi = m;
                if (m < lo) lo = m;
            }
            System.out.println(s.name + " avg=" + s.avg() + " high=" + hi + " low=" + lo);
        }
    }

    Student best() {                                   // B3
        Student b = list.get(0);
        for (Student s : list) if (s.avg() > b.avg()) b = s;
        return b;
    }

    int countPassedAll() {                             // B4
        int n = 0;
        for (Student s : list) if (s.passedAll()) n++;
        return n;
    }

    void sortMarks(int[] a) {                          // B5 bubble sort
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length - 1; j++)
                if (a[j] > a[j + 1]) { int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t; }
    }

    double classAvg() {                                // B7
        int sum = 0, n = 0;
        for (Student s : list)
            for (int m : s.marks) { sum += m; n++; }
        return (double) sum / n;
    }

    int[] aboveAvg() {                                 // B6
        double avg = classAvg();
        int n = 0;
        for (Student s : list) for (int m : s.marks) if (m > avg) n++;
        int[] r = new int[n];
        int k = 0;
        for (Student s : list) for (int m : s.marks) if (m > avg) r[k++] = m;
        return r;
    }
}
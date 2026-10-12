public class Student {
    int id;
    String name, dept;
    int[] marks;

    Student(int id, String name, String dept, int[] marks) {
        this.id = id; this.name = name; this.dept = dept; this.marks = marks;
    }

    double avg() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum / 5.0;
    }

    boolean passedAll() {
        for (int m : marks) if (m < 50) return false;
        return true;
    }

    public String toString(){ return id + " " + name + " " + dept + " avg=" + avg(); }
}
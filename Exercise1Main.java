import java.util.*;

public class Exercise1Main {
    public static void main(String[] args) {
        StudentManager m = new StudentManager();

        m.add(new Student(1, "Aline", "IS", new int[]{78, 85, 90, 66, 72}));
        m.add(new Student(2, "Benigne", "IS", new int[]{88, 92, 95, 81, 79}));
        m.add(new Student(3, "Claude", "Networking", new int[]{45, 52, 38, 60, 49}));
        m.add(new Student(4, "Divine", "Business", new int[]{70, 65, 74, 80, 68}));
        m.add(new Student(5, "Eric", "Networking", new int[]{55, 61, 58, 63, 50}));
        m.add(new Student(6, "Fabiola", "Business", new int[]{92, 89, 94, 91, 97}));
        m.add(new Student(7, "Grace", "IS", new int[]{40, 35, 48, 52, 44}));
        m.add(new Student(8, "Habimana", "Security", new int[]{67, 71, 69, 73, 75}));
        m.add(new Student(9, "Immaculee", "Security", new int[]{82, 79, 85, 88, 90}));
        m.add(new Student(10, "Jean", "Networking", new int[]{30, 42, 51, 47, 39}));
        m.add(new Student(11, "Kevine", "Business", new int[]{60, 58, 62, 64, 59}));
        m.add(new Student(12, "Lionel", "IS", new int[]{75, 80, 77, 79, 81}));
        m.add(new Student(13, "Marie", "Security", new int[]{50, 50, 50, 50, 50}));
        m.add(new Student(14, "Nadine", "Business", new int[]{85, 87, 83, 90, 86}));
        m.add(new Student(15, "Olivier", "Networking", new int[]{66, 54, 72, 61, 58}));
        m.add(new Student(1, "Copy", "IS", new int[]{50, 50, 50, 50, 50}));   // duplicate

        m.showAll();
        m.highLow();
        System.out.println("Best: " + m.best().name);
        System.out.println("Passed all: " + m.countPassedAll());
        System.out.println("Class avg: " + m.classAvg());
        System.out.println("Above avg: " + Arrays.toString(m.aboveAvg()));

        int[] marks = m.find(2).marks.clone();
        m.sortMarks(marks);
        System.out.println("Sorted marks: " + Arrays.toString(marks));

        System.out.println("Student 6: " + m.find(6));
        System.out.println("IS: " + m.byDept("IS"));
        m.changeDept(3, "Security");
        m.topFive();
        m.below50();
        m.countDept();
        System.out.println("Third: " + m.third());
        System.out.println("Passed list size: " + m.passedAllList().size());
        m.remove(10);
    }
}
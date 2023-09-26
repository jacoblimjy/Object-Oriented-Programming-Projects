import java.util.Scanner;

class Main {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        int intValue =  sc.nextInt();
        sc.nextLine();
        Roster roster = new Roster("AY1920");

        for (int i = 0; i < intValue; i++) {
            String studentKey = sc.next(); 
            String moduleKey = sc.next();
            String assignmentKey = sc.next();
            String grade = sc.next();
            roster = roster.add(studentKey, moduleKey, assignmentKey, grade);
        }

        while (sc.hasNext()) {
            String s = sc.next(); 
            String m = sc.next();
            String a = sc.next();
            String grade = roster.getGrade(s, m, a); 
            System.out.println(grade);
        }
    }
}



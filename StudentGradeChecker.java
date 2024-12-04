import java.util.Scanner;

public class StudentGradeChecker {
    static final int SUBJECTS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            String name = inputStudentInfo(scanner);
            float[] scores = inputScores(scanner);
            float average = calculateAverage(scores);
            char grade = assignGrade(average);

            displayResults(name, average, grade);

            System.out.print("\nWould you like to enter another student? (y/n): ");
            choice = scanner.nextLine();

        } while (choice.equalsIgnoreCase("y"));

        System.out.println("Thank you for using the Student Grade Checker!");
        scanner.close();
    }

    private static String inputStudentInfo(Scanner scanner) {
        System.out.print("Enter student name: ");
        return scanner.nextLine();
    }

    private static float[] inputScores(Scanner scanner) {
        float[] scores = new float[SUBJECTS];

        for (int i = 0; i < SUBJECTS; i++) {
            while (true) {
                System.out.printf("Enter score for subject %d: ", i + 1);
                float score = scanner.nextFloat();
                if (score >= 0 && score <= 100) {
                    scores[i] = score;
                    break;
                } else {
                    System.out.println("Invalid score! Please enter a score between 0 and 100.");
                }
            }
        }
        scanner.nextLine(); // Consume newline character
        return scores;
    }

    private static float calculateAverage(float[] scores) {
        float sum = 0;
        for (float score : scores) {
            sum += score;
        }
        return sum / SUBJECTS;
    }

    private static char assignGrade(float average) {
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private static void displayResults(String name, float average, char grade) {
        System.out.println("\nStudent Name: " + name);
        System.out.printf("Average Score: %.2f\n", average);
        System.out.println("Grade: " + grade);
    }
}

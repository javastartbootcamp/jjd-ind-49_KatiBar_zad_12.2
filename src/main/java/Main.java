import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("employees.csv");

        if (file.exists()) {
            File stats = new File("stats.txt");
            stats.createNewFile();
            try (
                    var fileWriter = new FileWriter(stats);
                    var bufferedWriter = new BufferedWriter(fileWriter);
            ) {
                Employee[] employees = Company.readFile(file);
                double averageSalary = Company.getAverageSalary(employees);
                double minSalary = Company.getMinSalary(employees);
                double maxSalary = Company.getMaxSalary(employees);
                int[] employeesInDepartment = Company.getEmployeesInDepartment(employees);
                bufferedWriter.write("Średnia wypłata: " + averageSalary);
                bufferedWriter.newLine();
                bufferedWriter.write("Minimalna wypłata: " + minSalary);
                bufferedWriter.newLine();
                bufferedWriter.write("Maksymalna wypłata: " + maxSalary);
                bufferedWriter.newLine();
                bufferedWriter.write("Liczba pracowników IT: " + employeesInDepartment[0]);
                bufferedWriter.newLine();
                bufferedWriter.write("Liczba pracowników Support: " + employeesInDepartment[1]);
                bufferedWriter.newLine();
                bufferedWriter.write("Liczba pracowników Management: " + employeesInDepartment[2]);
            } catch (NumberFormatException e) {
                System.err.println("Wypłata musi być liczbą");
            }
        }
    }
}

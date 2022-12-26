import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Company {
    private Employee employee;
    private static final String IT_DEPARTMENT = "IT";
    private static final String SUPPORT_DEPARTMENT = "Support";
    private static final String MANAGEMENT_DEPARTMENT = "Management";

    static Employee[] readFile(File file) throws FileNotFoundException, NumberFormatException {
        Scanner scanner = new Scanner(file);
        int lines = countLines(file);
        Employee[] employees = new Employee[lines];
        for (int i = 0; i < lines; i++) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            String firstName = split[0];
            String lastName = split[1];
            String pesel = split[2];
            String department = split[3];
            double salary = Double.parseDouble(split[4]);
            employees[i] = new Employee(firstName, lastName, pesel, department, salary);
        }
        return employees;
    }

    private static int countLines(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int lines = 0;
        while (scanner.hasNextLine()) {
            lines++;
            scanner.nextLine();
        }
        return lines;
    }

    static double getAverageSalary(Employee[] employees) {
        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum / employees.length;
    }

    static double getMinSalary(Employee[] employees) {
        double minSalary = employees[0].getSalary();
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i].getSalary() > employees[i + 1].getSalary()) {
                minSalary = employees[i + 1].getSalary();
            }
        }
        return minSalary;
    }

    static double getMaxSalary(Employee[] employees) {
        double maxSalary = employees[0].getSalary();
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i].getSalary() < employees[i + 1].getSalary()) {
                maxSalary = employees[i + 1].getSalary();
            }
        }
        return maxSalary;
    }

    static int[] getEmployeesInDepartment(Employee[] employees) {
        int[] numbersOfEmployees = new int[3];
        int numbersOfEmployeeInIT = 0;
        int numbersOfEmployeeInSupport = 0;
        int numbersOfEmployeeInManagement = 0;
        for (Employee employee : employees) {
            String department = employee.getDepartment();
            switch (department) {
                case IT_DEPARTMENT:
                    numbersOfEmployeeInIT++;
                    break;
                case SUPPORT_DEPARTMENT:
                    numbersOfEmployeeInSupport++;
                    break;
                case MANAGEMENT_DEPARTMENT:
                    numbersOfEmployeeInManagement++;
                    break;
                default:
                    System.err.println("Podany dział: " + department + " nie istnieje");
            }
            numbersOfEmployees[0] = numbersOfEmployeeInIT;
            numbersOfEmployees[1] = numbersOfEmployeeInSupport;
            numbersOfEmployees[2] = numbersOfEmployeeInManagement;
        }
        return numbersOfEmployees;
    }
}

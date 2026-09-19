package Assignment1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager(100);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT MODULE ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Load Employees from File");
            System.out.println("4. Save to File and Exit Program");
            System.out.println("5. Exit Without Saving");
            System.out.print("Enter choice (1-5): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    handleAdd();
                    break;
                case "2":
                    handleDisplay();
                    break;
                case "3":
                    handleLoad();
                    break;
                case "4":
                    handleSaveAndExit();
                    running = false;
                    break;
                case "5":
                    System.out.println("Exiting application without saving.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1-5.");
            }
        }
    }

    private static void handleAdd() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Department: ");
            String dept = scanner.nextLine().trim();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            boolean added = manager.addEmployee(new Employee(id, name, dept, salary));
            if (added) {
                System.out.println("Employee recorded successfully.");
            } else {
                System.out.println("Capacity reached. Cannot add more records.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Numeric values required for ID and Salary.");
        }
    }

    private static void handleDisplay() {
        Employee[] list = manager.getAllEmployees();
        if (list.length == 0) {
            System.out.println("No records found in memory.");
            return;
        }

        System.out.println("\n--- Current Employees (" + list.length + ") ---");
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    private static void handleLoad() {
        System.out.print("Enter file path to load: ");
        String fileName = scanner.nextLine().trim();

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Error: File does not exist.");
            return;
        }

        try {
            manager.loadFromFile(fileName);
            System.out.println("Loaded " + manager.getCount() + " employee(s) into memory.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load failed: " + e.getMessage());
        }
    }

    private static void handleSaveAndExit() {
        System.out.print("Enter file name to save (e.g., employees.dat): ");
        String fileName = scanner.nextLine().trim();

        try {
            manager.saveToFile(fileName);
            System.out.println("Saved " + manager.getCount() + " record(s) to '" + fileName + "'.");
            System.out.println("Terminating program...");
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
            System.out.println("Terminating program anyway.");
        }
    }
}
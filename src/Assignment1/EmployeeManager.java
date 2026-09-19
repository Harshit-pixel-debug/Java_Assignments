package Assignment1;

import java.io.*;
import java.util.Arrays;

public class EmployeeManager {
    private Employee[] employees;
    private int count;

    public EmployeeManager(int capacity) {
        this.employees = new Employee[capacity];
        this.count = 0;
    }

    public boolean addEmployee(Employee emp) {
        if (count >= employees.length) {
            return false;
        }
        employees[count++] = emp;
        return true;
    }

    public Employee[] getAllEmployees() {
        return Arrays.copyOf(employees, count);
    }

    public int getCount() {
        return count;
    }

    // Writes active array elements to the target file
    public void saveToFile(String fileName) throws IOException {
        Employee[] dataToSave = Arrays.copyOf(employees, count);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(dataToSave);
        }
    }

    // Reads records from file back into the internal array
    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Employee[] loadedData = (Employee[]) ois.readObject();
            
            int copyLength = Math.min(loadedData.length, employees.length);
            System.arraycopy(loadedData, 0, this.employees, 0, copyLength);
            this.count = copyLength;
        }
    }
}
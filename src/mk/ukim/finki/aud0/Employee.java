package mk.ukim.finki.aud0;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private String firstName;
    private String lastName;
    private double salary;
    private static double MINIMAL_SALARY_IN_MACEDONIA = 18000.0;

    public Employee(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = MINIMAL_SALARY_IN_MACEDONIA;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static double getMinimalSalaryInMacedonia() {
        return MINIMAL_SALARY_IN_MACEDONIA;
    }

    public static void setMinimalSalaryInMacedonia(double minimalSalaryInMacedonia) {
        MINIMAL_SALARY_IN_MACEDONIA = minimalSalaryInMacedonia;
    }

    @Override
    public String toString() { //operator << vo C++ cout<<employee; vo OOP predmetot
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Stefan", "Andonov");
        System.out.println(e1);


        Employee e2 = e1;

        e2.setSalary(25000);
                //new Employee("Stefan", "Andonov", 18000.0);
        System.out.println(e1.equals(e2));

        System.out.println(e1 + "\t" + e2);

    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.salary, o.salary);
    }
}

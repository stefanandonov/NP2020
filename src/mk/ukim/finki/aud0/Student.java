package mk.ukim.finki.aud0;

import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private String surname;
    private String index;
    private String faculty;
    private int age;

    //shortcut for generation of constructors, getters, setters... is Alt+Insert

    public Student() {
    }

    public Student(String name, String surname, String index, int age) {
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.faculty = "FINKI";
        this.age = age;
    }

    public Student(String name, String surname, String index, String faculty, int age) {
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.faculty = faculty;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //this method will be called when printing an Object od the Class Student
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", index='" + index + '\'' +
                ", faculty='" + faculty + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(index, student.index) &&
                Objects.equals(faculty, student.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, index, faculty, age);
    }

    //students will be compared by age
    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.age, o.age);
    }

    public static void main(String[] args) {
        Student student1 = new Student("Ana", "Todorovska", "161501",22);
        Student student2 = new Student("Ana", "Todorovska", "161501",22);

        //objects should be compared using the equals method, and not ==
        System.out.println(student1.equals(student2));
    }
}

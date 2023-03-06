package wissen.studentdomain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    int studentId;
    @Column(name = "name")
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "standard")
    String standard;
    @Column(name = "section")
    String section;

    public Student() {}

    // Getter/Setter for studentId
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Getter/Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter/Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter/Setter for standard
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    // Getter/Setter for section
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

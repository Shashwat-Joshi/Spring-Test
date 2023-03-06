package wissen.studentdomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wissen.studentdomain.models.Student;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "SELECT * FROM student WHERE standart = ?1", nativeQuery = true)
    public List<Student> getAllStudentsFromStandard(String standard);

    @Query(value = "SELECT * FROM student WHERE standard = ?1 AND section = ?2", nativeQuery = true)
    public List<Student> getAllStudentsFromStandardAndSection(String standard, String section);
}


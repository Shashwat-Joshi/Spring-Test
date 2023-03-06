package wissen.studentdomain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wissen.studentdomain.models.Student;
import wissen.studentdomain.repository.IStudentRepository;

import java.util.List;
import java.util.Optional;

/*
    StudentController - A REST controller for the Student Table.
    Endpoint: {_BASEURL}/student
    Contains all the basic CRUD functionalities
*/
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    IStudentRepository studentRepository;

    /*
        ENDPOINT: {_BASEURL}/student/getAll
        REQUEST METHOD: GET
        getAllStudent() is a function which will return a List of all students present
        in the database
    */
    @GetMapping("/getAll")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    /*
        ENDPOINT: {_BASEURL}/student/get?standard={_}&section={_}
        REQUEST METHOD: GET
        getAllStudentsByStandard() is a function which takes [standard] and [section] as @RequestParam
        and returns a List of Student type which are from same standard and section
    */
    @GetMapping(value = "/getAll", params = {"standard", "section"})
    public List<Student> getAllStudentsByStandard(@RequestParam String standard, @RequestParam String section) {
        return studentRepository.getAllStudentsFromStandardAndSection(standard, section);
    }

    /*
        ENDPOINT: {_BASEURL}/student/get/{id}
        REQUEST METHOD: GET
        getStudentById() is a function which takes [studentId] as a @PathVariable
        and will return a student object if it is present in the database.
        If the object with student_id = {id} is not found it will return [null]
    */
    @GetMapping("/get/{id}")
    public Optional<Student> getStudentById(@PathVariable int id) {
        return studentRepository.findById(id);
    }

    /*
        ENDPOINT: {_BASEURL}/student/create
        REQUEST METHOD: POST
        createStudent() is a function which will take a list of type Student param and
        will return the same list assuring that the given students have been saved
        to the database
    */
    @PostMapping("/create")
    public List<Student> createStudent(@RequestBody List<Student> StudentList) {
        return studentRepository.saveAll(StudentList);
    }

    /*
        ENDPOINT: {_BASEURL}/student/update
        REQUEST METHOD: PUT
        updateStudent() is a function which will take Student object and update, if present in
        the database. After a successful update it will return the Student Object.
        If the studentId is not found it will return [null];
    */
    @PutMapping("/update")
    public Object updateStudent(@RequestBody Student newStudent) {
        int studentId = newStudent.getStudentId();
        Optional<Student> dbStudent = studentRepository.findById(studentId);

        if (dbStudent.isPresent()) {
            Student Student = dbStudent.get();
            Student.setName(newStudent.getName());
            Student.setAge(newStudent.getAge());
            Student.setStandard(newStudent.getStandard());
            Student.setSection(newStudent.getSection());
            return studentRepository.save(Student);
        }

        return null;
    }

    /*
        ENDPOINT: {_BASEURL}/student/remove/{id}
        REQUEST METHOD: DELETE
        deleteStudent() is a function which will take the [studentId] as a @PathVariable and then delete the
        record if it is there in the database.
        After a successful delete operation it will return the deleted record.
        If the record is not in the database it will return [null]
    */
    @DeleteMapping("/remove/{id}")
    public Object deleteStudent(@PathVariable int id) {
        Optional<Student> fetchedStudent = studentRepository.findById(id);

        if (fetchedStudent.isPresent()) {
            studentRepository.deleteById(id);
            return fetchedStudent;
        }
        return null;
    }
}
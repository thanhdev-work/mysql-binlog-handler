package vn.thanhdev;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/student/trans")
    public ResponseEntity<?> addStudentTransactional(@RequestBody StudentRequest studentRequest) {
        var student = saveStudentTransactional(studentRequest);
        deleteStudentTransactional(student.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(saveStudent(studentRequest));
    }


    @Transactional
    Student saveStudentTransactional(StudentRequest studentRequest) {
        Student student = new Student();
        student.setId(studentRequest.getId());
        student.setName(studentRequest.getName());
        student.setAge(student.getAge());

        return studentRepository.save(student);
    }

    @Transactional
    void deleteStudentTransactional(int id) {
        Student student = new Student();
        student = studentRepository.findById(id).get();
        studentRepository.delete(student);
    }

    public Student saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setId(studentRequest.getId());
        student.setName(studentRequest.getName());
        student.setAge(student.getAge());

        return studentRepository.save(student);
    }
}

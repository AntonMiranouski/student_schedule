package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.Student;
import anton.miranouski.student_schedule.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final String ERROR_MESSAGE = "No Student with this id was found";

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Student student) {
        student.setId(null);
        studentRepository.saveAndFlush(student);
    }

    public void update(Student student) {
        studentRepository.saveAndFlush(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}

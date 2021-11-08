package anton.miranouski.student_schedule.controller;

import anton.miranouski.student_schedule.dto.request.StudentRequest;
import anton.miranouski.student_schedule.dto.response.StudentResponse;
import anton.miranouski.student_schedule.model.Student;
import anton.miranouski.student_schedule.service.StudentService;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final DozerBeanMapper mapper;

    public StudentController(StudentService studentService, DozerBeanMapper mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StudentResponse> findAll() {
        final List<Student> students = studentService.findAll();
        return students.stream()
                .map(student -> mapper.map(student, StudentResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        final Student student = studentService.findById(id);
        return mapper.map(student, StudentResponse.class);
    }

    @PostMapping
    public StudentResponse save(@Valid @RequestBody StudentRequest studentRequest) {
        final Student student = mapper.map(studentRequest, Student.class);
        studentService.save(student);
        return mapper.map(student, StudentResponse.class);
    }

    @PutMapping
    public StudentResponse update(@Valid @RequestBody StudentRequest studentRequest) {
        final Student student = mapper.map(studentRequest, Student.class);
        studentService.update(student);
        return mapper.map(student, StudentResponse.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}

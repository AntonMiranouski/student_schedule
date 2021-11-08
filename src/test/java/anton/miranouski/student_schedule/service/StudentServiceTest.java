package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void findAll() {
        assertFalse(studentService.findAll().isEmpty());
    }

    @Test
    void findById() {
        assertEquals(studentService.findById(1L).getId(), 1L);
    }

    @Test
    void save() {
        int size = studentService.findAll().size();
        studentService.save(new Student());

        assertEquals(studentService.findAll().size(), size + 1);
    }

    @Test
    void update() {
        Student student = studentService.findById(1L);
        student.setName("Test name");
        studentService.update(student);

        assertEquals(studentService.findById(1L).getName(), "Test name");
    }

    @Test
    void deleteById() {
        int size = studentService.findAll().size();
        studentService.deleteById(1L);

        assertEquals(studentService.findAll().size(), size - 1);
    }
}
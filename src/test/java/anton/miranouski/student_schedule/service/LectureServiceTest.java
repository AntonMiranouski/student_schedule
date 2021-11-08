package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LectureServiceTest {

    @Autowired
    private LectureService lectureService;

    @Test
    void findAll() {
        assertFalse(lectureService.findAll().isEmpty());
    }

    @Test
    void findById() {
        assertEquals(lectureService.findById(1L).getId(), 1L);
    }

    @Test
    void save() {
        int size = lectureService.findAll().size();
        lectureService.save(new Lecture());

        assertEquals(lectureService.findAll().size(), size + 1);
    }

    @Test
    void update() {
        Lecture lecture = lectureService.findById(1L);
        lecture.setName("Test name");
        lectureService.update(lecture);

        assertEquals(lectureService.findById(1L).getName(), "Test name");
    }

    @Test
    void deleteById() {
        int size = lectureService.findAll().size();
        lectureService.deleteById(1L);

        assertEquals(lectureService.findAll().size(), size - 1);
    }
}
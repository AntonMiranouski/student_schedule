package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.GroupLectureDay;
import anton.miranouski.student_schedule.model.Lecture;
import anton.miranouski.student_schedule.repository.GroupLectureDayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupLectureDayServiceTest {

    @Autowired
    GroupLectureDayRepository lectureDayRepository;

    @Autowired
    GroupLectureDayService lectureDayService;

    @Test
    void findByStudentIdAndDate() {
        assertEquals(
                lectureDayService.findByStudentIdAndDate(1L, LocalDate.parse("2021-11-11")).getDate(),
                LocalDate.parse("2021-11-11")
        );
    }

    @Test
    void save() {
        int size = lectureDayRepository.findAll().size();
        lectureDayService.save(new GroupLectureDay());

        assertEquals(lectureDayRepository.findAll().size(), size + 1);
    }

    @Test
    @Transactional
    void update() {
        GroupLectureDay lectureDay = lectureDayRepository.getById(1L);
        lectureDay.setDate(LocalDate.of(2030, 1, 1));
        lectureDayService.update(lectureDay);

        assertEquals(lectureDayRepository.getById(1L).getDate(), LocalDate.parse("2030-01-01"));
    }

    @Test
    void deleteById() {
        int size = lectureDayRepository.findAll().size();
        lectureDayService.deleteById(1L);

        assertEquals(lectureDayRepository.findAll().size(), size - 1);
    }
}
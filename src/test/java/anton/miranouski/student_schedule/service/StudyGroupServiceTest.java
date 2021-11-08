package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.StudyGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class StudyGroupServiceTest {

    @Autowired
    private StudyGroupService studyGroupService;

    @Test
    void findAll() {
        assertFalse(studyGroupService.findAll().isEmpty());
    }

    @Test
    void findById() {
        assertEquals(studyGroupService.findById(1L).getId(), 1L);
    }

    @Test
    void save() {
        int size = studyGroupService.findAll().size();
        studyGroupService.save(new StudyGroup());

        assertEquals(studyGroupService.findAll().size(), size + 1);
    }

    @Test
    void update() {
        StudyGroup studyGroup = studyGroupService.findById(1L);
        studyGroup.setName("Test name");
        studyGroupService.update(studyGroup);

        assertEquals(studyGroupService.findById(1L).getName(), "Test name");
    }

    @Test
    void deleteById() {
        int size = studyGroupService.findAll().size();
        studyGroupService.deleteById(1L);

        assertEquals(studyGroupService.findAll().size(), size - 1);
    }
}
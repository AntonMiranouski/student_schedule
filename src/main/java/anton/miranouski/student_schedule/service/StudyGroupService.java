package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.StudyGroup;
import anton.miranouski.student_schedule.repository.StudyGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupService {

    private static final String ERROR_MESSAGE = "No Group with this id was found";

    private final StudyGroupRepository studyGroupRepository;

    public StudyGroupService(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    public List<StudyGroup> findAll() {
        return studyGroupRepository.findAll();
    }

    public StudyGroup findById(Long id) {
        return studyGroupRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(StudyGroup studyGroup) {
        studyGroup.setId(null);
        studyGroupRepository.saveAndFlush(studyGroup);
    }

    public void update(StudyGroup studyGroup) {
        studyGroupRepository.saveAndFlush(studyGroup);
    }

    public void deleteById(Long id) {
        studyGroupRepository.deleteById(id);
    }
}

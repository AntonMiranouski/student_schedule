package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.Lecture;
import anton.miranouski.student_schedule.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    private static final String ERROR_MESSAGE = "No Lecture with this id was found";

    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    public Lecture findById(Long id) {
        return lectureRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Lecture lecture) {
        lecture.setId(null);
        lectureRepository.saveAndFlush(lecture);
    }

    public void update(Lecture lecture) {
        lectureRepository.saveAndFlush(lecture);
    }

    public void deleteById(Long id) {
        lectureRepository.deleteById(id);
    }
}

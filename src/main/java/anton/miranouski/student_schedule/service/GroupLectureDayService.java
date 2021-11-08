package anton.miranouski.student_schedule.service;

import anton.miranouski.student_schedule.model.GroupLectureDay;
import anton.miranouski.student_schedule.repository.GroupLectureDayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GroupLectureDayService {

    private static final String ERROR_MESSAGE_NOT_FOUND = "No Schedule for this day was found";
    private static final String ERROR_MESSAGE_ALREADY_EXISTS = "Schedule for this date and group already exists";

    private final GroupLectureDayRepository lectureDayRepository;

    public GroupLectureDayService(GroupLectureDayRepository lectureDayRepository) {
        this.lectureDayRepository = lectureDayRepository;
    }

    public GroupLectureDay findByStudentIdAndDate(Long id, LocalDate date) {
        GroupLectureDay lectureDay = lectureDayRepository.getByStudentAndDate(id, date);

        if (lectureDay != null) {
            return lectureDay;
        } else {
            throw new RuntimeException(ERROR_MESSAGE_NOT_FOUND);
        }
    }

    public void save(GroupLectureDay groupLectureDay) {
        if (!lectureDayRepository.existsByDateAndStudyGroup(
                groupLectureDay.getDate(), groupLectureDay.getStudyGroup()
        )) {
            groupLectureDay.setId(null);
            lectureDayRepository.saveAndFlush(groupLectureDay);
        } else throw new RuntimeException(ERROR_MESSAGE_ALREADY_EXISTS);
    }

    public void update(GroupLectureDay groupLectureDay) {
        lectureDayRepository.saveAndFlush(groupLectureDay);
    }

    public void deleteById(Long id) {
        lectureDayRepository.deleteById(id);
    }
}

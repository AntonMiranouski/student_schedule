package anton.miranouski.student_schedule.repository;

import anton.miranouski.student_schedule.model.GroupLectureDay;
import anton.miranouski.student_schedule.model.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface GroupLectureDayRepository extends JpaRepository<GroupLectureDay, Long> {

    @Query(
            "from GroupLectureDay ld where ld.studyGroup = (select s.studyGroup from Student s where s.id = :studentId) and ld.date = :date"
    )
    GroupLectureDay getByStudentAndDate(Long studentId, LocalDate date);

    Boolean existsByDateAndStudyGroup(LocalDate date, StudyGroup studyGroup);
}

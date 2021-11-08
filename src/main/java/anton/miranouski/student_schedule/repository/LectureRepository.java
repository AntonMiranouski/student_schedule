package anton.miranouski.student_schedule.repository;

import anton.miranouski.student_schedule.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}

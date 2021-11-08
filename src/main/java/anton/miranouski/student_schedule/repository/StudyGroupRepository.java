package anton.miranouski.student_schedule.repository;

import anton.miranouski.student_schedule.model.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
}

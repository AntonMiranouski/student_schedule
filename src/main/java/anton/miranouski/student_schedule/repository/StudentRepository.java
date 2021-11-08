package anton.miranouski.student_schedule.repository;

import anton.miranouski.student_schedule.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

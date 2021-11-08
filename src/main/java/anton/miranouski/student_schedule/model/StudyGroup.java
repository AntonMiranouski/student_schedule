package anton.miranouski.student_schedule.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "studyGroup")
    private List<Student> students;

    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.REMOVE)
    private List<GroupLectureDay> lectureDays;

    @PreRemove
    private void removeGroupFromStudents() {
        for (Student student : students) {
            student.setStudyGroup(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup studyGroup = (StudyGroup) o;
        return Objects.equals(id, studyGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

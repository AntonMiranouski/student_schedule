package anton.miranouski.student_schedule.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

    private Long id;

    private String name;

    private StudyGroupResponse studyGroup;
}

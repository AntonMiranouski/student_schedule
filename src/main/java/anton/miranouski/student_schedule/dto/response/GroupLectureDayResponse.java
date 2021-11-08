package anton.miranouski.student_schedule.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GroupLectureDayResponse {

    private Long id;

    private LocalDate date;

    private StudyGroupResponse studyGroup;

    private List<LectureResponse> lectures;
}

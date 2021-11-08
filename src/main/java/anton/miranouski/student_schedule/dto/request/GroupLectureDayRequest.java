package anton.miranouski.student_schedule.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GroupLectureDayRequest {

    private Long id;

    @NotNull
    private LocalDate date;

    private StudyGroupRequest studyGroup;

    private List<LectureRequest> lectures;
}

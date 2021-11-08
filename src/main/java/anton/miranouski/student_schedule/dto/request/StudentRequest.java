package anton.miranouski.student_schedule.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StudentRequest {

    private Long id;

    @NotBlank
    private String name;

    private StudyGroupRequest studyGroup;
}

package anton.miranouski.student_schedule.controller;

import anton.miranouski.student_schedule.dto.request.StudyGroupRequest;
import anton.miranouski.student_schedule.dto.response.StudyGroupResponse;
import anton.miranouski.student_schedule.model.StudyGroup;
import anton.miranouski.student_schedule.service.StudyGroupService;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;
    private final DozerBeanMapper mapper;

    public StudyGroupController(StudyGroupService studyGroupService, DozerBeanMapper mapper) {
        this.studyGroupService = studyGroupService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StudyGroupResponse> findAll() {
        final List<StudyGroup> studyGroups = studyGroupService.findAll();
        return studyGroups.stream()
                .map(studyGroup -> mapper.map(studyGroup, StudyGroupResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudyGroupResponse findById(@PathVariable Long id) {
        final StudyGroup studyGroup = studyGroupService.findById(id);
        return mapper.map(studyGroup, StudyGroupResponse.class);
    }

    @PostMapping
    public StudyGroupResponse save(@Valid @RequestBody StudyGroupRequest studyGroupRequest) {
        final StudyGroup studyGroup = mapper.map(studyGroupRequest, StudyGroup.class);
        studyGroupService.save(studyGroup);
        return mapper.map(studyGroup, StudyGroupResponse.class);
    }

    @PutMapping
    public StudyGroupResponse update(@Valid @RequestBody StudyGroupRequest studyGroupRequest) {
        final StudyGroup studyGroup = mapper.map(studyGroupRequest, StudyGroup.class);
        studyGroupService.update(studyGroup);
        return mapper.map(studyGroup, StudyGroupResponse.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studyGroupService.deleteById(id);
    }
}

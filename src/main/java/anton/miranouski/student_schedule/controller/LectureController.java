package anton.miranouski.student_schedule.controller;

import anton.miranouski.student_schedule.dto.request.LectureRequest;
import anton.miranouski.student_schedule.dto.response.LectureResponse;
import anton.miranouski.student_schedule.model.Lecture;
import anton.miranouski.student_schedule.service.LectureService;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;
    private final DozerBeanMapper mapper;

    public LectureController(LectureService lectureService, DozerBeanMapper mapper) {
        this.lectureService = lectureService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<LectureResponse> findAll() {
        final List<Lecture> lectures = lectureService.findAll();
        return lectures.stream()
                .map(lecture -> mapper.map(lecture, LectureResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LectureResponse findById(@PathVariable Long id) {
        final Lecture lecture = lectureService.findById(id);
        return mapper.map(lecture, LectureResponse.class);
    }

    @PostMapping
    public LectureResponse save(@Valid @RequestBody LectureRequest lectureRequest) {
        final Lecture lecture = mapper.map(lectureRequest, Lecture.class);
        lectureService.save(lecture);
        return mapper.map(lecture, LectureResponse.class);
    }

    @PutMapping
    public LectureResponse update(@Valid @RequestBody LectureRequest lectureRequest) {
        final Lecture lecture = mapper.map(lectureRequest, Lecture.class);
        lectureService.update(lecture);
        return mapper.map(lecture, LectureResponse.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        lectureService.deleteById(id);
    }
}

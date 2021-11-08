package anton.miranouski.student_schedule.controller;

import anton.miranouski.student_schedule.dto.request.GroupLectureDayRequest;
import anton.miranouski.student_schedule.dto.response.GroupLectureDayResponse;
import anton.miranouski.student_schedule.model.GroupLectureDay;
import anton.miranouski.student_schedule.service.GroupLectureDayService;
import org.dozer.DozerBeanMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/lectureDays")
public class GroupLectureDayController {

    private final GroupLectureDayService lectureDayService;
    private final DozerBeanMapper mapper;

    public GroupLectureDayController(GroupLectureDayService lectureDayService, DozerBeanMapper mapper) {
        this.lectureDayService = lectureDayService;
        this.mapper = mapper;
    }

    @GetMapping
    public GroupLectureDayResponse findByStudentIdAndDate(
            @RequestParam Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        final GroupLectureDay lectureDay = lectureDayService.findByStudentIdAndDate(id, date);
        return mapper.map(lectureDay, GroupLectureDayResponse.class);
    }

    @PostMapping
    public GroupLectureDayResponse save(@Valid @RequestBody GroupLectureDayRequest lectureDayRequest) {
        final GroupLectureDay lectureDay = mapper.map(lectureDayRequest, GroupLectureDay.class);
        lectureDayService.save(lectureDay);
        return mapper.map(lectureDay, GroupLectureDayResponse.class);
    }

    @PutMapping
    public GroupLectureDayResponse update(@Valid @RequestBody GroupLectureDayRequest lectureDayRequest) {
        final GroupLectureDay lectureDay = mapper.map(lectureDayRequest, GroupLectureDay.class);
        lectureDayService.update(lectureDay);
        return mapper.map(lectureDay, GroupLectureDayResponse.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        lectureDayService.deleteById(id);
    }
}

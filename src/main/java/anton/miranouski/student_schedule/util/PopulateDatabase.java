package anton.miranouski.student_schedule.util;

import anton.miranouski.student_schedule.model.GroupLectureDay;
import anton.miranouski.student_schedule.model.Lecture;
import anton.miranouski.student_schedule.model.Student;
import anton.miranouski.student_schedule.model.StudyGroup;
import anton.miranouski.student_schedule.repository.GroupLectureDayRepository;
import anton.miranouski.student_schedule.repository.LectureRepository;
import anton.miranouski.student_schedule.repository.StudentRepository;
import anton.miranouski.student_schedule.repository.StudyGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class PopulateDatabase {

    private final GroupLectureDayRepository lectureDayRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    private final Random random = new Random();

    private final List<String> firstNames = Arrays.asList(
            "John", "Mark", "Jan", "Helmut", "Budzikid", "Vid", "Bill", "Traniata", "Maximilian", "Luigi", "William"
    );
    private final List<String> lastNames = Arrays.asList(
            "Doe", "Johnson", "Paniatoŭski", "Pac", "Hindenburg", "Hiedyminavič", "Gates", "Cage", "Allegri", "Hill"
    );

    private final List<String> lectureNames = Arrays.asList(
            "Structural mechanics", "Engineering networks and equipment", "Soil mechanics, bases and foundations",
            "Metal constructions", "Automation of calculations and design",
            "Exploitation and technical service of buildings and facilities"
    );

    public PopulateDatabase(
            GroupLectureDayRepository lectureDayRepository,
            StudyGroupRepository studyGroupRepository,
            LectureRepository lectureRepository,
            StudentRepository studentRepository
    ) {
        this.lectureDayRepository = lectureDayRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.lectureRepository = lectureRepository;
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void populate() {
        createEntities();

        createRelations();
    }

    private void createEntities() {
        StudyGroup studyGroup1 = new StudyGroup();
        StudyGroup studyGroup2 = new StudyGroup();
        studyGroup1.setName("SG_1");
        studyGroup2.setName("SG_2");
        studyGroupRepository.saveAndFlush(studyGroup1);
        studyGroupRepository.saveAndFlush(studyGroup2);

        List<Student> students = new ArrayList<>();
        List<Lecture> lectures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName(
                    firstNames.get(random.nextInt(firstNames.size())) + " "
                            + lastNames.get(random.nextInt(lastNames.size()))
            );
            students.add(student);
        }

        for (String name : lectureNames) {
            Lecture lecture = new Lecture();
            lecture.setName(name);
            lectures.add(lecture);
        }

        studentRepository.saveAllAndFlush(students);
        lectureRepository.saveAllAndFlush(lectures);
    }

    private void createRelations() {
        List<StudyGroup> studyGroups = studyGroupRepository.findAll();
        List<Lecture> lectures = lectureRepository.findAll();
        List<Student> students = studentRepository.findAll();
        List<GroupLectureDay> lectureDays = new ArrayList<>();

        for (int i = 0; i < students.size() / 2; i++) {
            students.get(i).setStudyGroup(studyGroups.get(0));
        }

        for (Student student : students) {
            if (student.getStudyGroup() == null) {
                student.setStudyGroup(studyGroups.get(1));
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 8; i < 14; i++) {
                GroupLectureDay lectureDay = new GroupLectureDay();
                lectureDay.setDate(LocalDate.of(2021, 11, i));
                lectureDay.setStudyGroup(studyGroups.get(j));

                List<Lecture> randomLectures = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    randomLectures.add(lectures.get(random.nextInt(lectures.size())));
                }
                lectureDay.setLectures(randomLectures);

                lectureDays.add(lectureDay);
            }
        }

        studentRepository.saveAllAndFlush(students);
        lectureDayRepository.saveAllAndFlush(lectureDays);
    }
}

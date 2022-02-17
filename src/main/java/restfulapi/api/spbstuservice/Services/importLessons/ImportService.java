package restfulapi.api.spbstuservice.Services.importLessons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Lesson;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Subject;
import restfulapi.api.spbstuservice.Repositories.*;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Faculties;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.Day;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.LessonSpbstu;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.Lessons;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;
import restfulapi.api.spbstuservice.SpbstuServiceApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImportService {


    public final ProfessorRepository professorRepository;
    public final BuildingRepository buildingRepository;
    private final PupilGroupRepository pupilGroupRepository;
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public ImportService(ProfessorRepository professorRepository,
                         BuildingRepository buildingRepository,
                         PupilGroupRepository pupilGroupRepository, LessonRepository lessonRepository, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.buildingRepository = buildingRepository;
        this.pupilGroupRepository = pupilGroupRepository;
        this.lessonRepository = lessonRepository;
        this.subjectRepository = subjectRepository;
    }


    public void importTeachers() {
        Teachers teachers = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/teachers", Teachers.class);
        if (teachers == null || teachers.getTeachers() == null || teachers.getTeachers().isEmpty())
            return;
        List<Professor> professors = teachers
                .getTeachers()
                .stream()
                .map(Professor::new)
                .collect(Collectors.toList());
        professorRepository.saveAll(professors);
    }

    public void importGroups() {
        Faculties faculties = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties", Faculties.class);
        if (faculties == null || faculties.getFaculties() == null || faculties.getFaculties().isEmpty())
            return;
        for (int facultyId : faculties.getFaculties().stream().map(Faculties.Faculty::getId).collect(Collectors.toList())) {
            new Thread(() -> {
                importThreadGroups(facultyId);
            }).start();
        }
    }


    private void importThreadGroups(int facultyId) {
        Groups groups = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties/"+ facultyId + "/groups", Groups.class);
        if(groups == null || groups.getGroups().isEmpty())
            return;
        List<PupilGroup> pupilGroups = groups
                .getGroups()
                .stream()
                .map(PupilGroup::new)
                .collect(Collectors.toList());
        pupilGroupRepository.saveAll(pupilGroups);
        ArrayList<Integer> groupIdList = (ArrayList<Integer>) pupilGroups.stream().map(PupilGroup::getUniversityGroupId).collect(Collectors.toList());
        SpbstuServiceApplication.logger.info("вот список групп" + groupIdList);
        for(Integer groupId : groupIdList){
            new Thread(() -> {
                importThreadLessonFromGroup(groupId);
            }).start();
        }
    }


    private void importThreadLessonFromGroup(Integer groupId) {
        Lessons lessonsBody = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/scheduler/"+ groupId, Lessons.class);
        SpbstuServiceApplication.logger.info("вот ответ по группе: " + lessonsBody.getDays());
        if(lessonsBody.getDays() == null || lessonsBody.getDays().isEmpty())
            return;
        SpbstuServiceApplication.logger.info("вот список: " + lessonsBody.getDays());
        String nextWeek = importThreadLesson(lessonsBody, groupId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd]");
        LocalDate dt = LocalDate.parse(nextWeek, formatter);
        dt = dt.plusDays(7);
        SpbstuServiceApplication.logger.info("вот:" + dt);
        lessonsBody = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/scheduler/"+
                groupId +"?date="+dt, Lessons.class);
        if(lessonsBody == null || lessonsBody.getDays().isEmpty())
            return;
        importThreadLesson(lessonsBody, groupId);
    }




    private String importThreadLesson(Lessons body, Integer groupId){
        for(Day day : body.getDays()){
            String weekDay = getDayInText(day.getWeekday());
            boolean isOdd = body.getWeek().getIsOdd();
            List<Lesson> lessonsList = day.getLessons()
                    .stream()
                    .map(Lesson::new)
                    .peek(s -> s.setDay(String.valueOf(weekDay)))
                    .peek(s -> {
                        if(isOdd) {
                            s.setWeek("odd");
                            s.setId(s.getId()+"o"); //odd
                        } else {
                            s.setWeek("even");
                            s.setId(s.getId()+"e"); //even
                        }
                    })
                    .peek(s -> {
                        s.setGroups(pupilGroupRepository.getAllByUniversityGroupIdIn(s.getGroupUniversityIds()));
                        s.setProfessors(professorRepository.getAllByProfessorUniversityIdIn(s.getTeacherUniversityIds()));
                    })
                    .collect(Collectors.toList());
            List<Subject> subjectList = lessonsList.stream()
                            .map(Subject::new)
                            .collect(Collectors.toList());
            SpbstuServiceApplication.logger.info("вот ответ уроков:" + lessonsList);
            subjectRepository.saveAll(subjectList);
            lessonsList = lessonsList.stream().peek(s -> s.setSubject(subjectRepository.findByName(s.getName()))).collect(Collectors.toList());
            lessonRepository.saveAll(lessonsList);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return body.getDays().get(0).getDate();
    }




    private String getDayInText(Integer day){
        return switch (day) {
            case 1 -> "mon";
            case 2 -> "tue";
            case 3 -> "wed";
            case 4 -> "thu";
            case 5 -> "fri";
            case 6 -> "sat";
            case 7 -> "sun";
            default -> "NAN";
        };
    }
}

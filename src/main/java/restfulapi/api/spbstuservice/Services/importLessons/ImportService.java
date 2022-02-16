package restfulapi.api.spbstuservice.Services.importLessons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Lesson;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;
import restfulapi.api.spbstuservice.Repositories.BuildingRepository;
import restfulapi.api.spbstuservice.Repositories.LessonRepository;
import restfulapi.api.spbstuservice.Repositories.ProfessorRepository;
import restfulapi.api.spbstuservice.Repositories.PupilGroupRepository;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Faculties;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;
import restfulapi.api.spbstuservice.SpbstuServiceApplication;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportService {


    public final ProfessorRepository professorRepository;
    public final BuildingRepository buildingRepository;
    private final PupilGroupRepository pupilGroupRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public ImportService(ProfessorRepository professorRepository,
                         BuildingRepository buildingRepository,
                         PupilGroupRepository pupilGroupRepository, LessonRepository lessonRepository) {
        this.professorRepository = professorRepository;
        this.buildingRepository = buildingRepository;
        this.pupilGroupRepository = pupilGroupRepository;
        this.lessonRepository = lessonRepository;
    }


    public void importTeachers() throws UserException {
        Teachers teachers = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/teachers", Teachers.class);
        if (teachers == null || teachers.getTeachers().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "teachers in import are missing!");
        List<Professor> professors = teachers
                .getTeachers()
                .stream()
                .map(Professor::new)
                .collect(Collectors.toList());
        professorRepository.saveAll(professors);
    }

    public void importGroups() throws UserException {
        Faculties faculties = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties", Faculties.class);
        if (faculties == null || faculties.getFaculties().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "faculties in import are missing!");
        for (int facultyId : faculties.getFaculties().stream().map(Faculties.Faculty::getId).collect(Collectors.toList())) {
            new Thread(() -> {
                try {
                    importThreadGroups(facultyId);
                } catch (UserException e) {
                    SpbstuServiceApplication.logger.error(e.getMessage());
                }
            }).start();
        }
    }


    private void importThreadGroups(int facultyId) throws UserException {
        Groups groups = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties/"+ facultyId + "/groups", Groups.class);
        if(groups == null || groups.getGroups().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "groups in import are missing!");
        List<PupilGroup> pupilGroups = groups
                .getGroups()
                .stream()
                .map(PupilGroup::new)
                .collect(Collectors.toList());
        pupilGroupRepository.saveAll(pupilGroups);
        for(String groupId : pupilGroups.stream().map(PupilGroup::getId).collect(Collectors.toList())){
            new Thread(() -> {
                try {
                    importThreadLessonFromGroup(groupId);
                } catch (UserException e) {
                    SpbstuServiceApplication.logger.error(e.getMessage());
                }
            }).start();
        }
    }




    private void importThreadLessonFromGroup(String groupId) throws UserException {
        Lessons lessonsBody = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/scheduler/"+ groupId, Lessons.class);
        if(lessonsBody == null || lessonsBody.getDays() == null || lessonsBody.getDays().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "lessons in import are missing!");
        String nextWeek = importThreadLesson(lessonsBody);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(nextWeek);
        offsetDateTime = offsetDateTime.plusDays(7);
        lessonsBody = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/scheduler/"+
                groupId +"?date="+offsetDateTime.format(formatter), Lessons.class);
        if(lessonsBody == null || lessonsBody.getDays().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "lessons in import are missing!");
        importThreadLesson(lessonsBody);
    }




    private String importThreadLesson(Lessons body){
        for(Lessons.Day day : body.getDays()){
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
                    .collect(Collectors.toList());
            lessonRepository.saveAll(lessonsList);
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

package restfulapi.api.spbstuservice.Services.importLessons;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.*;
import restfulapi.api.spbstuservice.Repositories.*;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Buildings;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Faculties;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.Day;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.Lessons;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;
import restfulapi.api.spbstuservice.SpbstuServiceApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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


    public void importBuildings() {
        Buildings buildingsSpbstu = getBuildingsFromSpbstu();
        if(buildingsSpbstu==null || buildingsSpbstu.getBuildings()==null || buildingsSpbstu.getBuildings().isEmpty())
            return;
        List<Building> buildings = buildingsSpbstu
                .getBuildings()
                .stream()
                .map(Building::new)
                .collect(Collectors.toList());
        buildingRepository.saveAll(buildings);

    }
    @SneakyThrows
    public void importTeachers() {
        Teachers teachers = getTeachersFromSPbstu();
        if (teachers == null || teachers.getTeachers() == null || teachers.getTeachers().isEmpty())
            return;
        List<Professor> professors = teachers
                .getTeachers()
                .stream()
                .map(Professor::new)
                .collect(Collectors.toList());
        professorRepository.saveAll(professors);
    }


    public void importGroups(){
        Faculties faculties = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties", Faculties.class);
        if (faculties == null || faculties.getFaculties() == null || faculties.getFaculties().isEmpty())
            return;
        for (int facultyId : faculties.getFaculties().stream().map(Faculties.Faculty::getId).collect(Collectors.toList())) {
            Thread t = new Thread(() -> {
                try {
                    importThreadGroups(facultyId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }


    private void importThreadGroups(int facultyId) throws InterruptedException {
        Groups groups = getGroupsFromSpbstu(String.valueOf(facultyId));
        if (groups == null || groups.getGroups().isEmpty())
            return;
        List<PupilGroup> pupilGroups = groups
                .getGroups()
                .stream()
                .map(PupilGroup::new)
                .collect(Collectors.toList());
        pupilGroupRepository.saveAll(pupilGroups);
        ArrayList<Integer> groupIdList = (ArrayList<Integer>) pupilGroups.stream().map(PupilGroup::getUniversityGroupId).collect(Collectors.toList());
        for (Integer groupId :
             groupIdList) {
            Thread t = new Thread(() -> {
                try {
                    importThreadLessonFromGroups(groupId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }

    private void importThreadLessonFromGroups(Integer groupId) throws InterruptedException {
        Lessons lessonsBody = getLessonsFromSpbstu(String.valueOf(groupId));
        if ((lessonsBody != null ? lessonsBody.getDays() : null) == null || lessonsBody.getDays().isEmpty())
                return;
            String nextWeek = importThreadLesson(lessonsBody);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd]");
            LocalDate dt = LocalDate.parse(nextWeek, formatter);
            dt = dt.plusDays(7);
            lessonsBody = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/scheduler/" +
                    groupId + "?date=" + dt, Lessons.class);
            if (lessonsBody == null || lessonsBody.getDays().isEmpty())
                return;
            SpbstuServiceApplication.logger.info("чекает группу: " + groupId);
        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        importThreadLesson(lessonsBody);
    }




    private String importThreadLesson(Lessons body){
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
            subjectRepository.saveAll(subjectList);
            lessonsList = lessonsList.stream().peek(s -> {
                s.setSubject(subjectRepository.findByName(s.getName()));
            }).collect(Collectors.toList());
            lessonRepository.saveAll(lessonsList);
        }
        return body.getDays().get(0).getDate();
    }



    @SneakyThrows
    private Buildings getBuildingsFromSpbstu() {
        Buildings buildings;
        try {
            buildings = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/buildings", Buildings.class);
        } catch (RestClientException e) {
            Thread.sleep(1500);
            buildings = getBuildingsFromSpbstu();
        }
        return buildings;
    }

    @SneakyThrows
    private Teachers getTeachersFromSPbstu() {
        Teachers teachers;
        try {
            teachers = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/teachers", Teachers.class);
        } catch (RestClientException e) {
            Thread.sleep(1500);
            teachers = getTeachersFromSPbstu();
        }
        return teachers;
    }

    @SneakyThrows
    private Lessons getLessonsFromSpbstu(String groupId) throws InterruptedException {
        Lessons lessons;
        try {
            lessons = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/scheduler/" + groupId, Lessons.class);
        } catch (Exception e) {
            Thread.sleep(50);
            lessons = getLessonsFromSpbstu(groupId);
        }
        return lessons;
    }

    @SneakyThrows
    private Groups getGroupsFromSpbstu(String facultyId) throws InterruptedException {
        Groups groups;
        try {
            groups = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties/" + facultyId + "/groups", Groups.class);
        } catch (Exception e) {
            Thread.sleep(50);
            groups = getGroupsFromSpbstu(facultyId);
        }
        return groups;
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

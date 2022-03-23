package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.Auditory;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons.LessonSpbstu;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Lesson {


    @Id
    @Column
    @JsonProperty("_id")
    private String id;
    @Column
    private String name;
    @Column
    private String startTime;
    @Column
    private String endTime;
    @Column
    private String lessonNum;
    @Column
    private String day;
    @Column
    private String rooms;
    @Column
    private String type;
    @Column
    private String week;


    @ManyToMany
    private Set<Professor> professors;

    @ManyToOne
    private Subject subject;

    @ManyToMany
    private Set<PupilGroup> groups;


    @Transient
    private ArrayList<Integer> groupUniversityIds = new ArrayList<>();
    @Transient
    private String subjectId;
    @Transient
    private ArrayList<Integer> teacherUniversityIds = new ArrayList<>();



    public Lesson() {

    }

    public Lesson(LessonSpbstu lessonSpbstu) {
        id = DigestUtils.sha256Hex("spbstu_lesson" + lessonSpbstu.getTimeStart() + getAuditory(lessonSpbstu.getAuditories().get(0)) +
                lessonSpbstu.getSubject() + getTeacher(lessonSpbstu.getTeachers()) + lessonSpbstu.getAuditories().get(0).getId() + lessonSpbstu.getTypeObj().getId());
        name = lessonSpbstu.getSubject();
        startTime = lessonSpbstu.getTimeStart();
        endTime = lessonSpbstu.getTimeEnd();
        lessonNum = createLessonNum(startTime);
        rooms = setAdress(lessonSpbstu.getAuditories().get(0));
        type = lessonSpbstu.getTypeObj().getName();
        groupUniversityIds.addAll(lessonSpbstu.getGroups().stream().map(Groups.Group::getId).collect(Collectors.toList()));
        subjectId = DigestUtils.sha256Hex("spbstu" + lessonSpbstu.getSubject());
        if(lessonSpbstu.getTeachers() != null) {
            teacherUniversityIds.addAll(lessonSpbstu.getTeachers().stream().map(Teachers.Teacher::getId).collect(Collectors.toList()));
        }
    }

    private String createLessonNum(String startTime){
        switch (startTime) {
            case "08:00" -> {
                return "1";
            }
            case "10:00" -> {
                return "2";
            }

            case "12:00" -> {
                return "3";
            }

            case "14:00" -> {
                return "4";
            }

            case "16:00" -> {
                return "5";
            }

            case "18:00" -> {
                return "6";
            }
            case "19:45" -> {
                return "7";
            }
        }

            return "-";
        }

    private String getTeacher(List<Teachers.Teacher> teachers) {
        if(teachers!=null)
            return teachers.get(0).getFullName();
        return " ";
    }

    private String getAuditory(Auditory auditory) {
        if(auditory !=null)
            return auditory.getName();
        return " ";
    }

    private String setAdress(Auditory auditory) {
        String room = "";
        if(!auditory.getName().isEmpty()){
            room += "ауд. " + auditory.getName();
        }
        if(!auditory.getBuilding().getAbbr().isEmpty()){
            room += ", " + auditory.getBuilding().getAbbr();
        }

        return room;
    }
    }

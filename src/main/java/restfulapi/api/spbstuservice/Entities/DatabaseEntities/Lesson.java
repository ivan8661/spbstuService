package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.util.Pair;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import java.util.Set;

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


    public Lesson() {

    }

    public Lesson(Lessons.Day.Lesson lesson) {
        id = DigestUtils.sha256Hex("spbstu_lesson" + lesson.getTimeStart() +
                lesson.getSubject() + lesson.getTeachers().get(0).getId() + lesson.getAuditories().get(0).getId());
        startTime = lesson.getTimeStart();
        endTime = lesson.getTimeEnd();
        lessonNum = createLessonNum(startTime);
        rooms = "ауд. " + lesson.getAuditories().get(0).getName() + "адрес: " + lesson.getAuditories().get(0).getBuilding().getAddress();
        type = lesson.getTypeObj().getName();


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
        }

            return "-";
        }
    }

package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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



    public Lesson() {

    }
}

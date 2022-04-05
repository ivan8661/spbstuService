package restfulapi.api.spbstuservice.Entities.DatabaseEntities;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Professor {


    @Id
    @Column
    @JsonProperty("_id")
    private String id;
    @Column
    private String name;
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer professorUniversityId;


    public Professor() {
    }

    public Professor(Teachers.Teacher teacher) {
        id = DigestUtils.sha256Hex("spbstu_teacher" + teacher.getId());
        name = teacher.getFullName();
        professorUniversityId = teacher.getId();
    }
}

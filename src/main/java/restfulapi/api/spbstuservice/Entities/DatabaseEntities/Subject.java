package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Subject {


    @Id
    @Column
    @JsonProperty("_id")
    private String id;
    @Column
    private String name;

    @OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Lesson> lessons;



    public Subject() {
    }

    public Subject(Lesson lesson) {
        id = DigestUtils.sha256Hex("spbstu" + lesson.getName());
        name = lesson.getName();
    }
}

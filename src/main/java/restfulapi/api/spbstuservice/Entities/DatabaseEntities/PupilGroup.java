package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Lazy;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class PupilGroup {

    @Id
    @JsonProperty("_id")
    private String id;
    @Column
    private String name;
    @Column
    private String level;
    @Column
    private String type;
    @Column
    private String kind;
    @Column
    private String spec;
    @Column
    private String year;
    @Column
    private Integer universityGroupId;


    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Lesson> lessons;




    public PupilGroup() {

    }

    public PupilGroup(Groups.Group group) {
        id = DigestUtils.sha256Hex("spbstu_group" + group.getId());
        name = group.getName();
        universityGroupId = group.getId();
    }

    @Override
    @Lazy
    public String toString() {
        return "PupilGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", spec='" + spec + '\'' +
                ", year='" + year + '\'' +
                ", universityGroupId=" + universityGroupId +
                ", lessons=" + lessons +
                '}';
    }
}

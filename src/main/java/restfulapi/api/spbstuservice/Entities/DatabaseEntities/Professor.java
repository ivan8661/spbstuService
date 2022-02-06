package restfulapi.api.spbstuservice.Entities.DatabaseEntities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}

package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}

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


    public PupilGroup() {

    }
}

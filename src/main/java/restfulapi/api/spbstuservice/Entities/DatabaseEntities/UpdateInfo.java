package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class UpdateInfo {

    @Id
    private String name;

    @Column
    private long syncTime;

    @Column
    private String week;


    public UpdateInfo() {

    }
}

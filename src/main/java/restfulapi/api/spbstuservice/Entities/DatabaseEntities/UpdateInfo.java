package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


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

package restfulapi.api.spbstuservice.Entities.DatabaseEntities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Buildings;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@Entity
public class Building {


    @Id
    @JsonProperty("_id")
    private String id;

    @Column
    private String name;

    @Column
    private String address;



    public Building() {

    }

    public Building(Buildings.Building building) {
        id = DigestUtils.sha256Hex("spbstu_building" + building.getId());
        name = building.getName();
        address = building.getAddress();
    }
}

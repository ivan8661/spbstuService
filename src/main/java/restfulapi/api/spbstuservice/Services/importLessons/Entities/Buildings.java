package restfulapi.api.spbstuservice.Services.importLessons.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "buildings"
})
@Generated("jsonschema2pojo")
public class Buildings {



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "name",
            "abbr",
            "address"
    })
    @Generated("jsonschema2pojo")
    public static class  Building {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("abbr")
        private String abbr;
        @JsonProperty("address")
        private String address;

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("abbr")
        public String getAbbr() {
            return abbr;
        }

        @JsonProperty("abbr")
        public void setAbbr(String abbr) {
            this.abbr = abbr;
        }

        @JsonProperty("address")
        public String getAddress() {
            return address;
        }

        @JsonProperty("address")
        public void setAddress(String address) {
            this.address = address;
        }

    }


    @JsonProperty("buildings")
    private List<Building> buildings = null;

    @JsonProperty("buildings")
    public List<Building> getBuildings() {
        return buildings;
    }

    @JsonProperty("buildings")
    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

}


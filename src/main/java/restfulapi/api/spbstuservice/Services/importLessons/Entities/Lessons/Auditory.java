package restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Auditory {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("building")
    private Building building;

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

    @JsonProperty("building")
    public Building getBuilding() {
        return building;
    }

    @JsonProperty("building")
    public void setBuilding(Building building) {
        this.building = building;
    }

    public static class Building {

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


}

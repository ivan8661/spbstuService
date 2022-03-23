package restfulapi.api.spbstuservice.Services.importLessons.Entities;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Faculties {

    @JsonProperty("faculties")
    private List<Faculty> faculties = null;

    @JsonProperty("faculties")
    public List<Faculty> getFaculties() {
        return faculties;
    }

    @JsonProperty("faculties")
    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public static class Faculty {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("abbr")
        private String abbr;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}
package restfulapi.api.spbstuservice.Services.importLessons.Entities;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "level",
        "type",
        "kind",
        "spec",
        "year"
})

public class Groups {

    @JsonProperty("groups")
    private List<Group> groups = null;
    @JsonProperty("faculty")
    private Faculty faculty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Groups{" +
                "groups=" + groups +
                ", faculty=" + faculty +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @JsonProperty("groups")
    public List<Group> getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @JsonProperty("faculty")
    public Faculty getFaculty() {
        return faculty;
    }

    @JsonProperty("faculty")
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public static class Group {

        @Override
        public String toString() {
            return "Group{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", level=" + level +
                    ", type='" + type + '\'' +
                    ", kind=" + kind +
                    ", spec='" + spec + '\'' +
                    ", year=" + year +
                    ", additionalProperties=" + additionalProperties +
                    '}';
        }

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("level")
        private Integer level;
        @JsonProperty("type")
        private String type;
        @JsonProperty("kind")
        private Integer kind;
        @JsonProperty("spec")
        private String spec;
        @JsonProperty("year")
        private Integer year;
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

        @JsonProperty("level")
        public Integer getLevel() {
            return level;
        }

        @JsonProperty("level")
        public void setLevel(Integer level) {
            this.level = level;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("kind")
        public Integer getKind() {
            return kind;
        }

        @JsonProperty("kind")
        public void setKind(Integer kind) {
            this.kind = kind;
        }

        @JsonProperty("spec")
        public String getSpec() {
            return spec;
        }

        @JsonProperty("spec")
        public void setSpec(String spec) {
            this.spec = spec;
        }

        @JsonProperty("year")
        public Integer getYear() {
            return year;
        }

        @JsonProperty("year")
        public void setYear(Integer year) {
            this.year = year;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public Groups(List<Group> groups, Faculty faculty, Map<String, Object> additionalProperties) {
        this.groups = groups;
        this.faculty = faculty;
        this.additionalProperties = additionalProperties;
    }

    public Groups() {
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

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}

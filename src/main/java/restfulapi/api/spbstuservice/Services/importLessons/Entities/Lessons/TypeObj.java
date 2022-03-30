package restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeObj {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("abbr")
    private String abbr;

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

}

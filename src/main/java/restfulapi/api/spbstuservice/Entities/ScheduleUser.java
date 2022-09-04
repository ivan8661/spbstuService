package restfulapi.api.spbstuservice.Entities;

import GetGraphQL.SearchableField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleUser {

    @JsonProperty("_id")
    private String id;

    @SearchableField
    @JsonProperty("name")
    private String name;

    public ScheduleUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScheduleUser() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

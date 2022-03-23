package restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;

import java.util.List;

public class Lessons {
    @JsonProperty("week")
    private Week week;
    @JsonProperty("days")
    private List<Day> days;
    @JsonProperty("week")
    public Week getWeek() {
        return week;
    }
    @JsonProperty("week")
    public void setWeek(Week week) {
        this.week = week;
    }
    @JsonProperty("days")
    public List<Day> getDays() {
        return days;
    }
    @JsonProperty("days")
    public void setDays(List<Day> days) {
        this.days = days;
    }





}
